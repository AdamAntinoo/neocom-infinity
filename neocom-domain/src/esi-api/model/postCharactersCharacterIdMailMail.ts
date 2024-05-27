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
import { PostCharactersCharacterIdMailRecipient } from './postCharactersCharacterIdMailRecipient';


/**
 * mail object
 */
export interface PostCharactersCharacterIdMailMail { 
    /**
     * approved_cost integer
     */
    approved_cost?: number;
    /**
     * body string
     */
    body: string;
    /**
     * recipients array
     */
    recipients: Array<PostCharactersCharacterIdMailRecipient>;
    /**
     * subject string
     */
    subject: string;
}

