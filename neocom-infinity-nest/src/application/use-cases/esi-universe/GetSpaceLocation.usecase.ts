import { Injectable } from '@nestjs/common'

import { ESIDataUniverseServicesPort } from 'application/ports/ESIDataUniverseServices.port'
import {
	GetUniverseConstellationsConstellationIdOk,
	GetUniverseRegionsRegionIdOk,
	GetUniverseSystemsSystemIdOk,
	V1SpaceLocationDto,
} from 'neocom-domain'

@Injectable()
export class GetSpaceLocationUseCase {
	constructor(private readonly esiUniverseServices: ESIDataUniverseServicesPort) {}

	public esiGetSpaceLocation(locationId: number): Promise<V1SpaceLocationDto> {
		return this.buildUpLocation(locationId)
	}
	private async buildUpLocation(locationId: number): Promise<V1SpaceLocationDto> {
		if (locationId < 20000000) {
			// Can be a Region
			const region: GetUniverseRegionsRegionIdOk = await this.esiUniverseServices.getUniverseRegion(locationId)
			return new V1SpaceLocationDto({
				referenceType: 'Region',
				regionId: region.region_id,
				regionName: region.name,
			})
		}
		if (locationId < 30000000) {
			// Can be a Constellation
			const constellation: GetUniverseConstellationsConstellationIdOk = await this.esiUniverseServices.getUniverseConstellation(locationId)
			const region: GetUniverseRegionsRegionIdOk = await this.esiUniverseServices.getUniverseRegion(constellation.region_id)
			// TODO - Replace this type of constructors by another that checks field names
			return new V1SpaceLocationDto({
				referenceType: 'Constellation',
				regionId: region.region_id,
				regionName: region.name,
				constellationId: constellation.constellation_id,
				constellationName: constellation.name,
			})
		}
		if (locationId < 40000000) {
			// Can be a system
			const system: GetUniverseSystemsSystemIdOk = await this.esiUniverseServices.getUniverseSystem(locationId)
			const constellation: GetUniverseConstellationsConstellationIdOk = await this.esiUniverseServices.getUniverseConstellation(
				system.constellation_id,
			)
			const region: GetUniverseRegionsRegionIdOk = await this.esiUniverseServices.getUniverseRegion(constellation.region_id)
			return new V1SpaceLocationDto({
				referenceType: 'System',
				regionId: region.region_id,
				regionName: region.name,
				constellationId: constellation.constellation_id,
				constellationName: constellation.name,
				solarSystemId: system.system_id,
				solarSystemName: system.name,
			})
		}
		// if (locationId < 61000000) { // Can be a game station
		// 	final GetUniverseStationsStationIdOk station = NeoObjects.requireNonNull(this
		//     .getUniverseStationById(locationId.intValue()),
		//     "ESI Station should not be null while creating Location.");
		// 	final GetUniverseSystemsSystemIdOk solarSystem = NeoObjects.requireNonNull(this
		//         .getUniverseSystemById(station.getSystemId()),
		//         "ESI Solar System should not be null while creating Location.");
		// 	final GetUniverseConstellationsConstellationIdOk constellation = NeoObjects.requireNonNull(this
		//             .getUniverseConstellationById(solarSystem.getConstellationId()),
		//             "ESI Constellation should not be null while creating Location.");
		// 	final GetUniverseRegionsRegionIdOk region = NeoObjects.requireNonNull(this
		//                 .getUniverseRegionById(constellation.getRegionId()),
		//                 "ESI Region should not be null while creating Location.");
		//     return this.storeOnCacheLocation(
		//         new SpaceLocationImplementation.Builder()
		//             .withRegion(region)
		//             .withConstellation(constellation)
		//             .withSolarSystem(solarSystem)
		//             .withStation(station)
		//             .build());
		// }
		// return null
	}
}
