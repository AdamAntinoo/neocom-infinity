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
export interface GetFwSystems200Ok { 
    /**
     * contested string
     */
    contested: GetFwSystems200Ok.ContestedEnum;
    /**
     * occupier_faction_id integer
     */
    occupier_faction_id: number;
    /**
     * owner_faction_id integer
     */
    owner_faction_id: number;
    /**
     * solar_system_id integer
     */
    solar_system_id: number;
    /**
     * victory_points integer
     */
    victory_points: number;
    /**
     * victory_points_threshold integer
     */
    victory_points_threshold: number;
}
export namespace GetFwSystems200Ok {
    export type ContestedEnum = 'captured' | 'contested' | 'uncontested' | 'vulnerable';
    export const ContestedEnum = {
        Captured: 'captured' as ContestedEnum,
        Contested: 'contested' as ContestedEnum,
        Uncontested: 'uncontested' as ContestedEnum,
        Vulnerable: 'vulnerable' as ContestedEnum
    };
}


