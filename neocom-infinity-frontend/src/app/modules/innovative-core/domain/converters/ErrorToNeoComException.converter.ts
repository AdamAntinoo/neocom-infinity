// - DOMAIN
import { HttpErrorResponse } from "@angular/common/http";
import { NeoComException } from "../NeoComException";

export class ErrorToNeoComExceptionConverter {
    public convert(input: HttpErrorResponse): NeoComException {
        return new NeoComException()
            .withCode('HTTP-ERROR')
            .withTitle(input.error.errorName)
            .withMessage(input.error.message)
            .withCause(input.error.cause)
    }
}
