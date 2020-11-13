// - CORE
import { Observable } from 'rxjs'
// - DOMAIN
import { HALResolver } from '@app/services/HALResolver.service'

export class HALLink<T> {
    public isDownloaded: boolean = false
    public rel: string
    public href: string
    public target: T

    constructor(values: Object = {}) {
        Object.assign(this, values)
    }

    public access(resolver: HALResolver): Promise<T | any> {
        if (this.isDownloaded)
            return new Observable(subscriber => {
                subscriber.next(this.target)
                subscriber.complete()
            }).toPromise()
        else {
            return new Observable(subscriber => {
                if (null != resolver)
                    resolver.resolve(this.href)
                        .subscribe((entrydata: any): void => {
                            this.target = entrydata
                            this.isDownloaded = true
                            subscriber.next(this.target)
                            subscriber.complete();
                        })
                else subscriber.next(this.target)
            }).toPromise()
        }
    }
}
