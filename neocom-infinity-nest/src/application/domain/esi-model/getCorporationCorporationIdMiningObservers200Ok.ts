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
export interface GetCorporationCorporationIdMiningObservers200Ok { 
    /**
     * last_updated string
     */
    last_updated: string;
    /**
     * The entity that was observing the asteroid field when it was mined. 
     */
    observer_id: number;
    /**
     * The category of the observing entity
     */
    observer_type: GetCorporationCorporationIdMiningObservers200Ok.ObserverTypeEnum;
}
export namespace GetCorporationCorporationIdMiningObservers200Ok {
    export type ObserverTypeEnum = 'structure';
    export const ObserverTypeEnum = {
        Structure: 'structure' as ObserverTypeEnum
    };
}


