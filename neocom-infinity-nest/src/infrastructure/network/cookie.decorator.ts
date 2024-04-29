import { createParamDecorator, ExecutionContext } from '@nestjs/common'

export const Cookies = createParamDecorator((data: string, ctx: ExecutionContext) => {
    const request = ctx.switchToHttp().getRequest()
    console.log('cookies->' + JSON.stringify(request.cookies))
    return data ? request.cookies?.[data] : request.cookies
})
