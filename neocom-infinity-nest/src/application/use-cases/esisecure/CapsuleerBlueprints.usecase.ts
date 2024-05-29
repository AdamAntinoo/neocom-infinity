import { EsiToken } from '@App/domain/EsiToken.interface'
import { ESIDataServicesPort } from '@App/ports/ESIDataServices.port'
import { Injectable } from '@nestjs/common'
import { GetCharactersCharacterIdBlueprints200Ok } from 'neocom-domain'
import { V1BlueprintDto } from 'neocom-domain/V1.Blueprint.dto'
import { IUseCaseInput } from './constructors/UsecaseInput.interface'
import { V1StorageLocationDto } from 'neocom-domain/V1.V1StorageLocation.dto'
import { LocationTypeEnum } from 'neocom-domain/LocationType.enumerated'

export interface CapsuleerBlueprintsUseCaseInput extends IUseCaseInput {
	jwt: string // Original encoded token to be passed to ESI
	token: EsiToken // Decoded token to be used on the service
	capsuleerId: number // Extracted capsuleer id
}
declare namespace CapsuleerBlueprintsUseCase {
	export type Request = CapsuleerBlueprintsUseCaseInput
	export type Response = Promise<V1BlueprintDto>
}
@Injectable()
export class CapsuleerBlueprintsUseCase {
	constructor(private readonly esiSecureAdapter: ESIDataServicesPort) {}

	public async getBlueprints(input: CapsuleerBlueprintsUseCaseInput): Promise<V1BlueprintDto[]> {
		const esiBlueprints: GetCharactersCharacterIdBlueprints200Ok[] = await this.esiSecureAdapter.getCharactersCharacterIdBlueprints(
			input.capsuleerId,
			input.jwt,
		)
		return this.convertBlueprints(esiBlueprints)
	}
	private convertBlueprints(esiBlueprints: GetCharactersCharacterIdBlueprints200Ok[]): Promise<V1BlueprintDto[]> {
		return new Promise<V1BlueprintDto[]>(resolve => {
			const transformedBlueprints: V1BlueprintDto[] = []
			for (const blueprint of esiBlueprints) {
				const storageLocation: V1StorageLocationDto = new V1StorageLocationDto.Builder()
					.withLocationType(this.convertStringToEnum(blueprint.location_flag))
					.withLocation(blueprint.location_id)
					.build()
				transformedBlueprints.push(
					new V1BlueprintDto.Builder()
						.withId(blueprint.item_id)
						.withTypeLink(blueprint.type_id)
						.withEfficiency(blueprint.material_efficiency, blueprint.time_efficiency)
						.withStorageLocation(storageLocation)
						.withRuns(blueprint.runs)
						.build(),
				)
			}
			resolve(transformedBlueprints)
		})
	}
	private convertStringToEnum(value: string): LocationTypeEnum {
		return LocationTypeEnum[value as keyof typeof LocationTypeEnum]
	}
}
