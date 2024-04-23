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
import { GetUniverseConstellationsConstellationIdPosition } from './getUniverseConstellationsConstellationIdPosition';


/**
 * 200 ok object
 */
export interface GetUniverseConstellationsConstellationIdOk { 
    /**
     * constellation_id integer
     */
    constellation_id: number;
    /**
     * name string
     */
    name: string;
    position: GetUniverseConstellationsConstellationIdPosition;
    /**
     * The region this constellation is in
     */
    region_id: number;
    /**
     * systems array
     */
    systems: Array<number>;
}

