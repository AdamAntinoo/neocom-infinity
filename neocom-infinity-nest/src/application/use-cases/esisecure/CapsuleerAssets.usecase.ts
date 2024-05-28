import { ESIDataServicesPort } from '@App/ports/ESIDataServices.port'
import { EsiToken } from '@App/domain/EsiToken.interface'
import { Injectable } from '@nestjs/common'
import { GetCharactersCharacterIdAssets200Ok, V1StackDto } from 'neocom-domain'
import { Builder } from 'builder-pattern'
import { TypeLinkGenerator } from '@Infra/adapter/outbound/LinkGenerators/TypeLink.generator'
import { LocationLinkGenerator } from '@Infra/adapter/outbound/LinkGenerators/LocationLink.generator'
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

	public async getAssets(input: CapsuleerAssetsUseCaseInput): Promise<V1AssetDto[]> {
		const esiAssets: GetCharactersCharacterIdAssets200Ok[] = await this.esiSecureAdapter.getCharactersCharacterIdAssets(
			input.capsuleerId,
			input.jwt,
		)
		return this.convertAssets(esiAssets)
	}
	private convertAssets(esiAssets: GetCharactersCharacterIdAssets200Ok[]): Promise<V1AssetDto[]> {
		return new Promise<V1AssetDto[]>(resolve => {
			const transformedAssets: V1AssetDto[] = []
			for (const asset of esiAssets) {
				transformedAssets.push(
					Builder<V1AssetDto>()
						.id(asset.item_id)
						.resource(Builder<V1StackDto>().quantity(asset.quantity).typeLink(new TypeLinkGenerator().generate(asset.type_id)).build())
						.locationLink(new LocationLinkGenerator().generate(asset.location_id))
						.build(),
				)
			}
			resolve(transformedAssets)
		})
	}
}
