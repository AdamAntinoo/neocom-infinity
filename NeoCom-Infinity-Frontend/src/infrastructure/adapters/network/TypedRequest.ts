export interface TypedRequest {
    method: string
    request: string
    options: object

    prepare(parameters: any): TypedRequest
}
