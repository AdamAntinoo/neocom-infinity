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
import { GetCharactersCharacterIdMailMailIdRecipient } from './getCharactersCharacterIdMailMailIdRecipient';


/**
 * 200 ok object
 */
export interface GetCharactersCharacterIdMailMailIdOk { 
    /**
     * Mail\'s body
     */
    body?: string;
    /**
     * From whom the mail was sent
     */
    from?: number;
    /**
     * Labels attached to the mail
     */
    labels?: Array<number>;
    /**
     * Whether the mail is flagged as read
     */
    read?: boolean;
    /**
     * Recipients of the mail
     */
    recipients?: Set<GetCharactersCharacterIdMailMailIdRecipient>;
    /**
     * Mail subject
     */
    subject?: string;
    /**
     * When the mail was sent
     */
    timestamp?: string;
}

