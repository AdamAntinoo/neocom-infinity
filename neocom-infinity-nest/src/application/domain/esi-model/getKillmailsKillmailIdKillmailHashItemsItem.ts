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
 * item object
 */
export interface GetKillmailsKillmailIdKillmailHashItemsItem { 
    /**
     * flag integer
     */
    flag: number;
    /**
     * item_type_id integer
     */
    item_type_id: number;
    /**
     * quantity_destroyed integer
     */
    quantity_destroyed?: number;
    /**
     * quantity_dropped integer
     */
    quantity_dropped?: number;
    /**
     * singleton integer
     */
    singleton: number;
}

