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
import { GetDogmaEffectsEffectIdModifier } from './getDogmaEffectsEffectIdModifier';


/**
 * 200 ok object
 */
export interface GetDogmaEffectsEffectIdOk { 
    /**
     * description string
     */
    description?: string;
    /**
     * disallow_auto_repeat boolean
     */
    disallow_auto_repeat?: boolean;
    /**
     * discharge_attribute_id integer
     */
    discharge_attribute_id?: number;
    /**
     * display_name string
     */
    display_name?: string;
    /**
     * duration_attribute_id integer
     */
    duration_attribute_id?: number;
    /**
     * effect_category integer
     */
    effect_category?: number;
    /**
     * effect_id integer
     */
    effect_id: number;
    /**
     * electronic_chance boolean
     */
    electronic_chance?: boolean;
    /**
     * falloff_attribute_id integer
     */
    falloff_attribute_id?: number;
    /**
     * icon_id integer
     */
    icon_id?: number;
    /**
     * is_assistance boolean
     */
    is_assistance?: boolean;
    /**
     * is_offensive boolean
     */
    is_offensive?: boolean;
    /**
     * is_warp_safe boolean
     */
    is_warp_safe?: boolean;
    /**
     * modifiers array
     */
    modifiers?: Array<GetDogmaEffectsEffectIdModifier>;
    /**
     * name string
     */
    name?: string;
    /**
     * post_expression integer
     */
    post_expression?: number;
    /**
     * pre_expression integer
     */
    pre_expression?: number;
    /**
     * published boolean
     */
    published?: boolean;
    /**
     * range_attribute_id integer
     */
    range_attribute_id?: number;
    /**
     * range_chance boolean
     */
    range_chance?: boolean;
    /**
     * tracking_speed_attribute_id integer
     */
    tracking_speed_attribute_id?: number;
}

