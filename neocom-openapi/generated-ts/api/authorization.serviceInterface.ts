/**
 * NeoCom Backend API
 * New NeoCom Infinity backend API. This is the complete API implementation for the backend services exposed, whatever the backend server that will provide the service. Initial implementations used the Java backend server and the ESI Public Data Services as a complementary source. New implementations have a dual backend service, part of it the original Java SpringBoot services and a new NestJS Typescript service that will implement new services and also hide the ESI Public sources into a HyperLink like DTO definitions. <br> Different api endpoints will be isolated with a version prefix so even they are all defined under the same path root the different version will be used by routers and proxies to fire the request to the correct backend implementation. 
 *
 * The version of the OpenAPI document: 0.27.0
 * Contact: adamantinoo.git@gmail.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

import { Observable }                                        from 'rxjs';

import { BackendErrorDto } from '../model/backendError.dto';
import { RefreshTokenResponseDto } from '../model/refreshTokenResponse.dto';


import { Configuration }                                     from '../configuration';


export interface AuthorizationServiceInterface {
    defaultHeaders: {};
    configuration: Configuration;

    /**
    * Get a new updated authorization token from Esi.
    * Get a new updated authorization token from Esi. Use the current Credential refresh token to access ESI and get a new valid and not expired authorization token.  Update the new token on the corresponding Credential and also update the NeoCom cookie on server response.  New token will last for another 15 minutes. 
    * @param nEOCOMTOKEN The access token to be used for authorization. This token will contain information to locate the credential to be used for data location.
    */
    refreshAuthorizationToken(nEOCOMTOKEN: string, extraHttpRequestParams?: any): Observable<RefreshTokenResponseDto>;

}
