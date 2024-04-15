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
import { GetUniverseTypesTypeIdDogmaAttribute } from './getUniverseTypesTypeIdDogmaAttribute';
import { GetUniverseTypesTypeIdDogmaEffect } from './getUniverseTypesTypeIdDogmaEffect';


/**
 * 200 ok object
 */
export interface GetUniverseTypesTypeIdOk { 
    /**
     * capacity number
     */
    capacity?: number;
    /**
     * description string
     */
    description: string;
    /**
     * dogma_attributes array
     */
    dogma_attributes?: Array<GetUniverseTypesTypeIdDogmaAttribute>;
    /**
     * dogma_effects array
     */
    dogma_effects?: Array<GetUniverseTypesTypeIdDogmaEffect>;
    /**
     * graphic_id integer
     */
    graphic_id?: number;
    /**
     * group_id integer
     */
    group_id: number;
    /**
     * icon_id integer
     */
    icon_id?: number;
    /**
     * This only exists for types that can be put on the market
     */
    market_group_id?: number;
    /**
     * mass number
     */
    mass?: number;
    /**
     * name string
     */
    name: string;
    /**
     * packaged_volume number
     */
    packaged_volume?: number;
    /**
     * portion_size integer
     */
    portion_size?: number;
    /**
     * published boolean
     */
    published: boolean;
    /**
     * radius number
     */
    radius?: number;
    /**
     * type_id integer
     */
    type_id: number;
    /**
     * volume number
     */
    volume?: number;
}
