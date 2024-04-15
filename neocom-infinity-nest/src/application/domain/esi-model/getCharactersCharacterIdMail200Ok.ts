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
import { GetCharactersCharacterIdMailRecipient } from './getCharactersCharacterIdMailRecipient';


/**
 * 200 ok object
 */
export interface GetCharactersCharacterIdMail200Ok { 
    /**
     * From whom the mail was sent
     */
    from?: number;
    /**
     * is_read boolean
     */
    is_read?: boolean;
    /**
     * labels array
     */
    labels?: Set<number>;
    /**
     * mail_id integer
     */
    mail_id?: number;
    /**
     * Recipients of the mail
     */
    recipients?: Set<GetCharactersCharacterIdMailRecipient>;
    /**
     * Mail subject
     */
    subject?: string;
    /**
     * When the mail was sent
     */
    timestamp?: string;
}
