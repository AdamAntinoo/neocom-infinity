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
export interface PostCharactersAffiliation200Ok { 
    /**
     * The character\'s alliance ID, if their corporation is in an alliance
     */
    alliance_id?: number;
    /**
     * The character\'s ID
     */
    character_id: number;
    /**
     * The character\'s corporation ID
     */
    corporation_id: number;
    /**
     * The character\'s faction ID, if their corporation is in a faction
     */
    faction_id?: number;
}

