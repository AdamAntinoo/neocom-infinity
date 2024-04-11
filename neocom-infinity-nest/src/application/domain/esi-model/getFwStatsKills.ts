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
 * Summary of kills against an enemy faction for the given faction
 */
export interface GetFwStatsKills { 
    /**
     * Last week\'s total number of kills against enemy factions
     */
    last_week: number;
    /**
     * Total number of kills against enemy factions since faction warfare began
     */
    total: number;
    /**
     * Yesterday\'s total number of kills against enemy factions
     */
    yesterday: number;
}

