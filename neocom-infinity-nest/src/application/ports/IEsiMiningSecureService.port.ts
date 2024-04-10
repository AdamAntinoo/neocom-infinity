export abstract class IEsiMiningSecureService<T> {
    public abstract getMiningOperations(characterId: number,jwt:string): Promise<T[]>
}
