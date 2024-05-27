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
 * attacker object
 */
export interface GetKillmailsKillmailIdKillmailHashAttacker { 
    /**
     * alliance_id integer
     */
    alliance_id?: number;
    /**
     * character_id integer
     */
    character_id?: number;
    /**
     * corporation_id integer
     */
    corporation_id?: number;
    /**
     * damage_done integer
     */
    damage_done: number;
    /**
     * faction_id integer
     */
    faction_id?: number;
    /**
     * Was the attacker the one to achieve the final blow 
     */
    final_blow: boolean;
    /**
     * Security status for the attacker 
     */
    security_status: number;
    /**
     * What ship was the attacker flying 
     */
    ship_type_id?: number;
    /**
     * What weapon was used by the attacker for the kill 
     */
    weapon_type_id?: number;
}
