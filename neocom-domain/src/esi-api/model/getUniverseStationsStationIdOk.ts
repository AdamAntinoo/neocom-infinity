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
import { GetUniverseStationsStationIdPosition } from './getUniverseStationsStationIdPosition';


/**
 * 200 ok object
 */
export interface GetUniverseStationsStationIdOk { 
    /**
     * max_dockable_ship_volume number
     */
    max_dockable_ship_volume: number;
    /**
     * name string
     */
    name: string;
    /**
     * office_rental_cost number
     */
    office_rental_cost: number;
    /**
     * ID of the corporation that controls this station
     */
    owner?: number;
    position: GetUniverseStationsStationIdPosition;
    /**
     * race_id integer
     */
    race_id?: number;
    /**
     * reprocessing_efficiency number
     */
    reprocessing_efficiency: number;
    /**
     * reprocessing_stations_take number
     */
    reprocessing_stations_take: number;
    /**
     * services array
     */
    services: Array<GetUniverseStationsStationIdOk.ServicesEnum>;
    /**
     * station_id integer
     */
    station_id: number;
    /**
     * The solar system this station is in
     */
    system_id: number;
    /**
     * type_id integer
     */
    type_id: number;
}
export namespace GetUniverseStationsStationIdOk {
    export type ServicesEnum = 'bounty-missions' | 'assasination-missions' | 'courier-missions' | 'interbus' | 'reprocessing-plant' | 'refinery' | 'market' | 'black-market' | 'stock-exchange' | 'cloning' | 'surgery' | 'dna-therapy' | 'repair-facilities' | 'factory' | 'labratory' | 'gambling' | 'fitting' | 'paintshop' | 'news' | 'storage' | 'insurance' | 'docking' | 'office-rental' | 'jump-clone-facility' | 'loyalty-point-store' | 'navy-offices' | 'security-offices';
    export const ServicesEnum = {
        BountyMissions: 'bounty-missions' as ServicesEnum,
        AssasinationMissions: 'assasination-missions' as ServicesEnum,
        CourierMissions: 'courier-missions' as ServicesEnum,
        Interbus: 'interbus' as ServicesEnum,
        ReprocessingPlant: 'reprocessing-plant' as ServicesEnum,
        Refinery: 'refinery' as ServicesEnum,
        Market: 'market' as ServicesEnum,
        BlackMarket: 'black-market' as ServicesEnum,
        StockExchange: 'stock-exchange' as ServicesEnum,
        Cloning: 'cloning' as ServicesEnum,
        Surgery: 'surgery' as ServicesEnum,
        DnaTherapy: 'dna-therapy' as ServicesEnum,
        RepairFacilities: 'repair-facilities' as ServicesEnum,
        Factory: 'factory' as ServicesEnum,
        Labratory: 'labratory' as ServicesEnum,
        Gambling: 'gambling' as ServicesEnum,
        Fitting: 'fitting' as ServicesEnum,
        Paintshop: 'paintshop' as ServicesEnum,
        News: 'news' as ServicesEnum,
        Storage: 'storage' as ServicesEnum,
        Insurance: 'insurance' as ServicesEnum,
        Docking: 'docking' as ServicesEnum,
        OfficeRental: 'office-rental' as ServicesEnum,
        JumpCloneFacility: 'jump-clone-facility' as ServicesEnum,
        LoyaltyPointStore: 'loyalty-point-store' as ServicesEnum,
        NavyOffices: 'navy-offices' as ServicesEnum,
        SecurityOffices: 'security-offices' as ServicesEnum
    };
}


