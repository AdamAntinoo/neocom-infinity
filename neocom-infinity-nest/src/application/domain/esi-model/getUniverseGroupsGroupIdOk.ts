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


/**
 * 200 ok object
 */
export interface GetUniverseGroupsGroupIdOk { 
    /**
     * category_id integer
     */
    category_id: number;
    /**
     * group_id integer
     */
    group_id: number;
    /**
     * name string
     */
    name: string;
    /**
     * published boolean
     */
    published: boolean;
    /**
     * types array
     */
    types: Array<number>;
}

