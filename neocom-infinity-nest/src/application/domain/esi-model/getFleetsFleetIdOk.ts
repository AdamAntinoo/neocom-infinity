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
export interface GetFleetsFleetIdOk { 
    /**
     * Is free-move enabled
     */
    is_free_move: boolean;
    /**
     * Does the fleet have an active fleet advertisement
     */
    is_registered: boolean;
    /**
     * Is EVE Voice enabled
     */
    is_voice_enabled: boolean;
    /**
     * Fleet MOTD in CCP flavoured HTML
     */
    motd: string;
}

