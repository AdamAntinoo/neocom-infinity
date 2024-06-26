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
export interface GetCorporationsCorporationIdRoles200Ok { 
    /**
     * character_id integer
     */
    character_id: number;
    /**
     * grantable_roles array
     */
    grantable_roles?: Array<GetCorporationsCorporationIdRoles200Ok.GrantableRolesEnum>;
    /**
     * grantable_roles_at_base array
     */
    grantable_roles_at_base?: Array<GetCorporationsCorporationIdRoles200Ok.GrantableRolesAtBaseEnum>;
    /**
     * grantable_roles_at_hq array
     */
    grantable_roles_at_hq?: Array<GetCorporationsCorporationIdRoles200Ok.GrantableRolesAtHqEnum>;
    /**
     * grantable_roles_at_other array
     */
    grantable_roles_at_other?: Array<GetCorporationsCorporationIdRoles200Ok.GrantableRolesAtOtherEnum>;
    /**
     * roles array
     */
    roles?: Array<GetCorporationsCorporationIdRoles200Ok.RolesEnum>;
    /**
     * roles_at_base array
     */
    roles_at_base?: Array<GetCorporationsCorporationIdRoles200Ok.RolesAtBaseEnum>;
    /**
     * roles_at_hq array
     */
    roles_at_hq?: Array<GetCorporationsCorporationIdRoles200Ok.RolesAtHqEnum>;
    /**
     * roles_at_other array
     */
    roles_at_other?: Array<GetCorporationsCorporationIdRoles200Ok.RolesAtOtherEnum>;
}
export namespace GetCorporationsCorporationIdRoles200Ok {
    export type GrantableRolesEnum = 'Account_Take_1' | 'Account_Take_2' | 'Account_Take_3' | 'Account_Take_4' | 'Account_Take_5' | 'Account_Take_6' | 'Account_Take_7' | 'Accountant' | 'Auditor' | 'Communications_Officer' | 'Config_Equipment' | 'Config_Starbase_Equipment' | 'Container_Take_1' | 'Container_Take_2' | 'Container_Take_3' | 'Container_Take_4' | 'Container_Take_5' | 'Container_Take_6' | 'Container_Take_7' | 'Contract_Manager' | 'Diplomat' | 'Director' | 'Factory_Manager' | 'Fitting_Manager' | 'Hangar_Query_1' | 'Hangar_Query_2' | 'Hangar_Query_3' | 'Hangar_Query_4' | 'Hangar_Query_5' | 'Hangar_Query_6' | 'Hangar_Query_7' | 'Hangar_Take_1' | 'Hangar_Take_2' | 'Hangar_Take_3' | 'Hangar_Take_4' | 'Hangar_Take_5' | 'Hangar_Take_6' | 'Hangar_Take_7' | 'Junior_Accountant' | 'Personnel_Manager' | 'Rent_Factory_Facility' | 'Rent_Office' | 'Rent_Research_Facility' | 'Security_Officer' | 'Starbase_Defense_Operator' | 'Starbase_Fuel_Technician' | 'Station_Manager' | 'Trader';
    export const GrantableRolesEnum = {
        AccountTake1: 'Account_Take_1' as GrantableRolesEnum,
        AccountTake2: 'Account_Take_2' as GrantableRolesEnum,
        AccountTake3: 'Account_Take_3' as GrantableRolesEnum,
        AccountTake4: 'Account_Take_4' as GrantableRolesEnum,
        AccountTake5: 'Account_Take_5' as GrantableRolesEnum,
        AccountTake6: 'Account_Take_6' as GrantableRolesEnum,
        AccountTake7: 'Account_Take_7' as GrantableRolesEnum,
        Accountant: 'Accountant' as GrantableRolesEnum,
        Auditor: 'Auditor' as GrantableRolesEnum,
        CommunicationsOfficer: 'Communications_Officer' as GrantableRolesEnum,
        ConfigEquipment: 'Config_Equipment' as GrantableRolesEnum,
        ConfigStarbaseEquipment: 'Config_Starbase_Equipment' as GrantableRolesEnum,
        ContainerTake1: 'Container_Take_1' as GrantableRolesEnum,
        ContainerTake2: 'Container_Take_2' as GrantableRolesEnum,
        ContainerTake3: 'Container_Take_3' as GrantableRolesEnum,
        ContainerTake4: 'Container_Take_4' as GrantableRolesEnum,
        ContainerTake5: 'Container_Take_5' as GrantableRolesEnum,
        ContainerTake6: 'Container_Take_6' as GrantableRolesEnum,
        ContainerTake7: 'Container_Take_7' as GrantableRolesEnum,
        ContractManager: 'Contract_Manager' as GrantableRolesEnum,
        Diplomat: 'Diplomat' as GrantableRolesEnum,
        Director: 'Director' as GrantableRolesEnum,
        FactoryManager: 'Factory_Manager' as GrantableRolesEnum,
        FittingManager: 'Fitting_Manager' as GrantableRolesEnum,
        HangarQuery1: 'Hangar_Query_1' as GrantableRolesEnum,
        HangarQuery2: 'Hangar_Query_2' as GrantableRolesEnum,
        HangarQuery3: 'Hangar_Query_3' as GrantableRolesEnum,
        HangarQuery4: 'Hangar_Query_4' as GrantableRolesEnum,
        HangarQuery5: 'Hangar_Query_5' as GrantableRolesEnum,
        HangarQuery6: 'Hangar_Query_6' as GrantableRolesEnum,
        HangarQuery7: 'Hangar_Query_7' as GrantableRolesEnum,
        HangarTake1: 'Hangar_Take_1' as GrantableRolesEnum,
        HangarTake2: 'Hangar_Take_2' as GrantableRolesEnum,
        HangarTake3: 'Hangar_Take_3' as GrantableRolesEnum,
        HangarTake4: 'Hangar_Take_4' as GrantableRolesEnum,
        HangarTake5: 'Hangar_Take_5' as GrantableRolesEnum,
        HangarTake6: 'Hangar_Take_6' as GrantableRolesEnum,
        HangarTake7: 'Hangar_Take_7' as GrantableRolesEnum,
        JuniorAccountant: 'Junior_Accountant' as GrantableRolesEnum,
        PersonnelManager: 'Personnel_Manager' as GrantableRolesEnum,
        RentFactoryFacility: 'Rent_Factory_Facility' as GrantableRolesEnum,
        RentOffice: 'Rent_Office' as GrantableRolesEnum,
        RentResearchFacility: 'Rent_Research_Facility' as GrantableRolesEnum,
        SecurityOfficer: 'Security_Officer' as GrantableRolesEnum,
        StarbaseDefenseOperator: 'Starbase_Defense_Operator' as GrantableRolesEnum,
        StarbaseFuelTechnician: 'Starbase_Fuel_Technician' as GrantableRolesEnum,
        StationManager: 'Station_Manager' as GrantableRolesEnum,
        Trader: 'Trader' as GrantableRolesEnum
    };
    export type GrantableRolesAtBaseEnum = 'Account_Take_1' | 'Account_Take_2' | 'Account_Take_3' | 'Account_Take_4' | 'Account_Take_5' | 'Account_Take_6' | 'Account_Take_7' | 'Accountant' | 'Auditor' | 'Communications_Officer' | 'Config_Equipment' | 'Config_Starbase_Equipment' | 'Container_Take_1' | 'Container_Take_2' | 'Container_Take_3' | 'Container_Take_4' | 'Container_Take_5' | 'Container_Take_6' | 'Container_Take_7' | 'Contract_Manager' | 'Diplomat' | 'Director' | 'Factory_Manager' | 'Fitting_Manager' | 'Hangar_Query_1' | 'Hangar_Query_2' | 'Hangar_Query_3' | 'Hangar_Query_4' | 'Hangar_Query_5' | 'Hangar_Query_6' | 'Hangar_Query_7' | 'Hangar_Take_1' | 'Hangar_Take_2' | 'Hangar_Take_3' | 'Hangar_Take_4' | 'Hangar_Take_5' | 'Hangar_Take_6' | 'Hangar_Take_7' | 'Junior_Accountant' | 'Personnel_Manager' | 'Rent_Factory_Facility' | 'Rent_Office' | 'Rent_Research_Facility' | 'Security_Officer' | 'Starbase_Defense_Operator' | 'Starbase_Fuel_Technician' | 'Station_Manager' | 'Trader';
    export const GrantableRolesAtBaseEnum = {
        AccountTake1: 'Account_Take_1' as GrantableRolesAtBaseEnum,
        AccountTake2: 'Account_Take_2' as GrantableRolesAtBaseEnum,
        AccountTake3: 'Account_Take_3' as GrantableRolesAtBaseEnum,
        AccountTake4: 'Account_Take_4' as GrantableRolesAtBaseEnum,
        AccountTake5: 'Account_Take_5' as GrantableRolesAtBaseEnum,
        AccountTake6: 'Account_Take_6' as GrantableRolesAtBaseEnum,
        AccountTake7: 'Account_Take_7' as GrantableRolesAtBaseEnum,
        Accountant: 'Accountant' as GrantableRolesAtBaseEnum,
        Auditor: 'Auditor' as GrantableRolesAtBaseEnum,
        CommunicationsOfficer: 'Communications_Officer' as GrantableRolesAtBaseEnum,
        ConfigEquipment: 'Config_Equipment' as GrantableRolesAtBaseEnum,
        ConfigStarbaseEquipment: 'Config_Starbase_Equipment' as GrantableRolesAtBaseEnum,
        ContainerTake1: 'Container_Take_1' as GrantableRolesAtBaseEnum,
        ContainerTake2: 'Container_Take_2' as GrantableRolesAtBaseEnum,
        ContainerTake3: 'Container_Take_3' as GrantableRolesAtBaseEnum,
        ContainerTake4: 'Container_Take_4' as GrantableRolesAtBaseEnum,
        ContainerTake5: 'Container_Take_5' as GrantableRolesAtBaseEnum,
        ContainerTake6: 'Container_Take_6' as GrantableRolesAtBaseEnum,
        ContainerTake7: 'Container_Take_7' as GrantableRolesAtBaseEnum,
        ContractManager: 'Contract_Manager' as GrantableRolesAtBaseEnum,
        Diplomat: 'Diplomat' as GrantableRolesAtBaseEnum,
        Director: 'Director' as GrantableRolesAtBaseEnum,
        FactoryManager: 'Factory_Manager' as GrantableRolesAtBaseEnum,
        FittingManager: 'Fitting_Manager' as GrantableRolesAtBaseEnum,
        HangarQuery1: 'Hangar_Query_1' as GrantableRolesAtBaseEnum,
        HangarQuery2: 'Hangar_Query_2' as GrantableRolesAtBaseEnum,
        HangarQuery3: 'Hangar_Query_3' as GrantableRolesAtBaseEnum,
        HangarQuery4: 'Hangar_Query_4' as GrantableRolesAtBaseEnum,
        HangarQuery5: 'Hangar_Query_5' as GrantableRolesAtBaseEnum,
        HangarQuery6: 'Hangar_Query_6' as GrantableRolesAtBaseEnum,
        HangarQuery7: 'Hangar_Query_7' as GrantableRolesAtBaseEnum,
        HangarTake1: 'Hangar_Take_1' as GrantableRolesAtBaseEnum,
        HangarTake2: 'Hangar_Take_2' as GrantableRolesAtBaseEnum,
        HangarTake3: 'Hangar_Take_3' as GrantableRolesAtBaseEnum,
        HangarTake4: 'Hangar_Take_4' as GrantableRolesAtBaseEnum,
        HangarTake5: 'Hangar_Take_5' as GrantableRolesAtBaseEnum,
        HangarTake6: 'Hangar_Take_6' as GrantableRolesAtBaseEnum,
        HangarTake7: 'Hangar_Take_7' as GrantableRolesAtBaseEnum,
        JuniorAccountant: 'Junior_Accountant' as GrantableRolesAtBaseEnum,
        PersonnelManager: 'Personnel_Manager' as GrantableRolesAtBaseEnum,
        RentFactoryFacility: 'Rent_Factory_Facility' as GrantableRolesAtBaseEnum,
        RentOffice: 'Rent_Office' as GrantableRolesAtBaseEnum,
        RentResearchFacility: 'Rent_Research_Facility' as GrantableRolesAtBaseEnum,
        SecurityOfficer: 'Security_Officer' as GrantableRolesAtBaseEnum,
        StarbaseDefenseOperator: 'Starbase_Defense_Operator' as GrantableRolesAtBaseEnum,
        StarbaseFuelTechnician: 'Starbase_Fuel_Technician' as GrantableRolesAtBaseEnum,
        StationManager: 'Station_Manager' as GrantableRolesAtBaseEnum,
        Trader: 'Trader' as GrantableRolesAtBaseEnum
    };
    export type GrantableRolesAtHqEnum = 'Account_Take_1' | 'Account_Take_2' | 'Account_Take_3' | 'Account_Take_4' | 'Account_Take_5' | 'Account_Take_6' | 'Account_Take_7' | 'Accountant' | 'Auditor' | 'Communications_Officer' | 'Config_Equipment' | 'Config_Starbase_Equipment' | 'Container_Take_1' | 'Container_Take_2' | 'Container_Take_3' | 'Container_Take_4' | 'Container_Take_5' | 'Container_Take_6' | 'Container_Take_7' | 'Contract_Manager' | 'Diplomat' | 'Director' | 'Factory_Manager' | 'Fitting_Manager' | 'Hangar_Query_1' | 'Hangar_Query_2' | 'Hangar_Query_3' | 'Hangar_Query_4' | 'Hangar_Query_5' | 'Hangar_Query_6' | 'Hangar_Query_7' | 'Hangar_Take_1' | 'Hangar_Take_2' | 'Hangar_Take_3' | 'Hangar_Take_4' | 'Hangar_Take_5' | 'Hangar_Take_6' | 'Hangar_Take_7' | 'Junior_Accountant' | 'Personnel_Manager' | 'Rent_Factory_Facility' | 'Rent_Office' | 'Rent_Research_Facility' | 'Security_Officer' | 'Starbase_Defense_Operator' | 'Starbase_Fuel_Technician' | 'Station_Manager' | 'Trader';
    export const GrantableRolesAtHqEnum = {
        AccountTake1: 'Account_Take_1' as GrantableRolesAtHqEnum,
        AccountTake2: 'Account_Take_2' as GrantableRolesAtHqEnum,
        AccountTake3: 'Account_Take_3' as GrantableRolesAtHqEnum,
        AccountTake4: 'Account_Take_4' as GrantableRolesAtHqEnum,
        AccountTake5: 'Account_Take_5' as GrantableRolesAtHqEnum,
        AccountTake6: 'Account_Take_6' as GrantableRolesAtHqEnum,
        AccountTake7: 'Account_Take_7' as GrantableRolesAtHqEnum,
        Accountant: 'Accountant' as GrantableRolesAtHqEnum,
        Auditor: 'Auditor' as GrantableRolesAtHqEnum,
        CommunicationsOfficer: 'Communications_Officer' as GrantableRolesAtHqEnum,
        ConfigEquipment: 'Config_Equipment' as GrantableRolesAtHqEnum,
        ConfigStarbaseEquipment: 'Config_Starbase_Equipment' as GrantableRolesAtHqEnum,
        ContainerTake1: 'Container_Take_1' as GrantableRolesAtHqEnum,
        ContainerTake2: 'Container_Take_2' as GrantableRolesAtHqEnum,
        ContainerTake3: 'Container_Take_3' as GrantableRolesAtHqEnum,
        ContainerTake4: 'Container_Take_4' as GrantableRolesAtHqEnum,
        ContainerTake5: 'Container_Take_5' as GrantableRolesAtHqEnum,
        ContainerTake6: 'Container_Take_6' as GrantableRolesAtHqEnum,
        ContainerTake7: 'Container_Take_7' as GrantableRolesAtHqEnum,
        ContractManager: 'Contract_Manager' as GrantableRolesAtHqEnum,
        Diplomat: 'Diplomat' as GrantableRolesAtHqEnum,
        Director: 'Director' as GrantableRolesAtHqEnum,
        FactoryManager: 'Factory_Manager' as GrantableRolesAtHqEnum,
        FittingManager: 'Fitting_Manager' as GrantableRolesAtHqEnum,
        HangarQuery1: 'Hangar_Query_1' as GrantableRolesAtHqEnum,
        HangarQuery2: 'Hangar_Query_2' as GrantableRolesAtHqEnum,
        HangarQuery3: 'Hangar_Query_3' as GrantableRolesAtHqEnum,
        HangarQuery4: 'Hangar_Query_4' as GrantableRolesAtHqEnum,
        HangarQuery5: 'Hangar_Query_5' as GrantableRolesAtHqEnum,
        HangarQuery6: 'Hangar_Query_6' as GrantableRolesAtHqEnum,
        HangarQuery7: 'Hangar_Query_7' as GrantableRolesAtHqEnum,
        HangarTake1: 'Hangar_Take_1' as GrantableRolesAtHqEnum,
        HangarTake2: 'Hangar_Take_2' as GrantableRolesAtHqEnum,
        HangarTake3: 'Hangar_Take_3' as GrantableRolesAtHqEnum,
        HangarTake4: 'Hangar_Take_4' as GrantableRolesAtHqEnum,
        HangarTake5: 'Hangar_Take_5' as GrantableRolesAtHqEnum,
        HangarTake6: 'Hangar_Take_6' as GrantableRolesAtHqEnum,
        HangarTake7: 'Hangar_Take_7' as GrantableRolesAtHqEnum,
        JuniorAccountant: 'Junior_Accountant' as GrantableRolesAtHqEnum,
        PersonnelManager: 'Personnel_Manager' as GrantableRolesAtHqEnum,
        RentFactoryFacility: 'Rent_Factory_Facility' as GrantableRolesAtHqEnum,
        RentOffice: 'Rent_Office' as GrantableRolesAtHqEnum,
        RentResearchFacility: 'Rent_Research_Facility' as GrantableRolesAtHqEnum,
        SecurityOfficer: 'Security_Officer' as GrantableRolesAtHqEnum,
        StarbaseDefenseOperator: 'Starbase_Defense_Operator' as GrantableRolesAtHqEnum,
        StarbaseFuelTechnician: 'Starbase_Fuel_Technician' as GrantableRolesAtHqEnum,
        StationManager: 'Station_Manager' as GrantableRolesAtHqEnum,
        Trader: 'Trader' as GrantableRolesAtHqEnum
    };
    export type GrantableRolesAtOtherEnum = 'Account_Take_1' | 'Account_Take_2' | 'Account_Take_3' | 'Account_Take_4' | 'Account_Take_5' | 'Account_Take_6' | 'Account_Take_7' | 'Accountant' | 'Auditor' | 'Communications_Officer' | 'Config_Equipment' | 'Config_Starbase_Equipment' | 'Container_Take_1' | 'Container_Take_2' | 'Container_Take_3' | 'Container_Take_4' | 'Container_Take_5' | 'Container_Take_6' | 'Container_Take_7' | 'Contract_Manager' | 'Diplomat' | 'Director' | 'Factory_Manager' | 'Fitting_Manager' | 'Hangar_Query_1' | 'Hangar_Query_2' | 'Hangar_Query_3' | 'Hangar_Query_4' | 'Hangar_Query_5' | 'Hangar_Query_6' | 'Hangar_Query_7' | 'Hangar_Take_1' | 'Hangar_Take_2' | 'Hangar_Take_3' | 'Hangar_Take_4' | 'Hangar_Take_5' | 'Hangar_Take_6' | 'Hangar_Take_7' | 'Junior_Accountant' | 'Personnel_Manager' | 'Rent_Factory_Facility' | 'Rent_Office' | 'Rent_Research_Facility' | 'Security_Officer' | 'Starbase_Defense_Operator' | 'Starbase_Fuel_Technician' | 'Station_Manager' | 'Trader';
    export const GrantableRolesAtOtherEnum = {
        AccountTake1: 'Account_Take_1' as GrantableRolesAtOtherEnum,
        AccountTake2: 'Account_Take_2' as GrantableRolesAtOtherEnum,
        AccountTake3: 'Account_Take_3' as GrantableRolesAtOtherEnum,
        AccountTake4: 'Account_Take_4' as GrantableRolesAtOtherEnum,
        AccountTake5: 'Account_Take_5' as GrantableRolesAtOtherEnum,
        AccountTake6: 'Account_Take_6' as GrantableRolesAtOtherEnum,
        AccountTake7: 'Account_Take_7' as GrantableRolesAtOtherEnum,
        Accountant: 'Accountant' as GrantableRolesAtOtherEnum,
        Auditor: 'Auditor' as GrantableRolesAtOtherEnum,
        CommunicationsOfficer: 'Communications_Officer' as GrantableRolesAtOtherEnum,
        ConfigEquipment: 'Config_Equipment' as GrantableRolesAtOtherEnum,
        ConfigStarbaseEquipment: 'Config_Starbase_Equipment' as GrantableRolesAtOtherEnum,
        ContainerTake1: 'Container_Take_1' as GrantableRolesAtOtherEnum,
        ContainerTake2: 'Container_Take_2' as GrantableRolesAtOtherEnum,
        ContainerTake3: 'Container_Take_3' as GrantableRolesAtOtherEnum,
        ContainerTake4: 'Container_Take_4' as GrantableRolesAtOtherEnum,
        ContainerTake5: 'Container_Take_5' as GrantableRolesAtOtherEnum,
        ContainerTake6: 'Container_Take_6' as GrantableRolesAtOtherEnum,
        ContainerTake7: 'Container_Take_7' as GrantableRolesAtOtherEnum,
        ContractManager: 'Contract_Manager' as GrantableRolesAtOtherEnum,
        Diplomat: 'Diplomat' as GrantableRolesAtOtherEnum,
        Director: 'Director' as GrantableRolesAtOtherEnum,
        FactoryManager: 'Factory_Manager' as GrantableRolesAtOtherEnum,
        FittingManager: 'Fitting_Manager' as GrantableRolesAtOtherEnum,
        HangarQuery1: 'Hangar_Query_1' as GrantableRolesAtOtherEnum,
        HangarQuery2: 'Hangar_Query_2' as GrantableRolesAtOtherEnum,
        HangarQuery3: 'Hangar_Query_3' as GrantableRolesAtOtherEnum,
        HangarQuery4: 'Hangar_Query_4' as GrantableRolesAtOtherEnum,
        HangarQuery5: 'Hangar_Query_5' as GrantableRolesAtOtherEnum,
        HangarQuery6: 'Hangar_Query_6' as GrantableRolesAtOtherEnum,
        HangarQuery7: 'Hangar_Query_7' as GrantableRolesAtOtherEnum,
        HangarTake1: 'Hangar_Take_1' as GrantableRolesAtOtherEnum,
        HangarTake2: 'Hangar_Take_2' as GrantableRolesAtOtherEnum,
        HangarTake3: 'Hangar_Take_3' as GrantableRolesAtOtherEnum,
        HangarTake4: 'Hangar_Take_4' as GrantableRolesAtOtherEnum,
        HangarTake5: 'Hangar_Take_5' as GrantableRolesAtOtherEnum,
        HangarTake6: 'Hangar_Take_6' as GrantableRolesAtOtherEnum,
        HangarTake7: 'Hangar_Take_7' as GrantableRolesAtOtherEnum,
        JuniorAccountant: 'Junior_Accountant' as GrantableRolesAtOtherEnum,
        PersonnelManager: 'Personnel_Manager' as GrantableRolesAtOtherEnum,
        RentFactoryFacility: 'Rent_Factory_Facility' as GrantableRolesAtOtherEnum,
        RentOffice: 'Rent_Office' as GrantableRolesAtOtherEnum,
        RentResearchFacility: 'Rent_Research_Facility' as GrantableRolesAtOtherEnum,
        SecurityOfficer: 'Security_Officer' as GrantableRolesAtOtherEnum,
        StarbaseDefenseOperator: 'Starbase_Defense_Operator' as GrantableRolesAtOtherEnum,
        StarbaseFuelTechnician: 'Starbase_Fuel_Technician' as GrantableRolesAtOtherEnum,
        StationManager: 'Station_Manager' as GrantableRolesAtOtherEnum,
        Trader: 'Trader' as GrantableRolesAtOtherEnum
    };
    export type RolesEnum = 'Account_Take_1' | 'Account_Take_2' | 'Account_Take_3' | 'Account_Take_4' | 'Account_Take_5' | 'Account_Take_6' | 'Account_Take_7' | 'Accountant' | 'Auditor' | 'Communications_Officer' | 'Config_Equipment' | 'Config_Starbase_Equipment' | 'Container_Take_1' | 'Container_Take_2' | 'Container_Take_3' | 'Container_Take_4' | 'Container_Take_5' | 'Container_Take_6' | 'Container_Take_7' | 'Contract_Manager' | 'Diplomat' | 'Director' | 'Factory_Manager' | 'Fitting_Manager' | 'Hangar_Query_1' | 'Hangar_Query_2' | 'Hangar_Query_3' | 'Hangar_Query_4' | 'Hangar_Query_5' | 'Hangar_Query_6' | 'Hangar_Query_7' | 'Hangar_Take_1' | 'Hangar_Take_2' | 'Hangar_Take_3' | 'Hangar_Take_4' | 'Hangar_Take_5' | 'Hangar_Take_6' | 'Hangar_Take_7' | 'Junior_Accountant' | 'Personnel_Manager' | 'Rent_Factory_Facility' | 'Rent_Office' | 'Rent_Research_Facility' | 'Security_Officer' | 'Starbase_Defense_Operator' | 'Starbase_Fuel_Technician' | 'Station_Manager' | 'Trader';
    export const RolesEnum = {
        AccountTake1: 'Account_Take_1' as RolesEnum,
        AccountTake2: 'Account_Take_2' as RolesEnum,
        AccountTake3: 'Account_Take_3' as RolesEnum,
        AccountTake4: 'Account_Take_4' as RolesEnum,
        AccountTake5: 'Account_Take_5' as RolesEnum,
        AccountTake6: 'Account_Take_6' as RolesEnum,
        AccountTake7: 'Account_Take_7' as RolesEnum,
        Accountant: 'Accountant' as RolesEnum,
        Auditor: 'Auditor' as RolesEnum,
        CommunicationsOfficer: 'Communications_Officer' as RolesEnum,
        ConfigEquipment: 'Config_Equipment' as RolesEnum,
        ConfigStarbaseEquipment: 'Config_Starbase_Equipment' as RolesEnum,
        ContainerTake1: 'Container_Take_1' as RolesEnum,
        ContainerTake2: 'Container_Take_2' as RolesEnum,
        ContainerTake3: 'Container_Take_3' as RolesEnum,
        ContainerTake4: 'Container_Take_4' as RolesEnum,
        ContainerTake5: 'Container_Take_5' as RolesEnum,
        ContainerTake6: 'Container_Take_6' as RolesEnum,
        ContainerTake7: 'Container_Take_7' as RolesEnum,
        ContractManager: 'Contract_Manager' as RolesEnum,
        Diplomat: 'Diplomat' as RolesEnum,
        Director: 'Director' as RolesEnum,
        FactoryManager: 'Factory_Manager' as RolesEnum,
        FittingManager: 'Fitting_Manager' as RolesEnum,
        HangarQuery1: 'Hangar_Query_1' as RolesEnum,
        HangarQuery2: 'Hangar_Query_2' as RolesEnum,
        HangarQuery3: 'Hangar_Query_3' as RolesEnum,
        HangarQuery4: 'Hangar_Query_4' as RolesEnum,
        HangarQuery5: 'Hangar_Query_5' as RolesEnum,
        HangarQuery6: 'Hangar_Query_6' as RolesEnum,
        HangarQuery7: 'Hangar_Query_7' as RolesEnum,
        HangarTake1: 'Hangar_Take_1' as RolesEnum,
        HangarTake2: 'Hangar_Take_2' as RolesEnum,
        HangarTake3: 'Hangar_Take_3' as RolesEnum,
        HangarTake4: 'Hangar_Take_4' as RolesEnum,
        HangarTake5: 'Hangar_Take_5' as RolesEnum,
        HangarTake6: 'Hangar_Take_6' as RolesEnum,
        HangarTake7: 'Hangar_Take_7' as RolesEnum,
        JuniorAccountant: 'Junior_Accountant' as RolesEnum,
        PersonnelManager: 'Personnel_Manager' as RolesEnum,
        RentFactoryFacility: 'Rent_Factory_Facility' as RolesEnum,
        RentOffice: 'Rent_Office' as RolesEnum,
        RentResearchFacility: 'Rent_Research_Facility' as RolesEnum,
        SecurityOfficer: 'Security_Officer' as RolesEnum,
        StarbaseDefenseOperator: 'Starbase_Defense_Operator' as RolesEnum,
        StarbaseFuelTechnician: 'Starbase_Fuel_Technician' as RolesEnum,
        StationManager: 'Station_Manager' as RolesEnum,
        Trader: 'Trader' as RolesEnum
    };
    export type RolesAtBaseEnum = 'Account_Take_1' | 'Account_Take_2' | 'Account_Take_3' | 'Account_Take_4' | 'Account_Take_5' | 'Account_Take_6' | 'Account_Take_7' | 'Accountant' | 'Auditor' | 'Communications_Officer' | 'Config_Equipment' | 'Config_Starbase_Equipment' | 'Container_Take_1' | 'Container_Take_2' | 'Container_Take_3' | 'Container_Take_4' | 'Container_Take_5' | 'Container_Take_6' | 'Container_Take_7' | 'Contract_Manager' | 'Diplomat' | 'Director' | 'Factory_Manager' | 'Fitting_Manager' | 'Hangar_Query_1' | 'Hangar_Query_2' | 'Hangar_Query_3' | 'Hangar_Query_4' | 'Hangar_Query_5' | 'Hangar_Query_6' | 'Hangar_Query_7' | 'Hangar_Take_1' | 'Hangar_Take_2' | 'Hangar_Take_3' | 'Hangar_Take_4' | 'Hangar_Take_5' | 'Hangar_Take_6' | 'Hangar_Take_7' | 'Junior_Accountant' | 'Personnel_Manager' | 'Rent_Factory_Facility' | 'Rent_Office' | 'Rent_Research_Facility' | 'Security_Officer' | 'Starbase_Defense_Operator' | 'Starbase_Fuel_Technician' | 'Station_Manager' | 'Trader';
    export const RolesAtBaseEnum = {
        AccountTake1: 'Account_Take_1' as RolesAtBaseEnum,
        AccountTake2: 'Account_Take_2' as RolesAtBaseEnum,
        AccountTake3: 'Account_Take_3' as RolesAtBaseEnum,
        AccountTake4: 'Account_Take_4' as RolesAtBaseEnum,
        AccountTake5: 'Account_Take_5' as RolesAtBaseEnum,
        AccountTake6: 'Account_Take_6' as RolesAtBaseEnum,
        AccountTake7: 'Account_Take_7' as RolesAtBaseEnum,
        Accountant: 'Accountant' as RolesAtBaseEnum,
        Auditor: 'Auditor' as RolesAtBaseEnum,
        CommunicationsOfficer: 'Communications_Officer' as RolesAtBaseEnum,
        ConfigEquipment: 'Config_Equipment' as RolesAtBaseEnum,
        ConfigStarbaseEquipment: 'Config_Starbase_Equipment' as RolesAtBaseEnum,
        ContainerTake1: 'Container_Take_1' as RolesAtBaseEnum,
        ContainerTake2: 'Container_Take_2' as RolesAtBaseEnum,
        ContainerTake3: 'Container_Take_3' as RolesAtBaseEnum,
        ContainerTake4: 'Container_Take_4' as RolesAtBaseEnum,
        ContainerTake5: 'Container_Take_5' as RolesAtBaseEnum,
        ContainerTake6: 'Container_Take_6' as RolesAtBaseEnum,
        ContainerTake7: 'Container_Take_7' as RolesAtBaseEnum,
        ContractManager: 'Contract_Manager' as RolesAtBaseEnum,
        Diplomat: 'Diplomat' as RolesAtBaseEnum,
        Director: 'Director' as RolesAtBaseEnum,
        FactoryManager: 'Factory_Manager' as RolesAtBaseEnum,
        FittingManager: 'Fitting_Manager' as RolesAtBaseEnum,
        HangarQuery1: 'Hangar_Query_1' as RolesAtBaseEnum,
        HangarQuery2: 'Hangar_Query_2' as RolesAtBaseEnum,
        HangarQuery3: 'Hangar_Query_3' as RolesAtBaseEnum,
        HangarQuery4: 'Hangar_Query_4' as RolesAtBaseEnum,
        HangarQuery5: 'Hangar_Query_5' as RolesAtBaseEnum,
        HangarQuery6: 'Hangar_Query_6' as RolesAtBaseEnum,
        HangarQuery7: 'Hangar_Query_7' as RolesAtBaseEnum,
        HangarTake1: 'Hangar_Take_1' as RolesAtBaseEnum,
        HangarTake2: 'Hangar_Take_2' as RolesAtBaseEnum,
        HangarTake3: 'Hangar_Take_3' as RolesAtBaseEnum,
        HangarTake4: 'Hangar_Take_4' as RolesAtBaseEnum,
        HangarTake5: 'Hangar_Take_5' as RolesAtBaseEnum,
        HangarTake6: 'Hangar_Take_6' as RolesAtBaseEnum,
        HangarTake7: 'Hangar_Take_7' as RolesAtBaseEnum,
        JuniorAccountant: 'Junior_Accountant' as RolesAtBaseEnum,
        PersonnelManager: 'Personnel_Manager' as RolesAtBaseEnum,
        RentFactoryFacility: 'Rent_Factory_Facility' as RolesAtBaseEnum,
        RentOffice: 'Rent_Office' as RolesAtBaseEnum,
        RentResearchFacility: 'Rent_Research_Facility' as RolesAtBaseEnum,
        SecurityOfficer: 'Security_Officer' as RolesAtBaseEnum,
        StarbaseDefenseOperator: 'Starbase_Defense_Operator' as RolesAtBaseEnum,
        StarbaseFuelTechnician: 'Starbase_Fuel_Technician' as RolesAtBaseEnum,
        StationManager: 'Station_Manager' as RolesAtBaseEnum,
        Trader: 'Trader' as RolesAtBaseEnum
    };
    export type RolesAtHqEnum = 'Account_Take_1' | 'Account_Take_2' | 'Account_Take_3' | 'Account_Take_4' | 'Account_Take_5' | 'Account_Take_6' | 'Account_Take_7' | 'Accountant' | 'Auditor' | 'Communications_Officer' | 'Config_Equipment' | 'Config_Starbase_Equipment' | 'Container_Take_1' | 'Container_Take_2' | 'Container_Take_3' | 'Container_Take_4' | 'Container_Take_5' | 'Container_Take_6' | 'Container_Take_7' | 'Contract_Manager' | 'Diplomat' | 'Director' | 'Factory_Manager' | 'Fitting_Manager' | 'Hangar_Query_1' | 'Hangar_Query_2' | 'Hangar_Query_3' | 'Hangar_Query_4' | 'Hangar_Query_5' | 'Hangar_Query_6' | 'Hangar_Query_7' | 'Hangar_Take_1' | 'Hangar_Take_2' | 'Hangar_Take_3' | 'Hangar_Take_4' | 'Hangar_Take_5' | 'Hangar_Take_6' | 'Hangar_Take_7' | 'Junior_Accountant' | 'Personnel_Manager' | 'Rent_Factory_Facility' | 'Rent_Office' | 'Rent_Research_Facility' | 'Security_Officer' | 'Starbase_Defense_Operator' | 'Starbase_Fuel_Technician' | 'Station_Manager' | 'Trader';
    export const RolesAtHqEnum = {
        AccountTake1: 'Account_Take_1' as RolesAtHqEnum,
        AccountTake2: 'Account_Take_2' as RolesAtHqEnum,
        AccountTake3: 'Account_Take_3' as RolesAtHqEnum,
        AccountTake4: 'Account_Take_4' as RolesAtHqEnum,
        AccountTake5: 'Account_Take_5' as RolesAtHqEnum,
        AccountTake6: 'Account_Take_6' as RolesAtHqEnum,
        AccountTake7: 'Account_Take_7' as RolesAtHqEnum,
        Accountant: 'Accountant' as RolesAtHqEnum,
        Auditor: 'Auditor' as RolesAtHqEnum,
        CommunicationsOfficer: 'Communications_Officer' as RolesAtHqEnum,
        ConfigEquipment: 'Config_Equipment' as RolesAtHqEnum,
        ConfigStarbaseEquipment: 'Config_Starbase_Equipment' as RolesAtHqEnum,
        ContainerTake1: 'Container_Take_1' as RolesAtHqEnum,
        ContainerTake2: 'Container_Take_2' as RolesAtHqEnum,
        ContainerTake3: 'Container_Take_3' as RolesAtHqEnum,
        ContainerTake4: 'Container_Take_4' as RolesAtHqEnum,
        ContainerTake5: 'Container_Take_5' as RolesAtHqEnum,
        ContainerTake6: 'Container_Take_6' as RolesAtHqEnum,
        ContainerTake7: 'Container_Take_7' as RolesAtHqEnum,
        ContractManager: 'Contract_Manager' as RolesAtHqEnum,
        Diplomat: 'Diplomat' as RolesAtHqEnum,
        Director: 'Director' as RolesAtHqEnum,
        FactoryManager: 'Factory_Manager' as RolesAtHqEnum,
        FittingManager: 'Fitting_Manager' as RolesAtHqEnum,
        HangarQuery1: 'Hangar_Query_1' as RolesAtHqEnum,
        HangarQuery2: 'Hangar_Query_2' as RolesAtHqEnum,
        HangarQuery3: 'Hangar_Query_3' as RolesAtHqEnum,
        HangarQuery4: 'Hangar_Query_4' as RolesAtHqEnum,
        HangarQuery5: 'Hangar_Query_5' as RolesAtHqEnum,
        HangarQuery6: 'Hangar_Query_6' as RolesAtHqEnum,
        HangarQuery7: 'Hangar_Query_7' as RolesAtHqEnum,
        HangarTake1: 'Hangar_Take_1' as RolesAtHqEnum,
        HangarTake2: 'Hangar_Take_2' as RolesAtHqEnum,
        HangarTake3: 'Hangar_Take_3' as RolesAtHqEnum,
        HangarTake4: 'Hangar_Take_4' as RolesAtHqEnum,
        HangarTake5: 'Hangar_Take_5' as RolesAtHqEnum,
        HangarTake6: 'Hangar_Take_6' as RolesAtHqEnum,
        HangarTake7: 'Hangar_Take_7' as RolesAtHqEnum,
        JuniorAccountant: 'Junior_Accountant' as RolesAtHqEnum,
        PersonnelManager: 'Personnel_Manager' as RolesAtHqEnum,
        RentFactoryFacility: 'Rent_Factory_Facility' as RolesAtHqEnum,
        RentOffice: 'Rent_Office' as RolesAtHqEnum,
        RentResearchFacility: 'Rent_Research_Facility' as RolesAtHqEnum,
        SecurityOfficer: 'Security_Officer' as RolesAtHqEnum,
        StarbaseDefenseOperator: 'Starbase_Defense_Operator' as RolesAtHqEnum,
        StarbaseFuelTechnician: 'Starbase_Fuel_Technician' as RolesAtHqEnum,
        StationManager: 'Station_Manager' as RolesAtHqEnum,
        Trader: 'Trader' as RolesAtHqEnum
    };
    export type RolesAtOtherEnum = 'Account_Take_1' | 'Account_Take_2' | 'Account_Take_3' | 'Account_Take_4' | 'Account_Take_5' | 'Account_Take_6' | 'Account_Take_7' | 'Accountant' | 'Auditor' | 'Communications_Officer' | 'Config_Equipment' | 'Config_Starbase_Equipment' | 'Container_Take_1' | 'Container_Take_2' | 'Container_Take_3' | 'Container_Take_4' | 'Container_Take_5' | 'Container_Take_6' | 'Container_Take_7' | 'Contract_Manager' | 'Diplomat' | 'Director' | 'Factory_Manager' | 'Fitting_Manager' | 'Hangar_Query_1' | 'Hangar_Query_2' | 'Hangar_Query_3' | 'Hangar_Query_4' | 'Hangar_Query_5' | 'Hangar_Query_6' | 'Hangar_Query_7' | 'Hangar_Take_1' | 'Hangar_Take_2' | 'Hangar_Take_3' | 'Hangar_Take_4' | 'Hangar_Take_5' | 'Hangar_Take_6' | 'Hangar_Take_7' | 'Junior_Accountant' | 'Personnel_Manager' | 'Rent_Factory_Facility' | 'Rent_Office' | 'Rent_Research_Facility' | 'Security_Officer' | 'Starbase_Defense_Operator' | 'Starbase_Fuel_Technician' | 'Station_Manager' | 'Trader';
    export const RolesAtOtherEnum = {
        AccountTake1: 'Account_Take_1' as RolesAtOtherEnum,
        AccountTake2: 'Account_Take_2' as RolesAtOtherEnum,
        AccountTake3: 'Account_Take_3' as RolesAtOtherEnum,
        AccountTake4: 'Account_Take_4' as RolesAtOtherEnum,
        AccountTake5: 'Account_Take_5' as RolesAtOtherEnum,
        AccountTake6: 'Account_Take_6' as RolesAtOtherEnum,
        AccountTake7: 'Account_Take_7' as RolesAtOtherEnum,
        Accountant: 'Accountant' as RolesAtOtherEnum,
        Auditor: 'Auditor' as RolesAtOtherEnum,
        CommunicationsOfficer: 'Communications_Officer' as RolesAtOtherEnum,
        ConfigEquipment: 'Config_Equipment' as RolesAtOtherEnum,
        ConfigStarbaseEquipment: 'Config_Starbase_Equipment' as RolesAtOtherEnum,
        ContainerTake1: 'Container_Take_1' as RolesAtOtherEnum,
        ContainerTake2: 'Container_Take_2' as RolesAtOtherEnum,
        ContainerTake3: 'Container_Take_3' as RolesAtOtherEnum,
        ContainerTake4: 'Container_Take_4' as RolesAtOtherEnum,
        ContainerTake5: 'Container_Take_5' as RolesAtOtherEnum,
        ContainerTake6: 'Container_Take_6' as RolesAtOtherEnum,
        ContainerTake7: 'Container_Take_7' as RolesAtOtherEnum,
        ContractManager: 'Contract_Manager' as RolesAtOtherEnum,
        Diplomat: 'Diplomat' as RolesAtOtherEnum,
        Director: 'Director' as RolesAtOtherEnum,
        FactoryManager: 'Factory_Manager' as RolesAtOtherEnum,
        FittingManager: 'Fitting_Manager' as RolesAtOtherEnum,
        HangarQuery1: 'Hangar_Query_1' as RolesAtOtherEnum,
        HangarQuery2: 'Hangar_Query_2' as RolesAtOtherEnum,
        HangarQuery3: 'Hangar_Query_3' as RolesAtOtherEnum,
        HangarQuery4: 'Hangar_Query_4' as RolesAtOtherEnum,
        HangarQuery5: 'Hangar_Query_5' as RolesAtOtherEnum,
        HangarQuery6: 'Hangar_Query_6' as RolesAtOtherEnum,
        HangarQuery7: 'Hangar_Query_7' as RolesAtOtherEnum,
        HangarTake1: 'Hangar_Take_1' as RolesAtOtherEnum,
        HangarTake2: 'Hangar_Take_2' as RolesAtOtherEnum,
        HangarTake3: 'Hangar_Take_3' as RolesAtOtherEnum,
        HangarTake4: 'Hangar_Take_4' as RolesAtOtherEnum,
        HangarTake5: 'Hangar_Take_5' as RolesAtOtherEnum,
        HangarTake6: 'Hangar_Take_6' as RolesAtOtherEnum,
        HangarTake7: 'Hangar_Take_7' as RolesAtOtherEnum,
        JuniorAccountant: 'Junior_Accountant' as RolesAtOtherEnum,
        PersonnelManager: 'Personnel_Manager' as RolesAtOtherEnum,
        RentFactoryFacility: 'Rent_Factory_Facility' as RolesAtOtherEnum,
        RentOffice: 'Rent_Office' as RolesAtOtherEnum,
        RentResearchFacility: 'Rent_Research_Facility' as RolesAtOtherEnum,
        SecurityOfficer: 'Security_Officer' as RolesAtOtherEnum,
        StarbaseDefenseOperator: 'Starbase_Defense_Operator' as RolesAtOtherEnum,
        StarbaseFuelTechnician: 'Starbase_Fuel_Technician' as RolesAtOtherEnum,
        StationManager: 'Station_Manager' as RolesAtOtherEnum,
        Trader: 'Trader' as RolesAtOtherEnum
    };
}


