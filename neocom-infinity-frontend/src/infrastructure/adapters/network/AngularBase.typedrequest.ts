export abstract class AngularBaseTypedRequest {
	public method: string = "GET"
	public requestAge: number = 600
	public request?: string
	public options?: any

	protected abstract prepareRequest(): void
	protected abstract prepareOptions(): void
	protected abstract prepareBody(): void

	protected prepare(): void {
		this.prepareRequest()
		this.prepareOptions()
        this.prepareBody()
	}
}
