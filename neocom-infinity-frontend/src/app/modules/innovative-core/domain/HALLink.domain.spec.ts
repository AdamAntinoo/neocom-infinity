import { V3MiningOperation } from "@domain/industry/V3.MiningOperation.domain";
import { HALLink } from "./HALLink.domain";
import { V2MiningOperation } from "neocom-domain";
import { ESIMiningOperation } from "@infra/adapters/outbound/esi-data-service/domain/ESIMiningOperation";
import { HALResolver } from "@app/services/HALResolver.service";
import { HttpClient, HttpClientModule } from "@angular/common/http";
import { TestBed } from "@angular/core/testing";
import { NO_ERRORS_SCHEMA } from "@angular/core";
import { HALResolverService } from "./HALResolver.service";

fdescribe('CLASS HALLink [Module: DOMAIN]', () => {
    let httpClient: HttpClient

    beforeEach(() => {
        TestBed.configureTestingModule({
            schemas: [NO_ERRORS_SCHEMA],
            imports: [HttpClientModule
                // HttpClientTestingModule
            ],
            providers: [
                { provide: HttpClient, useClass: HttpClient },
            ]
        })
            .compileComponents()
            httpClient = TestBed.inject(HttpClient);
    })

    describe('Construction Phase', () => {
        it('Should be created from type', () => {
            const sut = new HALLink(V3MiningOperation)
            expect(sut).toBeDefined()
            expect(sut instanceof HALLink).toBeTrue()
        })
        it('Should be created from code', () => {
            const sut = new HALLink<V3MiningOperation>(V3MiningOperation)
            expect(sut).toBeDefined()
            expect(sut instanceof HALLink).toBeTrue()
        })
    })
    describe('Builder Phase', () => {
        it('Build from an object', () => {
            const sut: HALLink<V3MiningOperation> = new HALLink.Builder(V3MiningOperation)
                .withResolver(new HALResolverService(httpClient))
                .withReference('-link-')
                .build()
            expect(sut).toBeDefined()
            expect(sut instanceof HALLink).toBeTrue()
            // expect(sut.getTarget() instanceof V3MiningOperation).toBeTrue()
        })
        it('Build fails if missing mandatory field', () => {
            const operation: ESIMiningOperation = new ESIMiningOperation()
            expect(() => new HALLink.Builder(V3MiningOperation)
                .withReference('-link-')
                .build()
            ).toThrow()
        })
        it('Build fails if missing mandatory field', () => {
            const operation: ESIMiningOperation = new ESIMiningOperation()
            expect(() => new HALLink.Builder(V3MiningOperation)
                .withResolver(new HALResolverService(httpClient))
                .build()
            ).toThrow()
        })
        it('Build fails if missing mandatory field', () => {
            const operation: ESIMiningOperation = new ESIMiningOperation()
            expect(() => new HALLink.Builder(V3MiningOperation)
                .build()
            ).toThrow()
        })
    })
    describe('Functionality Phase', () => {
        it('When requested an un resolved link we get an unresolved Promise', () => {
            const operation: ESIMiningOperation = new ESIMiningOperation()
            const link: HALLink<V3MiningOperation> = new HALLink.Builder(V3MiningOperation)
                .withResolver(new HALResolverService(httpClient))
                .withReference('-link-')
                .build()
            expect(link).toBeDefined()
            const sut: Promise<V3MiningOperation> = link.getLink()
            expect(sut).toBeDefined()
            expect(sut instanceof Promise).toBeTrue()
            // expect (  sut.)
        })
    })
})
