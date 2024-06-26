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
 * event
 */
export interface GetCharactersCharacterIdCalendar200Ok { 
    /**
     * event_date string
     */
    event_date?: string;
    /**
     * event_id integer
     */
    event_id?: number;
    /**
     * event_response string
     */
    event_response?: GetCharactersCharacterIdCalendar200Ok.EventResponseEnum;
    /**
     * importance integer
     */
    importance?: number;
    /**
     * title string
     */
    title?: string;
}
export namespace GetCharactersCharacterIdCalendar200Ok {
    export type EventResponseEnum = 'declined' | 'not_responded' | 'accepted' | 'tentative';
    export const EventResponseEnum = {
        Declined: 'declined' as EventResponseEnum,
        NotResponded: 'not_responded' as EventResponseEnum,
        Accepted: 'accepted' as EventResponseEnum,
        Tentative: 'tentative' as EventResponseEnum
    };
}


