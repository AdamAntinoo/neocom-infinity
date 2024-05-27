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
export interface GetUniverseBloodlines200Ok { 
    /**
     * bloodline_id integer
     */
    bloodline_id: number;
    /**
     * charisma integer
     */
    charisma: number;
    /**
     * corporation_id integer
     */
    corporation_id: number;
    /**
     * description string
     */
    description: string;
    /**
     * intelligence integer
     */
    intelligence: number;
    /**
     * memory integer
     */
    memory: number;
    /**
     * name string
     */
    name: string;
    /**
     * perception integer
     */
    perception: number;
    /**
     * race_id integer
     */
    race_id: number;
    /**
     * ship_type_id integer
     */
    ship_type_id: number | null;
    /**
     * willpower integer
     */
    willpower: number;
}

