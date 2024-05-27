/**
 * EVE Swagger Interface
 * An OpenAPI for EVE Online
 *
 * The version of the OpenAPI document: 1.7.15
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


/**
 * 200 ok object
 */
export interface GetCorporationsCorporationIdMedalsIssued200Ok { 
    /**
     * ID of the character who was rewarded this medal
     */
    character_id: number;
    /**
     * issued_at string
     */
    issued_at: string;
    /**
     * ID of the character who issued the medal
     */
    issuer_id: number;
    /**
     * medal_id integer
     */
    medal_id: number;
    /**
     * reason string
     */
    reason: string;
    /**
     * status string
     */
    status: GetCorporationsCorporationIdMedalsIssued200Ok.StatusEnum;
}
export namespace GetCorporationsCorporationIdMedalsIssued200Ok {
    export type StatusEnum = 'private' | 'public';
    export const StatusEnum = {
        Private: 'private' as StatusEnum,
        Public: 'public' as StatusEnum
    };
}


