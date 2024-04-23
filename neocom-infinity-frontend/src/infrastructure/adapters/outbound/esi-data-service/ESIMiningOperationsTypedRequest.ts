import { HttpHeaders } from "@angular/common/http"

import { BACKEND_LINKS } from "@adapter/outbound/configuration/GlobalConstants"
import { TypedRequest } from "neocom-domain"


declare namespace ESIMiningOperationsTypedRequest {
    export type Request = number
}

export class ESIMiningOperationsTypedRequest implements TypedRequest {
    public method: string = 'GET'
    public request: string
    public options: {}

    constructor() {
        this.options = {
            headers: new HttpHeaders()
        }
    }

    public prepare(requestInput: ESIMiningOperationsTypedRequest.Request) {
        console.log('prepare->parameters->' + requestInput)
        this.request = BACKEND_LINKS.BACKEND_MINING_OPERATIONS_PREFIX + requestInput + BACKEND_LINKS.BACKEND_MINING_OPERATIONS_OPERATION
        console.log('prepare->request->' + this.request)
        return this
    }

}
