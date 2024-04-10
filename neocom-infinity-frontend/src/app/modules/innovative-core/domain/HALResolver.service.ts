// - CORE
import { Injectable } from '@angular/core'
import { map } from 'rxjs/operators'
import { HttpClient } from '@angular/common/http'
import { HttpErrorResponse } from '@angular/common/http'
import { HttpHeaders } from '@angular/common/http'
import { HALNode } from '@domain/hal/HALNode.hal'
import { Observable } from 'rxjs'
import { environment } from '@env/environment'
import { HALLinkArray } from '@domain/hal/HALLinkArray.hal'
import { HALLink } from '@innovative/domain/HALLink.domain'
import { PublicService } from '@app/services/public.service'

@Injectable({
    providedIn: 'root'
})
export class HALResolverService {
    constructor(protected publicService: PublicService) { }
    public resolve<T>(link: HALLink<T>, typeRef: T): Promise<T> {
        console.log('>[HALResolver.resolve]>Link: ' + link.getHref())
        return this.publicService.apiv1_GetUniverseLink(link.getHref(), typeRef)
    }
}
