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
 * recipient object
 */
export interface GetCharactersCharacterIdMailRecipient { 
    /**
     * recipient_id integer
     */
    recipient_id: number;
    /**
     * recipient_type string
     */
    recipient_type: GetCharactersCharacterIdMailRecipient.RecipientTypeEnum;
}
export namespace GetCharactersCharacterIdMailRecipient {
    export type RecipientTypeEnum = 'alliance' | 'character' | 'corporation' | 'mailing_list';
    export const RecipientTypeEnum = {
        Alliance: 'alliance' as RecipientTypeEnum,
        Character: 'character' as RecipientTypeEnum,
        Corporation: 'corporation' as RecipientTypeEnum,
        MailingList: 'mailing_list' as RecipientTypeEnum
    };
}


