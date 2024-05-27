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
export interface GetStatusOk { 
    /**
     * Current online player count
     */
    players: number;
    /**
     * Running version as string
     */
    server_version: string;
    /**
     * Server start timestamp
     */
    start_time: string;
    /**
     * If the server is in VIP mode
     */
    vip?: boolean;
}

