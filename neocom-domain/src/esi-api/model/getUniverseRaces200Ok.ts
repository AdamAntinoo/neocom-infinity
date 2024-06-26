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
export interface GetUniverseRaces200Ok { 
    /**
     * The alliance generally associated with this race
     */
    alliance_id: number;
    /**
     * description string
     */
    description: string;
    /**
     * name string
     */
    name: string;
    /**
     * race_id integer
     */
    race_id: number;
}

