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
import { GetFleetsFleetIdWingsSquad } from './getFleetsFleetIdWingsSquad';


/**
 * 200 ok object
 */
export interface GetFleetsFleetIdWings200Ok { 
    /**
     * id integer
     */
    id: number;
    /**
     * name string
     */
    name: string;
    /**
     * squads array
     */
    squads: Array<GetFleetsFleetIdWingsSquad>;
}

