import { createParamDecorator, ExecutionContext, Logger } from '@nestjs/common'

export const Cookies = createParamDecorator((data: string, ctx: ExecutionContext) => {
	const request = ctx.switchToHttp().getRequest()
	const logger = new Logger('RequestLogging')
	logger.debug('>>> cookies->' + JSON.stringify(request.cookies))
	return data ? request.cookies?.[data] : request.cookies
})
