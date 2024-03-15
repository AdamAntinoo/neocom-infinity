export abstract class IEsiSecureService<T> {
    abstract getMiningOperations(characterId: number): Promise<T[]>
}
