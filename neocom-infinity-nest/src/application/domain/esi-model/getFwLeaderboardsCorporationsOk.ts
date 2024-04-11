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
import { GetFwLeaderboardsCorporationsVictoryPoints } from './getFwLeaderboardsCorporationsVictoryPoints';
import { GetFwLeaderboardsCorporationsKills } from './getFwLeaderboardsCorporationsKills';


/**
 * 200 ok object
 */
export interface GetFwLeaderboardsCorporationsOk { 
    kills: GetFwLeaderboardsCorporationsKills;
    victory_points: GetFwLeaderboardsCorporationsVictoryPoints;
}

