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
import { GetFwLeaderboardsCharactersVictoryPoints } from './getFwLeaderboardsCharactersVictoryPoints';
import { GetFwLeaderboardsCharactersKills } from './getFwLeaderboardsCharactersKills';


/**
 * 200 ok object
 */
export interface GetFwLeaderboardsCharactersOk { 
    kills: GetFwLeaderboardsCharactersKills;
    victory_points: GetFwLeaderboardsCharactersVictoryPoints;
}
