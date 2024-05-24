/**
 * NeoCom Backend API
 * New NeoCom Infinity backend API. This is the complete API implementation for the backend services exposed, whatever the backend server that will provide the service. Initial implementations used the Java backend server and the ESI Public Data Services as a complementary source. New implementations have a dual backend service, part of it the original Java SpringBoot services and a new NestJS Typescript service that will implement new services and also hide the ESI Public sources into a HyperLink like DTO definitions. <br> Different api endpoints will be isolated with a version prefix so even they are all defined under the same path root the different version will be used by routers and proxies to fire the request to the correct backend implementation. 
 *
 * The version of the OpenAPI document: 0.27.1
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
import { BackendErrorDto } from '../model/backendError.dto';
import { V1MiningOperationDto } from '../model/v1MiningOperation.dto';
import { Configuration } from '../configuration';
import { IndustryServiceInterface } from './industry.serviceInterface';


@Injectable()
export class IndustryService implements IndustryServiceInterface {

    protected basePath = 'http://localhost';
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
     * Get the minings operations for current target character.
     * Gets the list of Esi Mining Operations that are generated automatically during mining. The target to be used is the capsuleer identifier or corporation identifier that is found on the access token. &lt;br&gt; The list  of operations is transformed to a hyperlink suitable frontend interpretation and operation items are given a unique key for easy identification of changes. &lt;br&gt; There is no persistence for this kind of data. 
     * @param nEOCOMTOKEN The access token to be used for authorization. This token will contain information to locate the credential to be used for data location.
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getMiningOperations(nEOCOMTOKEN: string, ): Observable<AxiosResponse<Array<V1MiningOperationDto>>>;
    public getMiningOperations(nEOCOMTOKEN: string, ): Observable<any> {

        if (nEOCOMTOKEN === null || nEOCOMTOKEN === undefined) {
            throw new Error('Required parameter nEOCOMTOKEN was null or undefined when calling getMiningOperations.');
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
        return this.httpClient.get<Array<V1MiningOperationDto>>(`${this.basePath}/api/v3/neocom/character/miningoperations`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers
            }
        );
    }
}
