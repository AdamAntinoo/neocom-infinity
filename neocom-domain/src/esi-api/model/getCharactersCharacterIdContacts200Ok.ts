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
export interface GetCharactersCharacterIdContacts200Ok { 
    /**
     * contact_id integer
     */
    contact_id: number;
    /**
     * contact_type string
     */
    contact_type: GetCharactersCharacterIdContacts200Ok.ContactTypeEnum;
    /**
     * Whether this contact is in the blocked list. Note a missing value denotes unknown, not true or false
     */
    is_blocked?: boolean;
    /**
     * Whether this contact is being watched
     */
    is_watched?: boolean;
    /**
     * label_ids array
     */
    label_ids?: Array<number>;
    /**
     * Standing of the contact
     */
    standing: number;
}
export namespace GetCharactersCharacterIdContacts200Ok {
    export type ContactTypeEnum = 'character' | 'corporation' | 'alliance' | 'faction';
    export const ContactTypeEnum = {
        Character: 'character' as ContactTypeEnum,
        Corporation: 'corporation' as ContactTypeEnum,
        Alliance: 'alliance' as ContactTypeEnum,
        Faction: 'faction' as ContactTypeEnum
    };
}


