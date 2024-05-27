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
 * Full details of a specific event
 */
export interface GetCharactersCharacterIdCalendarEventIdOk { 
    /**
     * date string
     */
    date: string;
    /**
     * Length in minutes
     */
    duration: number;
    /**
     * event_id integer
     */
    event_id: number;
    /**
     * importance integer
     */
    importance: number;
    /**
     * owner_id integer
     */
    owner_id: number;
    /**
     * owner_name string
     */
    owner_name: string;
    /**
     * owner_type string
     */
    owner_type: GetCharactersCharacterIdCalendarEventIdOk.OwnerTypeEnum;
    /**
     * response string
     */
    response: string;
    /**
     * text string
     */
    text: string;
    /**
     * title string
     */
    title: string;
}
export namespace GetCharactersCharacterIdCalendarEventIdOk {
    export type OwnerTypeEnum = 'eve_server' | 'corporation' | 'faction' | 'character' | 'alliance';
    export const OwnerTypeEnum = {
        EveServer: 'eve_server' as OwnerTypeEnum,
        Corporation: 'corporation' as OwnerTypeEnum,
        Faction: 'faction' as OwnerTypeEnum,
        Character: 'character' as OwnerTypeEnum,
        Alliance: 'alliance' as OwnerTypeEnum
    };
}


