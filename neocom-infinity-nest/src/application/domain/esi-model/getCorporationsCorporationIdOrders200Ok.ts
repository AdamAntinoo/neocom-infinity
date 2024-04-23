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
export interface GetCorporationsCorporationIdOrders200Ok { 
    /**
     * Number of days for which order is valid (starting from the issued date). An order expires at time issued + duration
     */
    duration: number;
    /**
     * For buy orders, the amount of ISK in escrow
     */
    escrow?: number;
    /**
     * True if the order is a bid (buy) order
     */
    is_buy_order?: boolean;
    /**
     * Date and time when this order was issued
     */
    issued: string;
    /**
     * The character who issued this order
     */
    issued_by: number;
    /**
     * ID of the location where order was placed
     */
    location_id: number;
    /**
     * For buy orders, the minimum quantity that will be accepted in a matching sell order
     */
    min_volume?: number;
    /**
     * Unique order ID
     */
    order_id: number;
    /**
     * Cost per unit for this order
     */
    price: number;
    /**
     * Valid order range, numbers are ranges in jumps
     */
    range: GetCorporationsCorporationIdOrders200Ok.RangeEnum;
    /**
     * ID of the region where order was placed
     */
    region_id: number;
    /**
     * The type ID of the item transacted in this order
     */
    type_id: number;
    /**
     * Quantity of items still required or offered
     */
    volume_remain: number;
    /**
     * Quantity of items required or offered at time order was placed
     */
    volume_total: number;
    /**
     * The corporation wallet division used for this order.
     */
    wallet_division: number;
}
export namespace GetCorporationsCorporationIdOrders200Ok {
    export type RangeEnum = '1' | '10' | '2' | '20' | '3' | '30' | '4' | '40' | '5' | 'region' | 'solarsystem' | 'station';
    export const RangeEnum = {
        _1: '1' as RangeEnum,
        _10: '10' as RangeEnum,
        _2: '2' as RangeEnum,
        _20: '20' as RangeEnum,
        _3: '3' as RangeEnum,
        _30: '30' as RangeEnum,
        _4: '4' as RangeEnum,
        _40: '40' as RangeEnum,
        _5: '5' as RangeEnum,
        Region: 'region' as RangeEnum,
        Solarsystem: 'solarsystem' as RangeEnum,
        Station: 'station' as RangeEnum
    };
}


