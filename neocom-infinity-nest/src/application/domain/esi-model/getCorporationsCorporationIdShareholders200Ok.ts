/**
 * EVE Swagger Interface
 * An OpenAPI for EVE Online
 *
 * The version of the OpenAPI document: 1.21
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


/**
 * 200 ok object
 */
export interface GetCorporationsCorporationIdShareholders200Ok { 
    /**
     * share_count integer
     */
    share_count: number;
    /**
     * shareholder_id integer
     */
    shareholder_id: number;
    /**
     * shareholder_type string
     */
    shareholder_type: GetCorporationsCorporationIdShareholders200Ok.ShareholderTypeEnum;
}
export namespace GetCorporationsCorporationIdShareholders200Ok {
    export type ShareholderTypeEnum = 'character' | 'corporation';
    export const ShareholderTypeEnum = {
        Character: 'character' as ShareholderTypeEnum,
        Corporation: 'corporation' as ShareholderTypeEnum
    };
}


