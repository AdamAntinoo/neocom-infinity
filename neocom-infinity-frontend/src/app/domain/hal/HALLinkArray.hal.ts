export class HALLinkArray<T> {
    private downloaded: boolean = false
    private rel: string
    private href: string
    private factory: any // This is the factory constructor for T instances
    public target: T[] // The Link when resolved. If empty then the link is pending resolution

    constructor(targetType: { new(values: Object): T }) {
        this.factory = targetType
    }

    public setContents(contents: Object): HALLinkArray<T> {
        this.rel = contents['rel']
        this.href = contents['href']
        return this
    }
    public typeCast(values: any[]): T[] {
        this.target = []
        for (let record of values)
            this.target.push(new this.factory(record))
        this.downloaded = true
        return this.target
    }
    // - G E T T E R S   &   S E T T E R S
    public isResolved(): boolean {
        return this.downloaded
    }
    public isDownloaded(): boolean {
        return this.downloaded
    }
    public getRelation(): string {
        return this.rel
    }
    public getHref(): string {
        return this.href
    }
    public getTarget(): T[] {
        return this.target
    }
}
