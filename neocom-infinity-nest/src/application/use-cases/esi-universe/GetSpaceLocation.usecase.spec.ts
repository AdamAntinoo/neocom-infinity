import { Test } from "@nestjs/testing"
import { GetTypeInformationUseCase } from "./GetTypeInformation.usecase"
import { ESIDataUniverseServicesPort } from "application/ports/ESIDataUniverseServices.port"
import { V1EsiTypeDto, V1SpaceLocationDto } from "neocom-domain"
import { EsiDataUniverseMock } from "mocks/EsiDataUniverse.mock"
import { GetSpaceLocationUseCase } from "./GetSpaceLocation.usecase"

describe('USECASE CapsuleerMiningOperationsUseCase [Module: Application.UseCases]', () => {
    let useCase: GetSpaceLocationUseCase
    let dataServices: ESIDataUniverseServicesPort

    beforeEach(async () => {
        const moduleRef = await Test.createTestingModule({
            providers: [
                { provide: ESIDataUniverseServicesPort, useClass: EsiDataUniverseMock }
            ],
        }).compile()

        dataServices = await moduleRef.resolve(ESIDataUniverseServicesPort)
        useCase = new GetSpaceLocationUseCase(dataServices)
    })

    describe('constructor contract', () => {
        test('should be created', () => {
            expect(useCase).toBeDefined()
        })
    })
    describe('endpoints phase', () => {
        test('when request for an esi region', async () => {
            expect(useCase).toBeDefined()
            const locationId = 10000002
            const sut: Promise<V1SpaceLocationDto> = useCase.esiGetSpaceLocation(locationId)
            expect(sut).toBeDefined()
            await sut.then((response: V1SpaceLocationDto) => {
                expect(response.referenceType).toBe('Region')
                expect(response.regionId).toBe(10000002)
                expect(response.regionName).toBe('The Forge')
                expect(response.constellationId).toBeUndefined()
                expect(response.solarSystemId).toBeUndefined()
            })
        })
        test('when request for an esi constellation', async () => {
            expect(useCase).toBeDefined()
            const locationId = 20000020
            const sut: Promise<V1SpaceLocationDto> = useCase.esiGetSpaceLocation(locationId)
            expect(sut).toBeDefined()
            await sut.then((response: V1SpaceLocationDto) => {
                expect(response.referenceType).toBe('Constellation')
                expect(response.regionId).toBe(10000002)
                expect(response.regionName).toBe('The Forge')
                expect(response.constellationId).toBe(20000020)
                expect(response.constellationName).toBe('Kimotoro')
                expect(response.solarSystemId).toBeUndefined()
            })
        })
        test('when request for an esi system', async () => {
            expect(useCase).toBeDefined()
            const locationId = 30000142
            const sut: Promise<V1SpaceLocationDto> = useCase.esiGetSpaceLocation(locationId)
            expect(sut).toBeDefined()
            await sut.then((response: V1SpaceLocationDto) => {
                expect(response.referenceType).toBe('System')
                expect(response.regionId).toBe(10000002)
                expect(response.regionName).toBe('The Forge')
                expect(response.constellationId).toBe(20000020)
                expect(response.constellationName).toBe('Kimotoro')
                expect(response.solarSystemId).toBe(30000142)
                expect(response.solarSystemName).toBe('Jita')
            })
        })
    })
})
