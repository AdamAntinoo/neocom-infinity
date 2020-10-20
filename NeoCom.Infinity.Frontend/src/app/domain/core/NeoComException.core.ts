export class NeoComException {
    public code: string
    public title: string
    public message: string
    public cause: string

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
}
