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
import { GetCorporationsCorporationIdBookmarksCoordinates } from './getCorporationsCorporationIdBookmarksCoordinates';
import { GetCorporationsCorporationIdBookmarksItem } from './getCorporationsCorporationIdBookmarksItem';


/**
 * 200 ok object
 */
export interface GetCorporationsCorporationIdBookmarks200Ok { 
    /**
     * bookmark_id integer
     */
    bookmark_id: number;
    coordinates?: GetCorporationsCorporationIdBookmarksCoordinates;
    /**
     * created string
     */
    created: string;
    /**
     * creator_id integer
     */
    creator_id: number;
    /**
     * folder_id integer
     */
    folder_id?: number;
    item?: GetCorporationsCorporationIdBookmarksItem;
    /**
     * label string
     */
    label: string;
    /**
     * location_id integer
     */
    location_id: number;
    /**
     * notes string
     */
    notes: string;
}

