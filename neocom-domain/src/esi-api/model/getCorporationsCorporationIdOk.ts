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
export interface GetCorporationsCorporationIdOk { 
    /**
     * ID of the alliance that corporation is a member of, if any
     */
    alliance_id?: number;
    /**
     * ceo_id integer
     */
    ceo_id: number;
    /**
     * creator_id integer
     */
    creator_id: number;
    /**
     * date_founded string
     */
    date_founded?: string;
    /**
     * description string
     */
    description?: string;
    /**
     * faction_id integer
     */
    faction_id?: number;
    /**
     * home_station_id integer
     */
    home_station_id?: number;
    /**
     * member_count integer
     */
    member_count: number;
    /**
     * the full name of the corporation
     */
    name: string;
    /**
     * shares integer
     */
    shares?: number;
    /**
     * tax_rate number
     */
    tax_rate: number;
    /**
     * the short name of the corporation
     */
    ticker: string;
    /**
     * url string
     */
    url?: string;
    /**
     * war_eligible boolean
     */
    war_eligible?: boolean;
}

