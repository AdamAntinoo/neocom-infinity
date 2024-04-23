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
import { GetCorporationsCorporationIdFwStatsKills } from './getCorporationsCorporationIdFwStatsKills';
import { GetCorporationsCorporationIdFwStatsVictoryPoints } from './getCorporationsCorporationIdFwStatsVictoryPoints';


/**
 * 200 ok object
 */
export interface GetCorporationsCorporationIdFwStatsOk { 
    /**
     * The enlistment date of the given corporation into faction warfare. Will not be included if corporation is not enlisted in faction warfare
     */
    enlisted_on?: string;
    /**
     * The faction the given corporation is enlisted to fight for. Will not be included if corporation is not enlisted in faction warfare
     */
    faction_id?: number;
    kills: GetCorporationsCorporationIdFwStatsKills;
    /**
     * How many pilots the enlisted corporation has. Will not be included if corporation is not enlisted in faction warfare
     */
    pilots?: number;
    victory_points: GetCorporationsCorporationIdFwStatsVictoryPoints;
}

