export interface INode {
    jsonClass: string
    getJsonClass(): string
    decode(): void
    identify(): string
}
