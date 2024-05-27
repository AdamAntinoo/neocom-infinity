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
import { GetCharactersCharacterIdMedalsGraphic } from './getCharactersCharacterIdMedalsGraphic';


/**
 * 200 ok object
 */
export interface GetCharactersCharacterIdMedals200Ok { 
    /**
     * corporation_id integer
     */
    corporation_id: number;
    /**
     * date string
     */
    date: string;
    /**
     * description string
     */
    description: string;
    /**
     * graphics array
     */
    graphics: Array<GetCharactersCharacterIdMedalsGraphic>;
    /**
     * issuer_id integer
     */
    issuer_id: number;
    /**
     * medal_id integer
     */
    medal_id: number;
    /**
     * reason string
     */
    reason: string;
    /**
     * status string
     */
    status: GetCharactersCharacterIdMedals200Ok.StatusEnum;
    /**
     * title string
     */
    title: string;
}
export namespace GetCharactersCharacterIdMedals200Ok {
    export type StatusEnum = 'public' | 'private';
    export const StatusEnum = {
        Public: 'public' as StatusEnum,
        Private: 'private' as StatusEnum
    };
}


