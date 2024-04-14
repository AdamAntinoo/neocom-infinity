import { TypedRequest } from "neocom-domain/TypedRequest"
import { ConfigurationAdapter } from "../configuration/ConfigurationAdapter"
import { HttpHeaders } from "@angular/common/http"


declare namespace ESIMiningOperationsTypedRequest {
    export type Request = number
}

export class ESIMiningOperationsTypedRequest implements TypedRequest {
    public method: string = 'GET'
    public request: string
    public options: {}

    constructor(private configuration: ConfigurationAdapter) {
        console.log('ESIMiningOperationsTypedRequest->' + JSON.stringify(configuration))
        this.options = {
            headers: new HttpHeaders()
        }
    }

    public prepare(requestInput: ESIMiningOperationsTypedRequest.Request) {
        console.log('prepare->parameters->' + requestInput)
        this.request = this.configuration.getNestBackendHost() + '/characters/' + requestInput + '/miningoperation'
        return this
    }

}
