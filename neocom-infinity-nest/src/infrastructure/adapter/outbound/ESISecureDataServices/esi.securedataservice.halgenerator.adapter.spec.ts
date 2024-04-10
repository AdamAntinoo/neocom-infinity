import { Test } from "@nestjs/testing"
import { ESISecureDataServiceHALGeneratorAdapter } from "./esi.securedataservice.halgenerator.adapter"

describe('SERVICE ESISecureDataServiceHALGeneratorAdapter [Module: Infrastructure.service]', () => {
    let halGenerator: ESISecureDataServiceHALGeneratorAdapter

    beforeEach(async () => {
        const moduleRef = await Test.createTestingModule({
            providers: [
                { provide: ESISecureDataServiceHALGeneratorAdapter, useClass: ESISecureDataServiceHALGeneratorAdapter }
            ],
        }).compile()

        halGenerator = await moduleRef.resolve(ESISecureDataServiceHALGeneratorAdapter);
    })

    describe('constructor contract', () => {
        test('should be created', () => {
            expect(halGenerator).toBeDefined()
        })
    })
    describe('endpoints', () => {
        test('when the link for a system is requested', () => {
            const systemId = 33456
            expect(halGenerator.getSystemLink(systemId)).toBe('https://esi.evetech.net/latest/universe/systems/33456/?datasource=tranquility&language=en')
        })
        test('when the link for a type is requested', () => {
            const typeId = 33456
            expect(halGenerator.getTypeLink(typeId)).toBe('https://esi.evetech.net/latest/universe/types/33456/?datasource=tranquility&language=en')
        })
    })
})
