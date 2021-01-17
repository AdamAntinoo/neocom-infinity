// - DOMAIN
import { NeoCom } from '@app/domain/NeoCom.domain';

export class NeoComException extends NeoCom {
    public status: number = 0;
    public statusText: string = 'Undefined Exception';
    public allowsRetry: boolean = false;
    public code: string
    public title: string
    public message: string = 'Request undefined exception:0:Undefined Exception.';
    public userMessage: string;
    public cause: string

    constructor(values: Object = {}) {
        super();
        Object.assign(this, values);
    }

    // - B U I L D E R
    public withCode(code: string): NeoComException {
        if (null != code) this.code = code
        return this
    }
    public withTitle(title: string): NeoComException {
        if (null != title) this.title = title
        return this
    }
    public withMessage(message: string): NeoComException {
        if (null != message) this.message = message
        return this
    }
    public withCause(cause: string): NeoComException {
        if (null != cause) this.cause = cause
        return this
    }

    // - G E T T E R S
    /** If the user message is not defined the we can expect to use the exception message. */
    public getUserMessage(): string {
        if (this.isEmpty(this.userMessage)) return this.message;
        else return this.userMessage;
    }
    public setUserMessage(message: string): NeoComException {
        this.userMessage = message;
        return this;
    }
    public setRetryable(retry: boolean): NeoComException {
        this.allowsRetry = retry;
        return this;
    }
}
