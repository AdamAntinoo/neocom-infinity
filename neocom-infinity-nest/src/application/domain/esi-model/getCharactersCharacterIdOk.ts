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
 * 200 ok object
 */
export interface GetCharactersCharacterIdOk { 
    /**
     * The character\'s alliance ID
     */
    alliance_id?: number;
    /**
     * Creation date of the character
     */
    birthday: string;
    /**
     * bloodline_id integer
     */
    bloodline_id: number;
    /**
     * The character\'s corporation ID
     */
    corporation_id: number;
    /**
     * description string
     */
    description?: string;
    /**
     * ID of the faction the character is fighting for, if the character is enlisted in Factional Warfare
     */
    faction_id?: number;
    /**
     * gender string
     */
    gender: GetCharactersCharacterIdOk.GenderEnum;
    /**
     * name string
     */
    name: string;
    /**
     * race_id integer
     */
    race_id: number;
    /**
     * security_status number
     */
    security_status?: number;
    /**
     * The individual title of the character
     */
    title?: string;
}
export namespace GetCharactersCharacterIdOk {
    export type GenderEnum = 'female' | 'male';
    export const GenderEnum = {
        Female: 'female' as GenderEnum,
        Male: 'male' as GenderEnum
    };
}

