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
export interface GetFleetsFleetIdMembers200Ok { 
    /**
     * character_id integer
     */
    character_id: number;
    /**
     * join_time string
     */
    join_time: string;
    /**
     * Member’s role in fleet
     */
    role: GetFleetsFleetIdMembers200Ok.RoleEnum;
    /**
     * Localized role names
     */
    role_name: string;
    /**
     * ship_type_id integer
     */
    ship_type_id: number;
    /**
     * Solar system the member is located in
     */
    solar_system_id: number;
    /**
     * ID of the squad the member is in. If not applicable, will be set to -1
     */
    squad_id: number;
    /**
     * Station in which the member is docked in, if applicable
     */
    station_id?: number;
    /**
     * Whether the member take fleet warps
     */
    takes_fleet_warp: boolean;
    /**
     * ID of the wing the member is in. If not applicable, will be set to -1
     */
    wing_id: number;
}
export namespace GetFleetsFleetIdMembers200Ok {
    export type RoleEnum = 'fleet_commander' | 'wing_commander' | 'squad_commander' | 'squad_member';
    export const RoleEnum = {
        FleetCommander: 'fleet_commander' as RoleEnum,
        WingCommander: 'wing_commander' as RoleEnum,
        SquadCommander: 'squad_commander' as RoleEnum,
        SquadMember: 'squad_member' as RoleEnum
    };
}


