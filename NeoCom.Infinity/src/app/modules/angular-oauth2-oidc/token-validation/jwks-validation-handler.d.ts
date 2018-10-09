import { AbstractValidationHandler, ValidationParams } from './validation-handler';
/**
 * Validates the signature of an id_token against one
 * of the keys of an JSON Web Key Set (jwks).
 *
 * This jwks can be provided by the discovery document.
 */
export declare class JwksValidationHandler extends AbstractValidationHandler {
    /**
     * Allowed algorithms
     */
    allowedAlgorithms: string[];
    /**
     * Time period in seconds the timestamp in the signature can
     * differ from the current time.
     */
    gracePeriodInSec: number;
    private cyptoObj;
    private textEncoder;
    validateSignature(params: ValidationParams, retry?: boolean): Promise<any>;
    private alg2kty(alg);
    calcHash(valueToHash: string, algorithm: string): Promise<string>;
    toByteArrayAsString(hexString: string): string;
}
