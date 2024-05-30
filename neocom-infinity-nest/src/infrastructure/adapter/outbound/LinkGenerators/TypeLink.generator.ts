import { ESI_CONSTANTS } from '@Infra/config/GlobalConstants'
import { IGenerator } from 'neocom-domain'

export class TypeLinkGenerator implements IGenerator {
	generate(identifier: string | number): string {
		return ESI_CONSTANTS.BACKEND_ESI_V3_PREFIX + ESI_CONSTANTS.BACKEND_ESI_TYPE + identifier
	}
}
