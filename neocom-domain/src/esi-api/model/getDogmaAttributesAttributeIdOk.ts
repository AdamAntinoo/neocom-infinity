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
export interface GetDogmaAttributesAttributeIdOk { 
    /**
     * attribute_id integer
     */
    attribute_id: number;
    /**
     * default_value number
     */
    default_value?: number;
    /**
     * description string
     */
    description?: string;
    /**
     * display_name string
     */
    display_name?: string;
    /**
     * high_is_good boolean
     */
    high_is_good?: boolean;
    /**
     * icon_id integer
     */
    icon_id?: number;
    /**
     * name string
     */
    name?: string;
    /**
     * published boolean
     */
    published?: boolean;
    /**
     * stackable boolean
     */
    stackable?: boolean;
    /**
     * unit_id integer
     */
    unit_id?: number;
}
