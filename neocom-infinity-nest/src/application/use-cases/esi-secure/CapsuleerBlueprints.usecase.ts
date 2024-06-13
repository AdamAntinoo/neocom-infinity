import { EsiToken } from '@Infra/adapter/security/EsiToken.interface'
import { ESIDataServicesPort } from '@App/ports/ESIDataServices.port'
import { Injectable } from '@nestjs/common'
import { GetCharactersCharacterIdBlueprints200Ok } from 'neocom-domain'
import { V1BlueprintDto } from 'neocom-domain'
import { IUseCaseInput } from './constructors/UsecaseInput.interface'
import { V1StorageLocationDto } from 'neocom-domain'

export interface CapsuleerBlueprintsUseCaseInput extends IUseCaseInput {
	jwt: string // Original encoded token to be passed to ESI
	token: EsiToken // Decoded token to be used on the service
	capsuleerId: number // Extracted capsuleer id
}
declare namespace CapsuleerBlueprintsUseCase {
	export type Request = CapsuleerBlueprintsUseCaseInput
	export type Response = Promise<V1BlueprintDto>
}
/**
 * The use case starts with a list of the character blueprints as a raw data array where the items are instances of V1BlueprintDto. This has the ESI
 * data representation , then to simplify backend requests and UI presentation we should pack all identical blueprints that at the ESI representation
 * are always singletons with a 1 quantity into Stacks of identical blueprints. This is a simplification of the ESI data representation of a
 * blueprint.
 *
 * The packiing should create a new V1BlueprintDto for each unique blueprint and set the identicalQuantity property to the number of identical
 * blueprints.
 */
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
			let transformedBlueprints: V1BlueprintDto[] = []
			for (const blueprint of esiBlueprints) {
				const storageLocation: V1StorageLocationDto = new V1StorageLocationDto.Builder()
					.withLocationType(blueprint.location_flag)
					.withLocation(blueprint.location_id)
					.build()
				const blueprintDto: V1BlueprintDto = new V1BlueprintDto.Builder()
					.withId(blueprint.item_id)
					.withTypeLink(blueprint.type_id)
					.withEfficiency(blueprint.material_efficiency, blueprint.time_efficiency)
					.withStorageLocation(storageLocation)
					.withRuns(blueprint.runs)
					.build()
				transformedBlueprints = this.packBlueprint(blueprintDto, transformedBlueprints)
			}
			resolve(transformedBlueprints)
		})
	}
	private packBlueprint(blueprintDto: V1BlueprintDto, transformedBlueprints: V1BlueprintDto[]): V1BlueprintDto[] {
		const existingBlueprint = transformedBlueprints.find(bp => bp.isIdentical(blueprintDto))
		if (existingBlueprint) {
			existingBlueprint.identicalQuantity++
		} else {
			transformedBlueprints.push(blueprintDto)
		}
		return transformedBlueprints
	}
}
