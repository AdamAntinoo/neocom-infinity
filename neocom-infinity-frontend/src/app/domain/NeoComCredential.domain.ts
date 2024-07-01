export class NeoComCredential {
    private uniqueCredential: string
    private accountId: number = -1
    private accountName: string
    private dataSource: string = 'tranquility'
    private corporationId: number = -1
    private assetsCount: number = 0
    private walletBalance: number = 0
    private miningResourcesEstimatedValue: number = 0
    private raceName: string

    constructor(values: Object = {}) {
        // super();
        Object.assign(this, values)
        // this.jsonClass = 'Credential';
    }

    // -  GETTERS & SETTERS
    public getAccountId(): number {
        return this.accountId
    }
    public getAccountName(): string {
        return this.accountName
    }
    public getUniqueId(): string {
        return this.uniqueCredential
    }
    public getRaceName(): string {
        if (null != this.raceName) return this.raceName
        else return '-UNDETERMINED-'
    }
    public getCorporationId(): number {
        return this.corporationId
    }
}
