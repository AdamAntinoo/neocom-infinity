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
export interface GetCorporationsCorporationIdMembertracking200Ok { 
    /**
     * base_id integer
     */
    base_id?: number;
    /**
     * character_id integer
     */
    character_id: number;
    /**
     * location_id integer
     */
    location_id?: number;
    /**
     * logoff_date string
     */
    logoff_date?: string;
    /**
     * logon_date string
     */
    logon_date?: string;
    /**
     * ship_type_id integer
     */
    ship_type_id?: number;
    /**
     * start_date string
     */
    start_date?: string;
}

