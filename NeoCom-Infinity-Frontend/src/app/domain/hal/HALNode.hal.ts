// - CORE
import { Observable } from 'rxjs';
// - DOMAIN
import { HALResolver } from '@app/services/HALResolver.service';
import { ResponseTransformer } from '@app/services/support/ResponseTransformer';

export class HALNode {
    private resolver: HALResolver

    public setResolver(newResolver: HALResolver): void {
        if (null != newResolver) this.resolver = newResolver
    }
    public resolve(link: string): Observable<any> {
        return this.resolver.resolve(link)
    }
}
