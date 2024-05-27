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
import { GetCorporationsCorporationIdDivisionsWalletWallet } from './getCorporationsCorporationIdDivisionsWalletWallet';
import { GetCorporationsCorporationIdDivisionsHangarHangar } from './getCorporationsCorporationIdDivisionsHangarHangar';


/**
 * 200 ok object
 */
export interface GetCorporationsCorporationIdDivisionsOk { 
    /**
     * hangar array
     */
    hangar?: Array<GetCorporationsCorporationIdDivisionsHangarHangar>;
    /**
     * wallet array
     */
    wallet?: Array<GetCorporationsCorporationIdDivisionsWalletWallet>;
}

