import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { UnsecuredProxyPort } from "@app/ports/UnsecuredProxy.port";
import { NeoComException } from "@innovative/domain/NeoComException";
import { Observable, firstValueFrom } from "rxjs";

/**
 * Make HTTP calls to unsecured external services. Connections are for:
 * <ul>
 * <li>* v1 -> old java baclend services.</li>
 * <li>* v3 -> new nest typescript services.</li>
 * </ul>
 */
@Injectable({
    providedIn: 'root'
})
export class UnsecuredProxy implements UnsecuredProxyPort {

    constructor(private readonly httpClient: HttpClient) { }

    public apiv3_GetUnsecuredLink<T>(link: string): Promise<T> {
        if (undefined != link) {
            console.log(">[UnsecuredProxy.apiv3_GetUnsecuredLink]> link: " + link)
            const newheaders = new HttpHeaders()
            return firstValueFrom(
                this.httpClient.get(link, { headers: newheaders }) as Observable<T>
            )
        }
        else throw new NeoComException()
            .withTitle('Invalid HAL link.')
            .withMessage('Invalid HAL link for type ' + 'T')
    }
}
