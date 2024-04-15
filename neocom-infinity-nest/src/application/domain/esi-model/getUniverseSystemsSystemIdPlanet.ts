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
 * planet object
 */
export interface GetUniverseSystemsSystemIdPlanet { 
    /**
     * asteroid_belts array
     */
    asteroid_belts?: Array<number>;
    /**
     * moons array
     */
    moons?: Array<number>;
    /**
     * planet_id integer
     */
    planet_id: number;
}
