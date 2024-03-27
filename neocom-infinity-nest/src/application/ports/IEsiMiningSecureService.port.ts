export abstract class IEsiMiningSecureService<T> {
    public abstract getMiningOperations(characterId: number): Promise<T[]>
}
