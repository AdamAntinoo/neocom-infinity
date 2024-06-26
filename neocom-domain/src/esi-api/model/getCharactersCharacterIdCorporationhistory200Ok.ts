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
export interface GetCharactersCharacterIdCorporationhistory200Ok { 
    /**
     * corporation_id integer
     */
    corporation_id: number;
    /**
     * True if the corporation has been deleted
     */
    is_deleted?: boolean;
    /**
     * An incrementing ID that can be used to canonically establish order of records in cases where dates may be ambiguous
     */
    record_id: number;
    /**
     * start_date string
     */
    start_date: string;
}

