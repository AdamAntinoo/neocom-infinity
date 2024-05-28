import { Logger } from "@nestjs/common"
import { AxiosRequestConfig } from "axios"
import { ITypedRequest } from "./ITypedRequest.interface"

export abstract class BaseTypedRequest implements ITypedRequest {
	protected readonly logger = new Logger("BaseTypedRequest")
	public method: string = "GET"
	public requestAge: number = 600
	public request?: string
	public options?: AxiosRequestConfig

	protected abstract prepareRequest(): void
	protected abstract prepareOptions(): void

	protected prepare(): void {
		this.prepareRequest()
		this.prepareOptions()
	}
}
