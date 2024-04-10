// - CORE
import { Observable } from 'rxjs';
// - DOMAIN
import { HALResolver } from '@app/services/HALResolver.service';
import { NeoCom } from '@domain/NeoCom.domain';

export class HALNode extends NeoCom {
    private resolver: HALResolver

    public getResolver(): HALResolver {
        return this.resolver
    }
    public setResolver(newResolver: HALResolver): void {
        if (null != newResolver) this.resolver = newResolver
    }
    /** DEPRECATED */
    public resolve(link: string): Observable<any> {
        return null
    }
}
