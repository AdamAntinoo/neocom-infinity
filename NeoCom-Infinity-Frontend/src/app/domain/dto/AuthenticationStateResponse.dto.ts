import { NeocomCredential } from "@domain/core/Credential.domain";

export class AuthenticationStateResponse {
    public state: string = 'NOT_VALID'
    private jwtToken: string
    private credential: NeocomCredential

    constructor(values: Object = {}) {
        Object.assign(this, values);
        if (this.credential) this.credential = new NeocomCredential(this.credential)
    }

    public getJwtToken(): string {
        return this.jwtToken
    }
    public getCredential(): NeocomCredential {
        return this.credential
    }
}
