import { AxiosRequestConfig } from "axios"

export interface ITypedRequest {
	method: string
	requestAge: number
	request: string
	options: AxiosRequestConfig

	prepareRequest(): void
	prepareOptions(): void
}
