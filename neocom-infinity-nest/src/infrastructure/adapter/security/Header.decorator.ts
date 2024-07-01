import { createParamDecorator, ExecutionContext, Logger } from '@nestjs/common'

export const Headers = createParamDecorator((data: string, ctx: ExecutionContext) => {
	const request = ctx.switchToHttp().getRequest()
	const logger = new Logger('RequestLogging')
	logger.debug('>>> headers->' + JSON.stringify(request.headers))
	return data ? request.headers?.[data] : request.headers
})
