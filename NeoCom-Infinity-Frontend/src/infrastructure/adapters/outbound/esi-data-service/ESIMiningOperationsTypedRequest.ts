import { ConfigurationPort } from "@app/ports/ConfigurationPort"
import { TypedRequest } from "../../network/TypedRequest"
import { ConfigurationAdapter } from "../../inbound/configuration/ConfigurationAdapter"


declare namespace ESIMiningOperationsTypedRequest {
    export type Request = number
}

export class ESIMiningOperationsTypedRequest implements TypedRequest {
    public method: string = 'GET'
    public request: string
    public options: object

    constructor(private configuration: ConfigurationAdapter) {
        console.log('ESIMiningOperationsTypedRequest->' + JSON.stringify(configuration))
    }

    public prepare(parameters: ESIMiningOperationsTypedRequest.Request) {
        console.log('prepare-><')
        this.request = this.configuration.getNestBackendHost() + '/characters/' + parameters + '/miningoperation'
        return this
    }

}
