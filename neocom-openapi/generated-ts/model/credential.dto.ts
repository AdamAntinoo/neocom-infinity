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


export interface CredentialDto { 
    /**
     * The unique identifier for a credential entity instance.
     */
    uniqueCredential?: string;
    /**
     * The Eve Online account number.
     */
    accountId?: number;
    /**
     * The Eve Online account name identifier. Each Eve Online account can contain up to 3 characters with a single account identification.
     */
    accountName?: string;
    /**
     * The unique identifier for the Corporation on the Eve Online set of data. Initial game corporations are also on this list along user created corporations.
     */
    corporationId?: number;
    /**
     * The Eve Online data source for the ESI requests. It can differentiate between Production (tranquility server) and development and testing (singularity).
     */
    dataSource?: CredentialDto.DataSourceEnum;
    /**
     * The ESI Data Source authorization token received when creating a token with a set of i for ona of the characters related to aEve Online account.
     */
    accessToken?: string;
    /**
     * The token type. There is only one type of token nowadays.
     */
    tokenType?: string;
    /**
     * The list of authorized scoped from the set of ESI Data Source possible grant permissions.
     */
    scope?: string;
    /**
     * The refresh identifier token to be used to get a new access token once the current one is expired.
     */
    refreshToken?: string;
    /**
     * the number of assets for this character. This information is no longer required on the authorization credential.
     */
    assetsCount?: number;
    /**
     * the amount of ISKs for this credential character. This information is no longer required on the authorization credential.
     */
    walletBalance?: number;
    miningResourcesEstimatedValue?: number;
    raceName?: string;
    /**
     * The application JWT token to be used to isolate authorization from the frontend. This is no longer equired since we need the ESI authorization token to be available to other backend servers.
     */
    jwtoken?: string;
}
export namespace CredentialDto {
    export type DataSourceEnum = 'tranquility' | 'singularity';
    export const DataSourceEnum = {
        TRANQUILITY: 'tranquility' as DataSourceEnum,
        SINGULARITY: 'singularity' as DataSourceEnum
    };
}


