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
 * home_location object
 */
export interface GetCharactersCharacterIdClonesHomeLocation { 
    /**
     * location_id integer
     */
    location_id?: number;
    /**
     * location_type string
     */
    location_type?: GetCharactersCharacterIdClonesHomeLocation.LocationTypeEnum;
}
export namespace GetCharactersCharacterIdClonesHomeLocation {
    export type LocationTypeEnum = 'station' | 'structure';
    export const LocationTypeEnum = {
        Station: 'station' as LocationTypeEnum,
        Structure: 'structure' as LocationTypeEnum
    };
}


