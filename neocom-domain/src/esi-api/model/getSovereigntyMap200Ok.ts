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
export interface GetSovereigntyMap200Ok { 
    /**
     * alliance_id integer
     */
    alliance_id?: number;
    /**
     * corporation_id integer
     */
    corporation_id?: number;
    /**
     * faction_id integer
     */
    faction_id?: number;
    /**
     * system_id integer
     */
    system_id: number;
}

