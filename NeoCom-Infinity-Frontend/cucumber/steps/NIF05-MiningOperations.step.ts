import { Given, When, Before } from "@cucumber/cucumber"
import { NIF05World } from "cucumber/worlds/NIF05World"
import { Observable } from "rxjs";

import { Credential } from "@domain/core/Credential.domain"
import { ConfigurationAdapter } from "@infra/adapters/inbound/configuration/ConfigurationAdapter";
import { ESISecureDataServiceAdapter } from "@infra/adapters/outbound/esi-data-service/ESISecureDataServiceAdapter";
import { MiningOperation } from "@infra/adapters/outbound/esi-data-service/domain/MiningOperation";
// import { HttpClient } from "@angular/common/http";

Given('a valid credential with the next data', function (this: NIF05World, dataTable) {
    const record = dataTable.hashes()[0]
    this.credential = new Credential({ uniqueId: record.uniqueId })
})
When('the ESI endpoint {string} is called', function (endpoint: string) {
    const httpClient = {
        get: (request: string, headers?: object) => {
            console.log('method->' + 'GET')
            console.log('request->' + request)
            console.log('options->' + JSON.stringify(headers))

            return Observable.create((observer) => {
                observer.next([new MiningOperation({
                    characterId: 324
                })]);
                observer.complete();
            })
        }
    }
    this.esiAdapter = new ESISecureDataServiceAdapter(new ConfigurationAdapter(), httpClient as any)
    switch (endpoint) {
        case "Get Mining Data": {
            console.log('Get Mining Data-><')
            const pilotId: number = 34565
            // const sut = esiAdapter.v1_apiEsiMiningOperationsData(pilotId)
            // expect(sut).toBeDefined()
        }
    }
})
// }
