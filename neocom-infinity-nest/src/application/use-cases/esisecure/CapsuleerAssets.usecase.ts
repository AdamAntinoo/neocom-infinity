import { ESIDataServicesPort } from '@Application/ports/EsiDataServices.port'
import { EsiToken } from '@Infra/adapter/inbound/EsiToken.interface'
import { Injectable } from '@nestjs/common'
import { V1AssetDto } from 'neocom-domain'

export interface CapsuleerAssetsUseCaseInput {
	jwt: string // Original encoded token to be passed to ESI
	token: EsiToken // Decoded token to be used on the service
	capsuleerId: number // Extracted capsuleer id
}
declare namespace CapsuleerAssetsUseCase {
	export type Request = CapsuleerAssetsUseCaseInput
	export type Response = Promise<V1AssetDto>
}

@Injectable()
export class CapsuleerAssetsUseCase {
	constructor(private readonly esiSecureAdapter: ESIDataServicesPort) {}

	async getAssets(input: CapsuleerAssetsUseCaseInput): Promise<V1AssetDto> {
		return this.esiSecureAdapter.getCharactersCharacterIdAssets(input.capsuleerId)
	}
}
