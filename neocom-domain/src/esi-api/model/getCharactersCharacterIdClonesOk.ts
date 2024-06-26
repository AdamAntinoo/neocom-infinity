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
import { GetCharactersCharacterIdClonesJumpClone } from './getCharactersCharacterIdClonesJumpClone';
import { GetCharactersCharacterIdClonesHomeLocation } from './getCharactersCharacterIdClonesHomeLocation';


/**
 * 200 ok object
 */
export interface GetCharactersCharacterIdClonesOk { 
    home_location?: GetCharactersCharacterIdClonesHomeLocation;
    /**
     * jump_clones array
     */
    jump_clones: Array<GetCharactersCharacterIdClonesJumpClone>;
    /**
     * last_clone_jump_date string
     */
    last_clone_jump_date?: string;
    /**
     * last_station_change_date string
     */
    last_station_change_date?: string;
}

