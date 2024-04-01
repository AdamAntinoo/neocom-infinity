import { AuthenticationServicesPort } from "@App/ports/AuthenticationServices.port"
import { ITokenAuthentication } from "@App/ports/ITokenAuthentication.port"
import { Injectable } from "@nestjs/common"
import { JwtService } from "@nestjs/jwt"
import { Option, Some, None } from '@sniptt/monads'

@Injectable()
export class AuthenticationServicesAdapter implements AuthenticationServicesPort {
    public authentication: ITokenAuthentication

    constructor(private readonly jwtService: JwtService) {
        this.authentication = new TokenAuthenticationService(jwtService)
    }
}

@Injectable()
export class TokenAuthenticationService implements ITokenAuthentication {
    private token: Option<any> = None

    constructor(private readonly jwtService: JwtService) { }

    public validateToken(token: string): boolean {
        if (token.startsWith('Bearer ')) {
            const decodedToken: string = this.jwtService.decode(this.extractToken(token))
            console.log('decodedToken->' + decodedToken)
            if (undefined != decodedToken) {
                this.token = Some(decodedToken)
                // TODO - Do the real token validation here
                const token = this.token.unwrap()
                if (token['kid'] != 'JWT-Signature-Key') return false

                return true
            }
        }
        else return false
    }
    public checkCapsuleer(capsuleerId: number): boolean {
        console.log('capsuleerId->' + capsuleerId)
        console.log('jwtId->' + this.token.unwrap()['sub'])
        const jwtCharacterField: string = this.token.unwrap()['sub']
        const id: number = Number(jwtCharacterField.split(':')[2])
        if (id == capsuleerId) return true
        else return false
    }
    public getCurrentToken(): Option<any> {
        return this.token
    }
    private extractToken(token: string): string {
        return token.split(' ')[1]
    }
}
