export class NeoComErrorDefinition {
    public id: string
    public httpCode: number
    public code: string
    public messagePattern: string

    constructor(values: object = {}) {
        Object.assign(this, values)
    }
}
export const NeoComErrorCatalog: NeoComErrorDefinition[] = [
    new NeoComErrorDefinition({
        id: "TOKEN-NOT-PRESENT",
        httpCode: 401,
        code: "TOKEN-NOT-PRESENT",
        messagePattern: "The authentication token is not present or invalid so the request is not authorized."
    })
]
export const TOKEN_NOT_PRESENT:NeoComErrorDefinition=new NeoComErrorDefinition({
    httpCode: 401,
    code: "TOKEN-NOT-PRESENT",
    messagePattern: "The authentication token is not present or invalid so the request is not authorized."
})
export const TOKEN_INVALID:NeoComErrorDefinition=new NeoComErrorDefinition({
    httpCode: 401,
    code: "TOKEN-INVALID",
    messagePattern: "The authentication token is not valid because does not declare a correct subject."
})
