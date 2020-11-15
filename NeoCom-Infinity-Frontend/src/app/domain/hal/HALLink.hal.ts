// - CORE
import { Observable } from 'rxjs'
// - DOMAIN
import { HALResolver } from '@app/services/HALResolver.service'

export class HALLink<T> {
    private downloaded: boolean = false
    private rel: string
    private href: string
    public target: T

    constructor(values: Object = {}) {
        Object.assign(this, values)
    }

    public access(resolver: HALResolver): Promise<T> {
        if (this.isDownloaded())
            return new Observable<T>(subscriber => {
                subscriber.next(this.target)
                subscriber.complete()
            }).toPromise()
        else {
            return new Observable<T>(subscriber => {
                if (null != resolver)
                    resolver.resolve(this.href)
                        .subscribe((entrydata: any): void => {
                            this.target = entrydata
                            this.downloaded = true
                            subscriber.next(this.target)
                            subscriber.complete();
                        })
                else subscriber.next(this.target)
            }).toPromise()
        }
    }
    // - G E T T E R S   &   S E T T E R S
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
