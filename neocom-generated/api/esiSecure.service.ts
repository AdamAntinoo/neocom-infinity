/**
 * NeoCom Infinity - NestJS Backend Api v.0.20.0
 * This is the Rest Api definition for the NeoCom Infinity Backend services. This new set of services are being developed with NestJs that is a Typescript backend framework code compatible with the Angular Frontend.  The new backend starts at version 0.20.0 for the new Infinity feature set. The server will export the Api endpoints to Access some of the Esi Data Source CCP published endpoints and on some cases it will expand Esi features and enrich data.
 *
 * The version of the OpenAPI document: 0.21.0
 * Contact: adamantinoo.git@gmail.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
/* tslint:disable:no-unused-variable member-ordering */

import { Inject, Injectable, Optional } from '@nestjs/common';
import { HttpService } from '@nestjs/axios';
import { AxiosResponse } from 'axios';
import { Observable } from 'rxjs';
import { ErrorResponse } from '../model/errorResponse';
import { EsiTypeDto } from '../model/esiTypeDto';
import { MiningOperation } from '../model/miningOperation';
import { Configuration } from '../configuration';


@Injectable()
export class EsiSecureService {

    protected basePath = 'https://neocom-infinity.io:5257/status';
    public defaultHeaders: Record<string,string> = {};
    public configuration = new Configuration();

    constructor(protected httpClient: HttpService, @Optional() configuration: Configuration) {
        this.configuration = configuration || this.configuration;
        this.basePath = configuration?.basePath || this.basePath;
    }

    /**
     * @param consumes string[] mime-types
     * @return true: consumes contains 'multipart/form-data', false: otherwise
     */
    private canConsumeForm(consumes: string[]): boolean {
        const form = 'multipart/form-data';
        return consumes.includes(form);
    }

    /**
     * Get the main information related to an Eve type from the ESI data source.
     * Instead using 3 endpoints directly to the ESI Data Service, encapsulate group and category info into a single cached request to the new backend. 
     * @param typeId The unique ESI type identifier.
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public esiGetTypeInformation(typeId: number, ): Observable<AxiosResponse<Array<EsiTypeDto>>>;
    public esiGetTypeInformation(typeId: number, ): Observable<any> {

        if (typeId === null || typeId === undefined) {
            throw new Error('Required parameter typeId was null or undefined when calling esiGetTypeInformation.');
        }

        let headers = {...this.defaultHeaders};

        // authentication (neocom_esi_auth) required
        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers['Accept'] = httpHeaderAcceptSelected;
        }

        // to determine the Content-Type header
        const consumes: string[] = [
        ];
        return this.httpClient.get<Array<EsiTypeDto>>(`${this.basePath}/esi/v1/universe/types/${encodeURIComponent(String(typeId))}`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers
            }
        );
    }
    /**
     * Get the minings operations for current target.
     * Gets the list of Esi Mining Operations that are generated automatically during mining. The target to be used is the capsuleer identifier or corporation identifier that is found on the authentication token. &lt;br&gt; The list  of operations is transformed to a hyperlink suitable frontend interpretation and operation items are given a unique key for easy identification of changes. &lt;br&gt; There is no persistence for this kind of data. 
     * @param token Authentication token generated at Esi service and used for Esi data access endpoints.
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getMiningOperations(token: string, ): Observable<AxiosResponse<Array<MiningOperation>>>;
    public getMiningOperations(token: string, ): Observable<any> {

        if (token === null || token === undefined) {
            throw new Error('Required parameter token was null or undefined when calling getMiningOperations.');
        }

        let headers = {...this.defaultHeaders};

        // authentication (neocom_esi_auth) required
        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers['Accept'] = httpHeaderAcceptSelected;
        }

        // to determine the Content-Type header
        const consumes: string[] = [
        ];
        return this.httpClient.get<Array<MiningOperation>>(`${this.basePath}/nin/v1/character/miningoperations`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers
            }
        );
    }
}
