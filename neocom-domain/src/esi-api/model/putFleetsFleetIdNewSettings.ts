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
 * new_settings object
 */
export interface PutFleetsFleetIdNewSettings { 
    /**
     * Should free-move be enabled in the fleet
     */
    is_free_move?: boolean;
    /**
     * New fleet MOTD in CCP flavoured HTML
     */
    motd?: string;
}

