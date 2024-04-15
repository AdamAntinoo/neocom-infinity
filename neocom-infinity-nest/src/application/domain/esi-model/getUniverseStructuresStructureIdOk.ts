/**
 * EVE Swagger Interface
 * An OpenAPI for EVE Online
 *
 * The version of the OpenAPI document: 1.21
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
import { GetUniverseStructuresStructureIdPosition } from './getUniverseStructuresStructureIdPosition';


/**
 * 200 ok object
 */
export interface GetUniverseStructuresStructureIdOk { 
    /**
     * The full name of the structure
     */
    name: string;
    /**
     * The ID of the corporation who owns this particular structure
     */
    owner_id: number;
    position?: GetUniverseStructuresStructureIdPosition;
    /**
     * solar_system_id integer
     */
    solar_system_id: number;
    /**
     * type_id integer
     */
    type_id?: number;
}
