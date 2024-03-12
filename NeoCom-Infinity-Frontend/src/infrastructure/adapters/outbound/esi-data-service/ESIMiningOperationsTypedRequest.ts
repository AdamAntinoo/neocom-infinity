import { ConfigurationPort } from "@app/ports/ConfigurationPort"
import { TypedRequest } from "../../network/TypedRequest"
import { ConfigurationAdapter } from "../../inbound/configuration/ConfigurationAdapter"
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
            // headers: new HttpHeaders({
            //     'Accept': '*/*',
            //     'Content-Type': 'application/json',
            //     'Cache-Control':'no-cache'
            // }),
            observe: 'response',
            responseType: 'json'
        }
    }

    public prepare(parameters: ESIMiningOperationsTypedRequest.Request) {
        console.log('prepare->parameters->' + parameters)
        this.request = this.configuration.getNestBackendHost() + '/characters/' + parameters + '/miningoperation'
        return this
    }

}
