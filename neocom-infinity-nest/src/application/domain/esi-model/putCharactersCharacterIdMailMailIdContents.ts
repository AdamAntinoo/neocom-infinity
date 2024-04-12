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
 * contents object
 */
export interface PutCharactersCharacterIdMailMailIdContents { 
    /**
     * Labels to assign to the mail. Pre-existing labels are unassigned.
     */
    labels?: Array<number>;
    /**
     * Whether the mail is flagged as read
     */
    read?: boolean;
}

