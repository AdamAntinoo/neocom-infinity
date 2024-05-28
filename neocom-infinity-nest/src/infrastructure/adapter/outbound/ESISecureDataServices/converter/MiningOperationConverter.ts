import { IConverter } from 'neocom-domain/dist/converter/IConverter.interface'
import { GetCharactersCharacterIdMining200Ok, V1StackDto } from 'neocom-domain'

export class MiningOperationConverter<S, D> implements IConverter<GetCharactersCharacterIdMining200Ok, V1StackDto> {
	public convert(source: GetCharactersCharacterIdMining200Ok): V1StackDto {
		let type: string = undefined
		if (source.type_id) {
			type = this.getTypeLink(source.type_id)
		}
		return new V1StackDto({
			quantity: source.quantity,
			typeLink: type,
		})
	}
	public getTypeLink(typeId: number): string {
		return '/esi/v1/universe/types/' + typeId
	}
}
