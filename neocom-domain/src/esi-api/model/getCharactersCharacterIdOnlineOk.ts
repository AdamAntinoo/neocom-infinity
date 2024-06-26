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
export interface GetCharactersCharacterIdOnlineOk { 
    /**
     * Timestamp of the last login
     */
    last_login?: string;
    /**
     * Timestamp of the last logout
     */
    last_logout?: string;
    /**
     * Total number of times the character has logged in
     */
    logins?: number;
    /**
     * If the character is online
     */
    online: boolean;
}

