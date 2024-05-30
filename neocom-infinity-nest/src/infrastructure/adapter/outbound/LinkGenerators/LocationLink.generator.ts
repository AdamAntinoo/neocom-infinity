import { ESI_CONSTANTS } from '@Infra/config/GlobalConstants'
import { IGenerator } from 'neocom-domain'

export class LocationLinkGenerator implements IGenerator {
	generate(identifier: string | number): string {
		return ESI_CONSTANTS.BACKEND_ESI_V1_PREFIX + ESI_CONSTANTS.BACKEND_ESI_SPACE_LOCATION + identifier
	}
}
