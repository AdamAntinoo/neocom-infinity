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
 * Summary of victory points gained for the given faction
 */
export interface GetFwStatsVictoryPoints { 
    /**
     * Last week\'s victory points gained
     */
    last_week: number;
    /**
     * Total victory points gained since faction warfare began
     */
    total: number;
    /**
     * Yesterday\'s victory points gained
     */
    yesterday: number;
}
