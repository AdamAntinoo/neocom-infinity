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

fdescribe('SERVICE BackendService [Module: APP]', () => {
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
    xdescribe('Code Coverage Phase [apiValidateAuthorizationToken_v1]', () => {
        it('apiValidateAuthorizationToken_v1.success: get a validated authorization from the mocked server',
            async(inject([HttpTestingController, BackendService], (backend: HttpTestingController, service: BackendService) => {
                const validationResponseJson = {
                    "responseType": "ValidateAuthorizationTokenResponse",
                    "jwtToken": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJFU0kgT0F1dGgyIEF1dGhlbnRpY2F0aW9uIiwiY29ycG9yYXRpb25JZCI6OTgzODQ3MjYsImFjY291bnROYW1lIjoiQmV0aCBSaXBsZXkiLCJpc3MiOiJOZW9Db20uSW5maW5pdHkuQmFja2VuZCIsInVuaXF1ZUlkIjoidHJhbnF1aWxpdHkvOTIyMjM2NDciLCJwaWxvdElkIjo5MjIyMzY0N30.Qom8COyZB2sW3bCGm9pnGcIOqw-E2yKDsmGklQW6r9Fhu8jJpkNUv5TUhU2cJjIg5jX3082bZ6eKtRZ3z10vGw",
                    "credential": {
                        "jsonClass": "Credential",
                        "uniqueId": "tranquility/92223647",
                        "accountId": 92223647,
                        "accountName": "Adam Antinoo",
                        "assetsCount": 1476,
                        "walletBalance": 6.309543632E8,
                        "raceName": "Amarr",
                        "dataSource": "tranquility"
                    }
                };
                service.apiValidateAuthorizationToken_v1('-ANY-CODE-', '-ANY-STATE-',
                    new ResponseTransformer().setDescription('Do response transformation to "ValidateAuthorizationTokenResponse".')
                        .setTransformation((data: any): ValidateAuthorizationTokenResponse => {
                            return new ValidateAuthorizationTokenResponse(data);
                        }))
                    .subscribe(response => {
                        console.log('--[apiValidateAuthorizationToken_v1]> response: ' + JSON.stringify(response));
                        expect(response).toBeDefined();
                        expect(response.getResponseType()).toContain('ValidateAuthorizationTokenResponse');
                    });
                backend.match((request) => {
                    return request.url.match(/validateAuthorizationToken/) &&
                        request.method === 'GET';
                })[0].flush(validationResponseJson);
                backend.verify();
            })));
    });
    describe('Code Coverage Phase [apiGetServerInfo_v1]', () => {
        it('apiGetServerInfo_v1.success: get the server info from the mocked server',
            async(inject([HttpTestingController, BackendService], (backend: HttpTestingController, service: BackendService) => {
                const responseJson = {
                    "players": 10528,
                    "server_version": "1585794",
                    "start_time": "2019-10-16T11:06:17Z"
                };
                service.apiGetServerInfo_v1(new ResponseTransformer().setDescription('Do response transformation to "ServerStatus".')
                    .setTransformation((data: any): ServerStatus => {
                        return new ServerStatus(data);
                    }))
                    .subscribe(response => {
                        console.log('--[apiGetServerInfo_v1]> response: ' + JSON.stringify(response));
                        expect(response).toBeDefined();
                        expect(response.getJsonClass()).toContain('ServerStatus');
                    });
                backend.match((request) => {
                    return request.url.match(/server/) &&
                        request.method === 'GET';
                })[0].flush(responseJson);
                backend.verify();
            })));
    });
    describe('Code Coverage Phase [apiGetCorporationPublicData_v1]', () => {
        it('apiGetCorporationPublicData_v1.success: get the corporation data from the mocked server',
            async(inject([HttpTestingController, BackendService], (backend: HttpTestingController, service: BackendService) => {
                const responseJson = appStoreService.directAccessMockResource('corporations');
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
                backend.match((request) => {
                    return request.url.match(/corporations/) &&
                        request.method === 'GET';
                })[0].flush(responseJson);
                backend.verify();
            })));
    });
    describe('Code Coverage Phase [apiGetPilotPublicData_v1]', () => {
        it('apiGetPilotPublicData_v1.success: get the pilot data from the mocked server',
            async(inject([HttpTestingController, BackendService], (backend: HttpTestingController, service: BackendService) => {
                const responseJson = appStoreService.directAccessMockResource('pilots');
                service.apiGetPilotPublicData_v1(123)
                    .subscribe((response: Pilot) => {
                        console.log('--[apiGetCorporationPublicData_v1]> response: ' + JSON.stringify(response));
                        expect(response).toBeDefined();
                        expect(response.getJsonClass()).toContain('Pilot');
                    });
                backend.match((request) => {
                    return request.url.match(/pilots/) &&
                        request.method === 'GET';
                })[0].flush(responseJson);
                backend.verify();
            })));
    });
    fdescribe('Code Coverage Phase [apiGetPilotFittings_v1]', () => {
        it('apiGetPilotFittings_v1.success: get the list of the pilot fittings', () => {
            // async(inject([HttpTestingController, BackendService], (backend: HttpTestingController, service: BackendService) => {
            // Load into a constant the JSON definition for the mock fittings.
            const fittingResponseJson = isolationService.directAccessMockResource('pilot.fittings');
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
            // backend.match((request) => {
            //     return request.url.match(/validateAuthorizationToken/) &&
            //         request.method === 'GET';
            // })[0].flush(fittingResponseJson);
            // backend.verify();
        });
    });
});
