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
export interface GetCharactersCharacterIdAgentsResearch200Ok { 
    /**
     * agent_id integer
     */
    agent_id: number;
    /**
     * points_per_day number
     */
    points_per_day: number;
    /**
     * remainder_points number
     */
    remainder_points: number;
    /**
     * skill_type_id integer
     */
    skill_type_id: number;
    /**
     * started_at string
     */
    started_at: string;
}

