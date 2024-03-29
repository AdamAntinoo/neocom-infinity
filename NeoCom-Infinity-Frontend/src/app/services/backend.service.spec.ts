// - CORE
import { NO_ERRORS_SCHEMA } from '@angular/core';
// - HTTP PACKAGE
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { HttpTestingController } from '@angular/common/http/testing';
// - TESTING
import { async } from '@angular/core/testing';
import { inject } from '@angular/core/testing';
import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { RouteMockUpComponent } from '@app/testing/RouteMockUp.component';
import { routes } from '@app/testing/RouteMockUp.component';
// - PROVIDERS
import { IsolationService } from '@app/platform/isolation.service';
import { HttpClientWrapperService } from './httpclientwrapper.service';
import { SupportIsolationService } from '@app/testing/SupportIsolation.service';
import { SupportHttpClientWrapperService } from '@app/testing/SupportHttpClientWrapperService.service';
import { BackendService } from './backend.service';

import { ValidateAuthorizationTokenResponse } from '@app/domain/dto/ValidateAuthorizationTokenResponse';
import { AppStoreService } from './appstore.service';
import { SupportAppStoreService } from '@app/testing/SupportAppStore.service';
import { Pilot } from '@app/domain/Pilot.domain';
import { ResponseTransformer } from './support/ResponseTransformer';
import { Corporation } from '@app/domain/Corporation.domain';
import { ServerStatus } from '@app/domain/ServerStatus.domain';
import { Fitting } from '@app/domain/Fitting.domain';

describe('SERVICE BackendService [Module: APP]', () => {
    let service: BackendService;
    let isolationService: SupportIsolationService;
    let httpService: HttpClientWrapperService;

    let appStoreService: SupportAppStoreService;

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
                { provide: HttpClientWrapperService, useClass: SupportHttpClientWrapperService },
            ]
        })
            .compileComponents();
        service = TestBed.get(BackendService);
        isolationService = TestBed.get(IsolationService);
        appStoreService = TestBed.get(AppStoreService);
        httpService = TestBed.get(HttpClientWrapperService);
    });

    // - C O N S T R U C T I O N   P H A S E
    describe('Construction Phase', () => {
        it('Should be created', () => {
            console.log('><[app/BackendService]> should be created');
            expect(service).toBeTruthy('component has not been created.');
        });
    });

    // - C O D E   C O V E R A G E   P H A S E
    describe('Code Coverage Phase [apiValidateAuthorizationToken_v1]', () => {
        it('apiValidateAuthorizationToken_v1.success: get a validated authorization from the mocked server', () => {
            service.apiValidateAuthorizationToken_v1('-ANY-CODE-', '-ANY-STATE-', new ResponseTransformer()
                .setDescription('Do response transformation to "ValidateAuthorizationTokenResponse".')
                .setTransformation((data: any): ValidateAuthorizationTokenResponse => {
                    console.log('--[apiValidateAuthorizationToken_v1]> transformation data: ' + JSON.stringify(data));
                    return new ValidateAuthorizationTokenResponse(data);
                }))
                .subscribe(response => {
                    console.log('--[apiValidateAuthorizationToken_v1]> response: ' + JSON.stringify(response));
                    expect(response).toBeDefined();
                    expect(response.getResponseType()).toContain('ValidateAuthorizationTokenResponse');
                });
        });
    });
    describe('Code Coverage Phase [apiGetServerInfo_v1]', () => {
        it('apiGetServerInfo_v1.success: get the server info from the mocked server', () => {
            service.apiGetServerInfo_v1(new ResponseTransformer()
                .setDescription('Do response transformation to "ServerStatus".')
                .setTransformation((data: any): ServerStatus => {
                    return new ServerStatus(data);
                }))
                .subscribe(response => {
                    console.log('--[apiGetServerInfo_v1]> response: ' + JSON.stringify(response));
                    expect(response).toBeDefined();
                    expect(response.getJsonClass()).toContain('ServerStatus');
                });
        });
    });
    describe('Code Coverage Phase [apiGetCorporationPublicData_v1]', () => {
        it('apiGetCorporationPublicData_v1.success: get the corporation data from the mocked server', () => {
            const responseJson = isolationService.directAccessMockResource('corporations');
            service.apiGetCorporationPublicData_v1(123,
                new ResponseTransformer().setDescription('Do response transformation to "Corporation".')
                    .setTransformation((data: any): Corporation => {
                        return new Corporation(data);
                    }))
                .subscribe(response => {
                    console.log('--[apiGetCorporationPublicData_v1]> response: ' + JSON.stringify(response));
                    expect(response).toBeDefined();
                    expect(response.getJsonClass()).toContain('Corporation');
                });
        });
    });
    describe('Code Coverage Phase [apiGetPilotPublicData_v1]', () => {
        it('apiGetPilotPublicData_v1.success: get the pilot data from the mocked server', () => {
            const responseJson = isolationService.directAccessMockResource('pilots');
            service.apiGetPilotPublicData_v1(123)
                .subscribe((response: Pilot) => {
                    console.log('--[apiGetCorporationPublicData_v1]> response: ' + JSON.stringify(response));
                    expect(response).toBeDefined();
                    expect(response.getJsonClass()).toContain('Pilot');
                });
        });
    });
    describe('Code Coverage Phase [apiGetPilotFittings_v1]', () => {
        it('apiGetPilotFittings_v1.success: get the list of the pilot fittings', () => {
            service.apiGetPilotFittings_v1(123,
                new ResponseTransformer().setDescription('Do response transformation to "Fitting List".')
                    .setTransformation((entrydata: any): Fitting[] => {
                        let results: Fitting[] = [];
                        if (entrydata instanceof Array) {
                            for (let key in entrydata)
                                results.push(new Fitting(entrydata[key]));
                        } else
                            results.push(new Fitting(entrydata));

                        return results;
                    }))
                .subscribe(response => {
                    console.log('--[apiGetPilotFittings_v1]> response: ' + JSON.stringify(response));
                    expect(response).toBeDefined();
                    expect(response.length).toBe(9);
                });
        });
    });
});
