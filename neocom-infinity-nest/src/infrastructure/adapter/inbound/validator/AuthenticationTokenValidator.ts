import { JwtService } from '@nestjs/jwt'
import { TOKEN_INVALID, TOKEN_NOT_PRESENT } from 'neocom-domain'
import { NeoComError } from 'neocom-domain'
import { EsiToken } from '../../../../application/domain/EsiToken.interface'

export class AuthenticationTokenValidator {
	constructor(private readonly jwtService: JwtService) {}

	public validate(token: string): EsiToken {
		if (undefined == token) throw new NeoComError.Builder(TOKEN_NOT_PRESENT).build()
		const decodedToken: EsiToken = this.jwtService.decode(token) as EsiToken
		if (null === decodedToken) throw new NeoComError.Builder(TOKEN_NOT_PRESENT).build()
		if (undefined === decodedToken) throw new NeoComError.Builder(TOKEN_NOT_PRESENT).build()
		if (!this.validateSub(decodedToken)) throw new NeoComError.Builder(TOKEN_INVALID).build()
		return decodedToken
	}
	private validateSub(token: EsiToken): boolean {
		if (undefined === token.sub) return false
		const id = this.extractCapsuleerId(token.sub)
		if (undefined === id) return false
		return true
	}
	private extractCapsuleerId(subject: string): number {
		const id: number = Number(subject.split(':')[2])
		return id
	}
}
