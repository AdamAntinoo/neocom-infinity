import { JwtService } from "@nestjs/jwt"
import { TOKEN_INVALID, TOKEN_NOT_PRESENT } from "application/domain/NeoComErrorCatalog"
import { NeoComError } from "application/domain/NeocomError"
import { EsiToken } from "../EsiToken.interface"

export class AuthenticationTokenValidator {
    constructor(private readonly jwtService: JwtService) { }

    public validate(token: string): EsiToken {
        if (token.startsWith('Bearer ')) {
            const decodedToken: EsiToken = this.jwtService.decode(this.extractToken(token)) as EsiToken
            console.log('decodedToken->' + JSON.stringify(decodedToken))
            if (undefined === decodedToken) throw new NeoComError.Builder(TOKEN_NOT_PRESENT).build()
            if (!this.validateSub(decodedToken)) throw new NeoComError.Builder(TOKEN_INVALID).build()
            // const capsuleerId = this.extractCapsuleerId ( decodedToken.sub)
            return decodedToken
        }
        else throw new NeoComError.Builder(TOKEN_NOT_PRESENT).build()
    }
    /** Remove the 'Bearer ' from the token header contents and extract the jwt token. */
    private extractToken(token: string): string {
        return token.split(' ')[1]
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
