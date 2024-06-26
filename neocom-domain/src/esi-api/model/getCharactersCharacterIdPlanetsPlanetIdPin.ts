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
import { GetCharactersCharacterIdPlanetsPlanetIdFactoryDetails } from './getCharactersCharacterIdPlanetsPlanetIdFactoryDetails';
import { GetCharactersCharacterIdPlanetsPlanetIdExtractorDetails } from './getCharactersCharacterIdPlanetsPlanetIdExtractorDetails';
import { GetCharactersCharacterIdPlanetsPlanetIdContent } from './getCharactersCharacterIdPlanetsPlanetIdContent';


/**
 * pin object
 */
export interface GetCharactersCharacterIdPlanetsPlanetIdPin { 
    /**
     * contents array
     */
    contents?: Array<GetCharactersCharacterIdPlanetsPlanetIdContent>;
    /**
     * expiry_time string
     */
    expiry_time?: string;
    extractor_details?: GetCharactersCharacterIdPlanetsPlanetIdExtractorDetails;
    factory_details?: GetCharactersCharacterIdPlanetsPlanetIdFactoryDetails;
    /**
     * install_time string
     */
    install_time?: string;
    /**
     * last_cycle_start string
     */
    last_cycle_start?: string;
    /**
     * latitude number
     */
    latitude: number;
    /**
     * longitude number
     */
    longitude: number;
    /**
     * pin_id integer
     */
    pin_id: number;
    /**
     * schematic_id integer
     */
    schematic_id?: number;
    /**
     * type_id integer
     */
    type_id: number;
}

