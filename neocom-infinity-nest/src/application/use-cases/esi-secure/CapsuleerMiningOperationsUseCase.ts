import { Injectable } from '@nestjs/common'
import { Optional } from 'typescript-optional'
import { V1MiningOperationDto, V1StackDto } from 'neocom-domain'
import { GetCharactersCharacterIdMining200Ok } from 'neocom-domain'
import { EsiToken } from '@Infra/adapter/security/EsiToken.interface'
import { MiningOperationConverter } from '@Infra/adapter/outbound/ESISecureDataServices/converter/MiningOperationConverter'
import { ESI_CONSTANTS } from '@Infra/config/GlobalConstants'
import { ESIDataServicesPort } from '@App/ports/v1ESIDataServices.port'

export interface MiningOperationsUseCaseInput {
	jwt: string // Original encoded token to be passed to ESI
	token: EsiToken // Decoded token to be used on the service
	capsuleerId: number // Extracted capsuleer id
}
declare namespace CapsuleerMiningOperationsUseCase {
	export type Request = MiningOperationsUseCaseInput
}

@Injectable()
export class CapsuleerMiningOperationsUseCase {
	constructor(private readonly esiDataService: ESIDataServicesPort) {}

	public async getMiningOperations(input: MiningOperationsUseCaseInput): Promise<V1MiningOperationDto[]> {
		const esiMiningOperations: GetCharactersCharacterIdMining200Ok[] = await this.esiDataService.getMiningOperations(input.capsuleerId, input.jwt)
		return this.aggregateMiningOperations(esiMiningOperations)
	}
	private aggregateMiningOperations(esiMiningOperations: GetCharactersCharacterIdMining200Ok[]): Promise<V1MiningOperationDto[]> {
		return new Promise<V1MiningOperationDto[]>(resolve => {
			const transformedOperations: V1MiningOperationDto[] = []
			esiMiningOperations.forEach((miningAction: GetCharactersCharacterIdMining200Ok) => {
				// - find the operation where to store the mining action.
				const operationOptional: Optional<V1MiningOperationDto> = this.findOperation(miningAction, transformedOperations)
				if (operationOptional.isPresent()) {
					operationOptional
						.get()
						.addMiningResource(new MiningOperationConverter<GetCharactersCharacterIdMining200Ok, V1StackDto>().convert(miningAction))
				} else {
					// - add new Mining Operation
					const addition: V1MiningOperationDto = new V1MiningOperationDto.Builder()
						.withId(this.generateIdentifierForMiningAction(miningAction))
						.withDate(miningAction.date)
						.withSolarSystemLink(this.getSystemLink(miningAction.solar_system_id))
						.build()
					addition.addMiningResource(new MiningOperationConverter<GetCharactersCharacterIdMining200Ok, V1StackDto>().convert(miningAction))
					// Add the new item to the collection
					transformedOperations.push(addition)
				}
			})
			resolve(transformedOperations)
		})
	}
	private findOperation(miningAction: GetCharactersCharacterIdMining200Ok, operationsList: V1MiningOperationDto[]): Optional<V1MiningOperationDto> {
		for (const operation of operationsList) {
			const newIdentifier: string = this.generateIdentifierForMiningAction(miningAction)
			if (newIdentifier === operation.id) return Optional.of(operation)
		}
		return Optional.empty()
	}
	private generateIdentifierForMiningAction(miningAction: GetCharactersCharacterIdMining200Ok): string {
		return miningAction.date + '/' + miningAction.solar_system_id
	}
	public getSystemLink(locationId: number): string {
		return ESI_CONSTANTS.BACKEND_ESI_V1_PREFIX + ESI_CONSTANTS.BACKEND_ESI_SPACE_LOCATION + locationId
	}
}
