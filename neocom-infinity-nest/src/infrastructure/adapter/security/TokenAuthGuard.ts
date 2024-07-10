import { AuthenticationTokenValidator } from '@Infra/adapter/inbound/validator/AuthenticationTokenValidator'
import { Injectable, CanActivate, ExecutionContext } from '@nestjs/common'
import { JwtService } from '@nestjs/jwt'

@Injectable()
export class TokenAuthGuard implements CanActivate {
	constructor(private readonly jwtService: JwtService) {}

	async canActivate(context: ExecutionContext): Promise<boolean> {
		const request = context.switchToHttp().getRequest()
		const token = request.header.Authorization
		try {
			new AuthenticationTokenValidator(this.jwtService).validate(token)
		} catch (error) {
			return false
		}
		return true
	}
}
