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
export interface GetCharactersCharacterIdAttributesOk { 
    /**
     * Neural remapping cooldown after a character uses remap accrued over time
     */
    accrued_remap_cooldown_date?: string;
    /**
     * Number of available bonus character neural remaps
     */
    bonus_remaps?: number;
    /**
     * charisma integer
     */
    charisma: number;
    /**
     * intelligence integer
     */
    intelligence: number;
    /**
     * Datetime of last neural remap, including usage of bonus remaps
     */
    last_remap_date?: string;
    /**
     * memory integer
     */
    memory: number;
    /**
     * perception integer
     */
    perception: number;
    /**
     * willpower integer
     */
    willpower: number;
}

