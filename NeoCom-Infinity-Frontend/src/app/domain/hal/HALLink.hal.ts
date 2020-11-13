// - CORE
import { Observable } from 'rxjs'
// - DOMAIN
import { HALResolver } from '@app/services/HALResolver.service'
import { NeoCom } from '@domain/NeoCom.domain'

export class HALLink<T> /*extends NeoCom*/ {
    public isDownloaded: boolean = false
    public rel: string
    public href: string
    public target: T

    // public resolve(resolver: HALResolver): Observable<T> {
    //     return resolver.resolve(this.href) as Observable<T>
    // }
    public access(resolver: HALResolver): Observable<T> {
        if (this.isDownloaded)
            return new Observable((observer) => {
                observer.next(this.target)
                observer.complete()
            })
        else {
            return resolver.resolve(this.href) as Observable<T>
        }
    }
}
