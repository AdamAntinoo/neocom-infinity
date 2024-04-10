import { NeoComErrorDefinition } from "./NeoComErrorCatalog"

export class NeoComError extends Error {
    public httpCode: number
    public code: string

    public static Builder  = class Builder {
        public error: NeoComError

        constructor(error: NeoComErrorDefinition) {
            this.error = new NeoComError()
            this.error.httpCode = error.httpCode
            this.error.code = error.code
            this.error.message = error.messagePattern
        }
        public build(): NeoComError {
            return this.error
        }
    }
}
