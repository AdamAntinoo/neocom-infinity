import { AuthenticationServicesPort } from "@App/ports/AuthenticationServices.port"
import { ITokenAuthentication } from "@App/ports/ITokenAuthentication.port"
import { Injectable } from "@nestjs/common"
import { Option, Some, None } from '@sniptt/monads'

@Injectable()
export class AuthenticationServicesAdapter implements AuthenticationServicesPort {
   public authentication: ITokenAuthentication

    constructor() {
        this.authentication = new TokenAuthenticationService()
    }
}

@Injectable()
export class TokenAuthenticationService implements ITokenAuthentication {
    private tokenAvailable: boolean = false
    private token: string

    public validateToken(token: string): boolean {
        console.log('token->' + token)
        this.token = token
        // TODO - Do the raeal token validation here
        this.tokenAvailable=true
        return true
    }
    public checkCapsuleer(capsuleerId: number): boolean {
        console.log('capsuleerId->' + capsuleerId)
        return true
    }
    public getCurrentToken(): Option<string> {
        if (this.tokenAvailable)
            return Some(this.token)
        else
            return None
    }
}
