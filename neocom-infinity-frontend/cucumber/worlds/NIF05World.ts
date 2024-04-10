import { Credential } from "@domain/core/Credential.domain"
import { ESISecureDataServiceAdapter } from "@infra/adapters/outbound/esi-data-service/ESISecureDataServiceAdapter"

export class NIF05World {
    public credential: Credential
    public esiAdapter : ESISecureDataServiceAdapter
}
