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
export interface GetContractsPublicBidsContractId200Ok { 
    /**
     * The amount bid, in ISK
     */
    amount: number;
    /**
     * Unique ID for the bid
     */
    bid_id: number;
    /**
     * Datetime when the bid was placed
     */
    date_bid: string;
}

