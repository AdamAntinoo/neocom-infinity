// - CORE
import { NO_ERRORS_SCHEMA } from '@angular/core'
// - TESTING
import { TestBed } from '@angular/core/testing'
import { async } from '@angular/core/testing'
import { HttpTestingController } from '@angular/common/http/testing'
// - PROVIDERS
import { SupportIsolationService } from '@app/testing/SupportIsolation.service'
// - DOMAIN
import { HALLink } from './HALLink.hal'
import { EveItemDto } from '@domain/core/dto/EveItemDto.dto'
import { SupportHALResolver } from '@app/testing/SupportHALResolver.service'
import { HALResolver } from '@app/services/HALResolver.service'
import { HttpClient } from '@angular/common/http'
import { RouteMockUpComponent } from '@app/testing/RouteMockUp.component';
import { routes } from '@app/testing/RouteMockUp.component';
import { IsolationService } from '@innovative/services/isolation.service'

describe('CLASS HALLink [Module: HAL]', () => {
    let isolation: SupportIsolationService
    let resolver: SupportHALResolver

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            schemas: [NO_ERRORS_SCHEMA],
            imports: [
            ],
            declarations: [],
            providers: [
                { provide: IsolationService, useClass: SupportIsolationService },
                { provide: HttpClient, useClass: HttpTestingController },
                { provide: HALResolver, useClass: SupportHALResolver }
            ]
        }).compileComponents()
        isolation = TestBed.inject(SupportIsolationService)
        resolver = TestBed.inject(SupportHALResolver)
    }))

    const testData = {
        "rel": "item",
        "href": "http://localhost:5200/api/v2/universe/items/10872"
    }
    // - C O N S T R U C T I O N   P H A S E
    xdescribe('Construction Phase', () => {
        // it('constructor.none: validate initial state without constructor', () => {
        //     const instance = new HALLink<EveItemDto>()
        //     const instanceAsAny = instance as any
        //     expect(instance).toBeDefined()
        //     expect(instanceAsAny.downloaded).toBeFalse()
        //     expect(instanceAsAny.rel).toBeUndefined()
        //     expect(instanceAsAny.href).toBeUndefined()
        //     expect(instanceAsAny.target).toBeUndefined()
        // })
        // it('constructor.none: validate initial state with object data', () => {
        //     const instance = new HALLink<EveItemDto>(testData)
        //     const instanceAsAny = instance as any
        //     expect(instance).toBeDefined()
        //     expect(instanceAsAny.downloaded).toBeFalse()
        //     expect(instanceAsAny.rel).toBe('item')
        //     expect(instanceAsAny.href).toBe('http://localhost:5200/api/v2/universe/items/10872')
        //     expect(instanceAsAny.target).toBeUndefined()
        // })
    })
    // - G E T T E R   P H A S E
    describe('Getter Phase', () => {
        xit('Validate defined getters', () => {
            // const expected = isolation.generateRandomString(32)
            // const expectedUrl = isolation.generateRandomString(64)
            // const instance = new HALLink({
            //     "rel": expected,
            //     "href": expectedUrl
            // })
            // expect(instance).toBeDefined()
            // expect(instance.isDownloaded()).toBeFalse()
            // expect(instance.getRelation()).toBe(expected)
            // expect(instance.getHref()).toBe(expectedUrl)
            // expect(instance.getTarget()).toBeUndefined()
        })
    })
    // - C O V E R A G E   P H A S E
    describe('Coverage Phase [Methods]', () => {
        // it('access.success.downloaded: resolve the internal link and access the HAL instance.', () => {
        //     const instance = new HALLink(testData)
        //     const instanceAsAny = instance as any
        //     const expected = isolation.generateRandomString(64)
        //     expect(instance).toBeDefined()
        //     instanceAsAny.downloaded = true
        //     instanceAsAny.target = { message: expected }
        //     jasmine.clock().install()
        //     instance.access(resolver).then(target => {
        //         expect(target['message']).toBe(expected)
        //     })
        //     jasmine.clock().tick(500)
        //     jasmine.clock().uninstall()
        // })
        // it('access.success.not.downloaded: resolve the internal link and access the HAL instance.', () => {
        //     const instance = new HALLink(testData)
        //     const instanceAsAny = instance as any
        //     expect(instance).toBeDefined()
        //     jasmine.clock().install()
        //     instance.access(resolver).then(target => {
        //         console.log('>Target: '+ JSON.stringify(target))
        //         expect(target['name']).toBe('Venture')
        //     })
        //     jasmine.clock().tick(500)
        //     jasmine.clock().uninstall()
        // })
        // it('access.failure.not.downloaded: resolve the internal link and access the HAL instance.', () => {
        //     const instance = new HALLink(testData)
        //     const instanceAsAny = instance as any
        //     instanceAsAny.href = '-INVALID-'
        //     expect(instance).toBeDefined()
        //     jasmine.clock().install()
        //     instance.access(resolver).then(target => {
        //         console.log('>Target: '+ JSON.stringify(target))
        //         expect(target['name']).toBeUndefined()
        //     })
        //     jasmine.clock().tick(500)
        //     jasmine.clock().uninstall()
        // })
    })
})
