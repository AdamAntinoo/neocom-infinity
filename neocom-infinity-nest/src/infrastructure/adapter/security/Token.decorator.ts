import { AuthenticationTokenValidator } from '@Infra/adapter/inbound/validator/AuthenticationTokenValidator'
import { createParamDecorator, ExecutionContext, Logger } from '@nestjs/common'
import { EsiToken } from './EsiToken.interface'

export const ValidateToken = createParamDecorator((data: string, ctx: ExecutionContext) => {
	const request = ctx.switchToHttp().getRequest()
	// TODO - Implement a locator for the jwt decoder.
	const jwtService = ctx.switchToHttp().getRequest()
	const logger = new Logger('RequestLogging')
	logger.debug('>>> ValidateToken->' + JSON.stringify(request.headers))
	const token: string = 'token'
	const validator: EsiToken = new AuthenticationTokenValidator(jwtService).validate(token)
	return data ? request.headers?.[data] : request.headers
})
