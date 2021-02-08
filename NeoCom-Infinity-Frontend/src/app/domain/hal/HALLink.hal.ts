// - CORE
import { Observable } from 'rxjs'
// - DOMAIN
import { HALResolver } from '@app/services/HALResolver.service'
import { ObjectIterateeCustom } from 'cypress/types/lodash'

export class HALLink<T> {
    private downloaded: boolean = false
    private rel: string
    private href: string
    private factory: any // This is the actory constructor for T instances
    public target: T // The Link when resolved. If undefined then the link is pending resolution

    constructor(targetType: { new(values: Object): T }) {
        this.factory = targetType
    }
    public setContents(contents: Object): HALLink<T> {
        this.rel = contents['rel']
        this.href = contents['href']
        return this
    }
    public typeCast(values: any): T {
        this.target = new this.factory(values)
        this.downloaded = true
        return this.target
    }
    /** @deprecated */
    public access(resolver: HALResolver): Promise<T> {
        return null
        // if (this.isDownloaded())
        //     return new Observable<T>(subscriber => {
        //         subscriber.next(this.target)
        //         subscriber.complete()
        //     }).toPromise()
        // else {
        //     return new Observable<T>(subscriber => {
        //         if (null != resolver)
        //             resolver.resolve(this.href)
        //                 .subscribe((entrydata: any): void => {
        //                     this.target = entrydata
        //                     this.downloaded = true
        //                     subscriber.next(this.target)
        //                     subscriber.complete();
        //                 })
        //         else subscriber.next(this.target)
        //     }).toPromise()
        // }
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
    public getTarget(): T {
        return this.target
    }
}
