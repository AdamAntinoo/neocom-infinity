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
import { PostUniverseIdsInventoryType } from './postUniverseIdsInventoryType';
import { PostUniverseIdsAlliance } from './postUniverseIdsAlliance';
import { PostUniverseIdsStation } from './postUniverseIdsStation';
import { PostUniverseIdsCharacter } from './postUniverseIdsCharacter';
import { PostUniverseIdsCorporation } from './postUniverseIdsCorporation';
import { PostUniverseIdsSystem } from './postUniverseIdsSystem';
import { PostUniverseIdsConstellation } from './postUniverseIdsConstellation';
import { PostUniverseIdsRegion } from './postUniverseIdsRegion';
import { PostUniverseIdsFaction } from './postUniverseIdsFaction';
import { PostUniverseIdsAgent } from './postUniverseIdsAgent';


/**
 * 200 ok object
 */
export interface PostUniverseIdsOk { 
    /**
     * agents array
     */
    agents?: Array<PostUniverseIdsAgent>;
    /**
     * alliances array
     */
    alliances?: Array<PostUniverseIdsAlliance>;
    /**
     * characters array
     */
    characters?: Array<PostUniverseIdsCharacter>;
    /**
     * constellations array
     */
    constellations?: Array<PostUniverseIdsConstellation>;
    /**
     * corporations array
     */
    corporations?: Array<PostUniverseIdsCorporation>;
    /**
     * factions array
     */
    factions?: Array<PostUniverseIdsFaction>;
    /**
     * inventory_types array
     */
    inventory_types?: Array<PostUniverseIdsInventoryType>;
    /**
     * regions array
     */
    regions?: Array<PostUniverseIdsRegion>;
    /**
     * stations array
     */
    stations?: Array<PostUniverseIdsStation>;
    /**
     * systems array
     */
    systems?: Array<PostUniverseIdsSystem>;
}

