import { EsiToken } from '@Infra/adapter/inbound/EsiToken.interface'
import { V1ESISecureDataAdapter } from '@Infra/adapter/outbound/ESISecureDataServices/V1.EsiSecureData.adapter'
import { Injectable } from '@nestjs/common'
import { V1AssetDto } from 'neocom-domain'

export interface AssetsUseCaseInput {
	jwt: string // Original encoded token to be passed to ESI
	token: EsiToken // Decoded token to be used on the service
	capsuleerId: number // Extracted capsuleer id
}
declare namespace CapsuleerAssetsUseCase {
	export type Request = AssetsUseCaseInput
	export type Response = Promise<V1AssetDto>
}
@Injectable()
export class CapsuleerAssetsUseCase {
	constructor(private readonly esiSecureAdapter: V1ESISecureDataAdapter) {}

	async getAssets(esiSecureData: EsiSecureData): Promise<Assets> {
		return this.esiSecureAdapter.getAssets(esiSecureData)
	}
}
