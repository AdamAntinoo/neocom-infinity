import { TestBed } from "@angular/core/testing";
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing'
import { HttpClient, HttpClientModule, HttpErrorResponse } from '@angular/common/http'
import { Observable } from "rxjs";

import { ConfigurationAdapter } from "@infra/adapters/outbound/configuration/ConfigurationAdapter";
import { ESISecureDataServiceAdapter } from "@infra/adapters/outbound/esi-data-service/ESISecureDataServiceAdapter";
import { ESIMiningOperation } from "@infra/adapters/outbound/esi-data-service/domain/ESIMiningOperation";
import { NeoComCredential } from "@domain/NeoComCredential.domain";

describe('Feature: [NIF05]-Fetch mining data along the time.', () => {
    let esiAdapter: ESISecureDataServiceAdapter
    let configuration: ConfigurationAdapter
    let credential: NeoComCredential

    let getMiningDataResponse: ESIMiningOperation[]

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [HttpClientModule],
            providers: [
                { provide: ConfigurationAdapter },
                { provide: HttpClient, useClass: HttpClient }
            ]
        }).compileComponents()
        esiAdapter = TestBed.inject(ESISecureDataServiceAdapter)
    })

    describe('[NIF05.01]-Get the initial set of mining data. This is controlled by the character being used for retrieval.', () => {
        it('a valid credential with the next data', () => {
            const dataTable = [
                {
                    uniqueId: 'tranquility/93813310',
                    accountId: 93813310,
                    accountName: 'Perico Tuerto',
                    corporationId: 1427661573
                }
            ]
            const record = dataTable[0]
            credential = new NeoComCredential({ uniqueId: record.uniqueId })
            expect(credential).toBeDefined()
        })
        it('the ESI endpoint {string} is called', async () => {
            const endpoint = 'Get Mining Data'
            switch (endpoint) {
                case "Get Mining Data": {
                    console.log('Get Mining Data-><')
                    const pilotId: number = 34565
                    const sut = esiAdapter.v1_apiEsiMiningOperationsData(pilotId)
                    sut.subscribe((response: ESIMiningOperation[]) => {
                        //     console.log('response->' + JSON.stringify(response))
                        //     expect(response).toBeDefined()
                        //     getMiningDataResponse = response
                    },
                        err => console.log(err))
                    expect(sut).toBeDefined()
                    expect(endpoint).toBeDefined()
                    expect(esiAdapter).toBeDefined()
                }
            }
        })
        xit('And there are 4 mining records', () => {
            expect(getMiningDataResponse).toBeDefined()
            expect(getMiningDataResponse.length).toBe(4)
        })
    })
})
