export abstract class IEsiMiningSecureService<T> {
    abstract getMiningOperations(characterId: number): Promise<T[]>
}
