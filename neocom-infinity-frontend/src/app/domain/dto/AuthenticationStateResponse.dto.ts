import { NeocomCredential } from "@domain/core/Credential.domain";

export class AuthenticationStateResponse {
    public state: string = 'NOT_VALID'
    private jwtToken: string
    private esiToken: string
    private credential: NeocomCredential

    constructor(values: Object = {}) {
        Object.assign(this, values)
        // This is a good way to convert received json to internal Typescript instances.
        if (this.credential) this.credential = new NeocomCredential(this.credential)
    }

    public getJwtToken(): string {
        return this.jwtToken
    }
    public getEsiToken(): string {
        return this.esiToken
    }
    public getCredential(): NeocomCredential {
        return this.credential
    }
}
