// - CORE
import { NO_ERRORS_SCHEMA } from '@angular/core'
// - HTTP PACKAGE
import { HttpClientTestingModule } from '@angular/common/http/testing'
import { HttpTestingController } from '@angular/common/http/testing'
// - TESTING
import { async } from '@angular/core/testing'
import { inject } from '@angular/core/testing'
import { TestBed } from '@angular/core/testing'
import { RouterTestingModule } from '@angular/router/testing'
// - INNOVATIVE
import { RouteMockUpComponent } from '@app/testing/RouteMockUp.component'
import { routes } from '@app/testing/RouteMockUp.component'
import { IsolationService } from '@innovative/services/isolation.service'
import { HttpClientWrapperService } from '@innovative/services/httpclientwrapper.service'
import { ResponseTransformer } from '@innovative/services/support/ResponseTransformer'
// - PROVIDERS
import { SupportIsolationService } from '@app/testing/SupportIsolation.service'
import { SupportHttpClientWrapperService } from '@app/testing/SupportHttpClientWrapperService.service'
import { BackendService } from './backend.service'
import { AppStoreService } from './appstore.service'
import { SupportAppStoreService } from '@app/testing/SupportAppStore.service'
import { Pilot } from '@app/domain/Pilot.domain'
import { Corporation } from '@app/domain/Corporation.domain'
import { ServerStatus } from '@domain/esi/ServerStatus.domain'
import { Fitting } from '@app/domain/Fitting.domain'
import { MockHTTPRequestController } from '@app/testing/MockHTTPRequestController'
import { BackendHttpWrapper } from './backend.httpwrapper'
import { AuthenticationStateResponse } from '@domain/dto/AuthenticationStateResponse.dto'

describe('SERVICE BackendService [Module: APP]', () => {
    let service: BackendService
    let isolationService: SupportIsolationService
    let httpService: SupportHttpClientWrapperService
    let httpController: MockHTTPRequestController

    let appStoreService: SupportAppStoreService

    beforeEach(() => {
        TestBed.configureTestingModule({
            schemas: [NO_ERRORS_SCHEMA],
            imports: [
                RouterTestingModule.withRoutes(routes),
                HttpClientTestingModule
            ],
            declarations: [
                RouteMockUpComponent,
            ],
            providers: [
                { provide: IsolationService, useClass: SupportIsolationService },
                { provide: BackendHttpWrapper, useClass: SupportHttpClientWrapperService },
                { provide: MockHTTPRequestController, useClass: MockHTTPRequestController }
            ]
        })
            .compileComponents()
        service = TestBed.inject(BackendService)
        isolationService = TestBed.get(IsolationService)
        appStoreService = TestBed.get(AppStoreService)
        httpService = TestBed.inject<SupportHttpClientWrapperService>(SupportHttpClientWrapperService)
        httpController = TestBed.inject<MockHTTPRequestController>(MockHTTPRequestController)
        expect(httpService).toBeDefined()
        expect(httpController).toBeDefined()
        console.log('>Check instantiation: ' + httpController.check())
    })

    // - C O N S T R U C T I O N   P H A S E
    describe('Construction Contract', () => {
        it('Should be created', () => {
            console.log('><[app/BackendService]> should be created')
            expect(service).toBeTruthy('component has not been created.')
        })
    })

    // - C O D E   C O V E R A G E   P H A S E
    describe('Code Coverage Phase [apiv1_validateAuthenticationState]', () => {
        it('apiv1_ValidateAuthenticatedSession.success: validate the authentication is still valid', async () => {
            console.log('step 01')
             service.apiv1_ValidateAuthtenticationState()
                .subscribe(response => {
                    console.log('step 07')
                    console.log('--[apiv1_validateAuthenticationState]> response: ' + JSON.stringify(response))
                    expect(response).toBeDefined()
                    expect(response.state).toContain('valid')
                })
            console.log('step 05')
            httpController.service('GET', {state:"valid"})
        })
    })
    describe('Code Coverage Phase [apiv1_ValidateAuthorizationToken]', () => {
        it('apiv1_ValidateAuthorizationToken.success: get a validated authorization from the mocked server', () => {
            service.apiv1_ValidateAuthorizationToken('-ANY-CODE-', '-ANY-STATE-', new ResponseTransformer()
                .setDescription('Do response transformation to "ValidateAuthorizationTokenResponse".')
                .setTransformation((data: any): AuthenticationStateResponse => {
                    console.log('--[apiv1_ValidateAuthorizationToken]> transformation data: ' + JSON.stringify(data))
                    return new AuthenticationStateResponse(data)
                }))
                .subscribe(response => {
                    console.log('--[apiv1_ValidateAuthorizationToken]> response: ' + JSON.stringify(response))
                    expect(response).toBeDefined()
                    // expect(response.getResponseType()).toContain('ValidateAuthorizationTokenResponse')
                })
        })
    })
    describe('Code Coverage Phase [apiGetCorporationPublicData_v1]', () => {
        it('apiGetCorporationPublicData_v1.success: get the corporation data from the mocked server', () => {
            const responseJson = isolationService.directAccessMockResource('corporations')
            service.apiGetCorporationPublicData_v1(123,
                new ResponseTransformer().setDescription('Do response transformation to "Corporation".')
                    .setTransformation((data: any): Corporation => {
                        return new Corporation(data)
                    }))
                .subscribe(response => {
                    console.log('--[apiGetCorporationPublicData_v1]> response: ' + JSON.stringify(response))
                    expect(response).toBeDefined()
                    expect(response.getJsonClass()).toContain('Corporation')
                })
        })
    })
    describe('Code Coverage Phase [apiGetPilotPublicData_v1]', () => {
        it('apiGetPilotPublicData_v1.success: get the pilot data from the mocked server', () => {
            const responseJson = isolationService.directAccessMockResource('pilots')
            service.apiGetPilotPublicData_v1(123)
                .subscribe((response: Pilot) => {
                    console.log('--[apiGetCorporationPublicData_v1]> response: ' + JSON.stringify(response))
                    expect(response).toBeDefined()
                    expect(response.getJsonClass()).toContain('Pilot')
                })
        })
    })
    describe('Code Coverage Phase [apiGetPilotFittings_v1]', () => {
        it('apiGetPilotFittings_v1.success: get the list of the pilot fittings', () => {
            service.apiGetPilotFittings_v1(123,
                new ResponseTransformer().setDescription('Do response transformation to "Fitting List".')
                    .setTransformation((entrydata: any): Fitting[] => {
                        let results: Fitting[] = []
                        if (entrydata instanceof Array) {
                            for (let key in entrydata)
                                results.push(new Fitting(entrydata[key]))
                        } else
                            results.push(new Fitting(entrydata))

                        return results
                    }))
                .subscribe(response => {
                    console.log('--[apiGetPilotFittings_v1]> response: ' + JSON.stringify(response))
                    expect(response).toBeDefined()
                    expect(response.length).toBe(9)
                })
        })
    })
})
