import { AuthenticationTokenValidator } from '@Infra/adapter/inbound/validator/AuthenticationTokenValidator'
import { createParamDecorator, ExecutionContext, Logger } from '@nestjs/common'

export const ValidateToken = createParamDecorator((data: string, ctx: ExecutionContext) => {
	const request = ctx.switchToHttp().getRequest()
	const jwtService = ctx.
	const logger = new Logger('RequestLogging')
	logger.debug('>>> ValidateToken->' + JSON.stringify(request.headers))
	const validator : AuthenticationTokenValidator =new AuthenticationTokenValidator(this.jwtService).validate(token))
	return data ? request.headers?.[data] : request.headers
})
