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
import { GetCharactersCharacterIdSkillsSkill } from './getCharactersCharacterIdSkillsSkill';


/**
 * 200 ok object
 */
export interface GetCharactersCharacterIdSkillsOk { 
    /**
     * skills array
     */
    skills: Array<GetCharactersCharacterIdSkillsSkill>;
    /**
     * total_sp integer
     */
    total_sp: number;
    /**
     * Skill points available to be assigned
     */
    unallocated_sp?: number;
}

