import { TestBed } from "@angular/core/testing"
import { ESIMiningOperationsTypedRequest } from "./ESIMiningOperationsTypedRequest"
import { ConfigurationAdapter } from "../../inbound/configuration/ConfigurationAdapter"

describe('DTO ESIMiningOperationsTypedRequest [Module: Network]', () => {
    let configuration: ConfigurationAdapter
    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [
                { provide: ConfigurationAdapter }
            ]
        }).compileComponents()
        configuration = TestBed.inject(ConfigurationAdapter)
    })

    it('Should be created', () => {
        const request = new ESIMiningOperationsTypedRequest(configuration)
        expect(request).toBeDefined()
    })
    it('when getting method shoeld be GET', () => {
        const request = new ESIMiningOperationsTypedRequest(configuration)
        expect(request).toBeDefined()
        expect(request.method).toBe('GET')
    })
    it('when prepared then request should match', () => {
        const pilotId: number = 123
        const request = new ESIMiningOperationsTypedRequest(configuration).prepare(pilotId)
        expect(request).toBeDefined()
        expect(request.method).toBe('GET')
        expect(request.request).toBe('https://localhost:9999' + '/characters/' + pilotId + '/miningoperation')
        expect(request.options).toBeUndefined()
    })
    it('when prepared then options should not be defined', () => {
        const pilotId: number = 123
        const request = new ESIMiningOperationsTypedRequest(configuration).prepare(pilotId)
        expect(request).toBeDefined()
        expect(request.options).toBeUndefined()
    })
})
