// - CORE
import { NO_ERRORS_SCHEMA } from '@angular/core'
// - TESTING
import { TestBed } from '@angular/core/testing'
import { async } from '@angular/core/testing'
import { HttpTestingController } from '@angular/common/http/testing'
// - PROVIDERS
// - DOMAIN
import { HALLink } from './HALLink.hal'
import { EsiType } from '@domain/esi/EsiType.esi'

describe('CLASS HALLink [Module: HAL]', () => {
    beforeEach(() => {
    })

    // - C O N S T R U C T I O N   P H A S E
    describe('Construction Contract', () => {
        it('Should be created', () => {
            expect(new HALLink<EsiType>(EsiType)).toBeTruthy()
        })
        it('Initial state', () => {
            const link = new HALLink<EsiType>(EsiType)
            const linkAsAny = link as any
            expect(linkAsAny.downloaded).toBeFalse()
            expect(linkAsAny.rel).toBeUndefined()
            expect(linkAsAny.href).toBeUndefined()
            expect(linkAsAny.factory).toBeDefined()
            expect(link.target).toBeUndefined()
        })
        it('Complete construction', () => {
            const link = new HALLink<EsiType>(EsiType).setContents({
                "rel": "corporation",
                "href": "http://localhost:5220/api/v1/public/corporations/98661092"
            })
            const linkAsAny = link as any
            expect(linkAsAny.downloaded).toBeFalse()
            expect(linkAsAny.rel).toBe("corporation")
            expect(linkAsAny.href).toBe("http://localhost:5220/api/v1/public/corporations/98661092")
            expect(linkAsAny.factory).toBeDefined()
            expect(link.target).toBeUndefined('the target should be undefined until the link is downloaded')
        })
    })
    // - G E T T E R   C O N T R A C T
    describe('Getter Contract - Unresolved link', () => {
        it('isResolved.unresolved: check if the links has been resolved', () => {
            const link = new HALLink<EsiType>(EsiType).setContents({
                "rel": "corporation",
                "href": "http://localhost:5220/api/v1/public/corporations/98661092"
            })
            expect(link.isResolved()).toBeFalse()
        })
        it('isDownloaded.unresolved: check the link has beed completed', () => {
            const link = new HALLink<EsiType>(EsiType).setContents({
                "rel": "corporation",
                "href": "http://localhost:5220/api/v1/public/corporations/98661092"
            })
            expect(link.isDownloaded()).toBeFalse()
        })
        it('getRelation.unresolved: check content for the relation field', () => {
            const link = new HALLink<EsiType>(EsiType).setContents({
                "rel": "corporation",
                "href": "http://localhost:5220/api/v1/public/corporations/98661092"
            })
            expect(link.getRelation()).toBe("corporation")
        })
        it('getHref.unresolved: check content for the link reference field', () => {
            const link = new HALLink<EsiType>(EsiType).setContents({
                "rel": "corporation",
                "href": "http://localhost:5220/api/v1/public/corporations/98661092"
            })
            expect(link.getHref()).toBe("http://localhost:5220/api/v1/public/corporations/98661092")
        })
        it('getTarget.unresolved: check content for the link reference field', () => {
            const link = new HALLink<EsiType>(EsiType).setContents({
                "rel": "corporation",
                "href": "http://localhost:5220/api/v1/public/corporations/98661092"
            })
            expect(link.getTarget()).toBeUndefined()
        })
    })
    describe('Getter Contract - Resolved link', () => {
        it('isResolved.resolved: check if the links has been resolved', () => {
            const link = new HALLink<EsiType>(EsiType).setContents({
                "rel": "corporation",
                "href": "http://localhost:5220/api/v1/public/corporations/98661092"
            })
            expect(link.typeCast({
                "typeId": 11535,
                "name": "Magnetometric Sensor Cluster",
                "description": "Description",
                "marketData": {
                    "rel": "marketData",
                    "href": "http://localhost:5242/api/v1/universe/market/consolidated/byregion/10000043/11535"
                }
            })).toBeDefined()
            expect(link.isResolved()).toBeTrue()
        })
        it('isDownloaded.resolved: check the link has beed completed', () => {
            const link = new HALLink<EsiType>(EsiType).setContents({
                "rel": "corporation",
                "href": "http://localhost:5220/api/v1/public/corporations/98661092"
            })
            expect(link.typeCast({
                "typeId": 11535,
                "name": "Magnetometric Sensor Cluster",
                "description": "Description",
                "marketData": {
                    "rel": "marketData",
                    "href": "http://localhost:5242/api/v1/universe/market/consolidated/byregion/10000043/11535"
                }
            })).toBeDefined()
            expect(link.isDownloaded()).toBeTrue()
        })
        it('getRelation.resolved: check content for the relation field', () => {
            const link = new HALLink<EsiType>(EsiType).setContents({
                "rel": "corporation",
                "href": "http://localhost:5220/api/v1/public/corporations/98661092"
            })
            expect(link.typeCast({
                "typeId": 11535,
                "name": "Magnetometric Sensor Cluster",
                "description": "Description",
                "marketData": {
                    "rel": "marketData",
                    "href": "http://localhost:5242/api/v1/universe/market/consolidated/byregion/10000043/11535"
                }
            })).toBeDefined()
            expect(link.getRelation()).toBe("corporation")
        })
        it('getHref.resolved: check content for the link reference field', () => {
            const link = new HALLink<EsiType>(EsiType).setContents({
                "rel": "corporation",
                "href": "http://localhost:5220/api/v1/public/corporations/98661092"
            })
            expect(link.typeCast({
                "typeId": 11535,
                "name": "Magnetometric Sensor Cluster",
                "description": "Description",
                "marketData": {
                    "rel": "marketData",
                    "href": "http://localhost:5242/api/v1/universe/market/consolidated/byregion/10000043/11535"
                }
            })).toBeDefined()
            expect(link.getHref()).toBe("http://localhost:5220/api/v1/public/corporations/98661092")
        })
        it('getTarget.resolved: check content for the link reference field', () => {
            const link = new HALLink<EsiType>(EsiType).setContents({
                "rel": "corporation",
                "href": "http://localhost:5220/api/v1/public/corporations/98661092"
            })
            expect(link.typeCast({
                "typeId": 11535,
                "name": "Magnetometric Sensor Cluster",
                "description": "Description",
                "marketData": {
                    "rel": "marketData",
                    "href": "http://localhost:5242/api/v1/universe/market/consolidated/byregion/10000043/11535"
                }
            })).toBeDefined()
            expect(link.getTarget()).toBeDefined()
        })
    })
})
