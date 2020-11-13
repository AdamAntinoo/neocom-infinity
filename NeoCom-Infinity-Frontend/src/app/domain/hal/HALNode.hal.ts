// - CORE
import { Observable } from 'rxjs';
// - DOMAIN
import { HALResolver } from '@app/services/HALResolver.service';
import { ResponseTransformer } from '@app/services/support/ResponseTransformer';
import { NeoCom } from '@domain/NeoCom.domain';

export class HALNode extends NeoCom {
    private resolver: HALResolver

    public getResolver(): HALResolver {
        return this.resolver
    }
    public setResolver(newResolver: HALResolver): void {
        if (null != newResolver) this.resolver = newResolver
    }
    public resolve(link: string): Observable<any> {
        return this.resolver.resolve(link)
    }
}
