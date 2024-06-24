// - CORE
import { NO_ERRORS_SCHEMA } from '@angular/core';
// import { platformconstants } from '../platform/platform-constants';
// - TESTING
import { async } from '@angular/core/testing';
import { fakeAsync } from '@angular/core/testing';
import { inject } from '@angular/core/testing';
import { tick } from '@angular/core/testing';
import { ComponentFixture } from '@angular/core/testing';
import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { RouteMockUpComponent } from '../../../testing/RouteMockUp.component';
import { routes } from '../../../testing/RouteMockUp.component';
// - STORAGE
import { LOCAL_STORAGE, StorageServiceModule, WebStorageService } from 'ngx-webstorage-service';
// - PROVIDERS
import { IsolationService } from './isolation.service';
import { ToastrService } from 'ngx-toastr';
import { SupportLocalStorage } from '../testing/SupportLocalStorage.service';
import { SupportToastrService } from '../testing/SupportToastrService.service';
// - DOMAIN
// import { Feature } from '@domain/Feature.domain';
import { HttpErrorResponse } from '@angular/common/http';
// import { ToastrTestingModule } from '@app/testing/ToastrTestingModule.mock';
// import { SupportToastConfig } from '@app/testing/SupportToastConfig.service';

describe('SERVICE IsolationService [Module: PLATFORM]', () => {
    const TEST_MESSAGE = '-TEST_MESSAGE-';
    const TEST_TITLE = '-TEST_TITLE-';
    let service: IsolationService;
    let notifier: ToastrService;

    beforeEach(() => {
        TestBed.configureTestingModule({
            schemas: [NO_ERRORS_SCHEMA],
            imports: [
                RouterTestingModule.withRoutes(routes),
                StorageServiceModule,
                // ToastrModule.forRoot()
            ],
            declarations: [
                RouteMockUpComponent,
                // ToastrModuleComponent
            ],
            providers: [
                { provide: WebStorageService, useClass: StorageServiceModule },
                { provide: LOCAL_STORAGE, useClass: SupportLocalStorage },
                { provide: ToastrService, useClass: SupportToastrService },
                // ToastrModuleService
                // {provide: ToastrConfig, useClass: ToastrConfig}
            ]
        })
            .compileComponents();
        notifier = TestBed.inject(ToastrService);
        service = TestBed.get(IsolationService);
    });

    // - C O N S T R U C T I O N   P H A S E
    describe('Construction Phase', () => {
        it('constructor.none: validate initial state without constructor', () => {
            console.log('><[core/IsolationService]> should be created');
            expect(service).toBeTruthy('service has not been created.');
        });
    });
    // - C O D E   C O V E R A G E   P H A S E
    describe('Code Coverage Phase [JWT]', () => {
        it('JWTDecode: check JWT decode api', () => {
            const token = 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJFU0kgT0F1dGgyIEF1dGhlbnRpY2F0aW9uIiwiY29ycG9yYXRpb25JZCI6OTgzODQ3MjYsImFjY291bnROYW1lIjoiVGVzdGluZyBDaGFyYWN0ZXIgQWNjb3VudCIsImlzcyI6Ik5lb0NvbS5JbmZpbml0eS5CYWNrZW5kIiwidW5pcXVlSWQiOiJ0cmFucXVpbGl0eS85MzgxMzMxMCIsInBpbG90SWQiOjkzODEzMzEwfQ.eJvBC2144s7sKv5rxSUVEjNbP2BpQJlJhmTOu4AJ9eJj9so_WcrAthbvwgYM4BqyBSNZAjw7bVegieWqx8IX8Q';
            expect(service.JWTDecode(token)).toBeDefined();
        });
    });
    describe('Code Coverage Phase [Exceptions]', async function () {
        it('processException.404: report detected exception', () => {
            const exception = new HttpErrorResponse({
                error: {
                    status: 404
                }
            })
            spyOn(service, 'errorNotification')
            service.processException(exception)
            expect(service.errorNotification).toHaveBeenCalled()
        });
        it('processException.http: report detected exception', () => {
            const exception = new HttpErrorResponse({
                error: {
                    status: 500,
                    errorName: "EXCEPTION",
                    httpStatus: "500 EXCEPTION",
                    message: "-EXCEPTION-MESSAGE-"
                }
            })
            spyOn(service, 'errorNotification')
            service.processException(exception)
            expect(service.errorNotification).toHaveBeenCalled()
        });
        it('processException.cause: report detected exception', () => {
            const exception = new HttpErrorResponse({
                error: {
                    status: 500,
                    errorName: "EXCEPTION",
                    httpStatus: "500 EXCEPTION",
                    message: "-EXCEPTION-MESSAGE-",
                    cause: "-EXCEPTION-CAUSE-"
                }
            })
            spyOn(service, 'errorNotification')
            service.processException(exception)
            expect(service.errorNotification).toHaveBeenCalled()
        });
    });
    // describe('Code Coverage Phase [Storage]', async function () {

    //     // it('setToStorage: store an object on local storage', () => {
    //     //     const TEST_DATA: string = '-TEST-DATA-TO-STORE-';
    //     //     expect(service.getFromStorage(DOCK_CURRENT_CONFIGURATION_KEY)).toBeUndefined();
    //     //     service.setToStorage(DOCK_CURRENT_CONFIGURATION_KEY, TEST_DATA);
    //     //     expect(service.getFromStorage(DOCK_CURRENT_CONFIGURATION_KEY)).toBeDefined();
    //     //     expect(service.getFromStorage(DOCK_CURRENT_CONFIGURATION_KEY)).toBe(TEST_DATA);
    //     // });
    //     // it('setToStorageObject: store a serialized object on local storage', () => {
    //     //     expect(service.getFromStorage(DOCK_CURRENT_CONFIGURATION_KEY)).toBeUndefined();
    //     //     const targetFeature = new Object();
    //     //     service.setToStorageObject(DOCK_CURRENT_CONFIGURATION_KEY, targetFeature);
    //     //     expect(service.getFromStorage(DOCK_CURRENT_CONFIGURATION_KEY)).toBeDefined();
    //     //     expect(service.getFromStorage(DOCK_CURRENT_CONFIGURATION_KEY)).toBe(JSON.stringify(targetFeature));
    //     // });
    //     // it('getFromStorage: get a serialized object from local storage', () => {
    //     //     expect(service.getFromStorage(DOCK_CURRENT_CONFIGURATION_KEY)).toBeUndefined();
    //     //     const targetFeature = new Object();
    //     //     service.setToStorageObject(DOCK_CURRENT_CONFIGURATION_KEY, JSON.stringify(targetFeature))
    //     //     expect(service.getFromStorage(DOCK_CURRENT_CONFIGURATION_KEY)).toBeDefined();
    //     //     expect(service.getFromStorage(DOCK_CURRENT_CONFIGURATION_KEY)).toBe(JSON.stringify(JSON.stringify(targetFeature)))
    //     // });
    //     // it('removeFromStorage: remove an storage key', () => {
    //     //     // localStorage = new Map();
    //     //     expect(service.getFromStorage(DOCK_CURRENT_CONFIGURATION_KEY)).toBeUndefined();
    //     //     service.setToStorageObject(DOCK_CURRENT_CONFIGURATION_KEY, new Object());
    //     //     expect(service.getFromStorage(DOCK_CURRENT_CONFIGURATION_KEY)).toBeDefined();
    //     //     const obtained = service.removeFromStorage(DOCK_CURRENT_CONFIGURATION_KEY)
    //     //     expect(obtained).toBeDefined();
    //     //     expect(service.getFromStorage(DOCK_CURRENT_CONFIGURATION_KEY)).toBeNull();
    //     // });
    //     it('getFromSession: check Session api', () => {
    //         const key = service.generateRandomString(12);
    //         const value = service.generateRandomString(12);
    //         expect(service.getFromSession(key)).toBeNull();
    //         service.setToSession(key, value);
    //         expect(service.getFromSession(key)).toBe(value);
    //     });
    //     it('removeFromSession: check Session api', () => {
    //         const key = service.generateRandomString(12);
    //         const value = service.generateRandomString(12);
    //         expect(service.getFromSession(key)).toBeNull();
    //         service.setToSession(key, value);
    //         expect(service.getFromSession(key)).toBe(value);
    //         service.removeFromSession(key);
    //         expect(service.getFromSession(key)).toBeNull();
    //     });
    // });
    // describe('Code Coverage Phase [Notifications]', async function () {
    //     // it('successNotification: show a success notification', () => {
    //     //     service.successNotification(TEST_MESSAGE);
    //     //     expect(notifier.successCount).toBe(1)
    //     //     service.successNotification(TEST_MESSAGE, TEST_TITLE, { autoDismiss: false });
    //     //     expect(notifier.successCount).toBe(2)
    //     // });
    //     // it('errorNotification: show an error notification', () => {
    //     //     service.errorNotification(TEST_MESSAGE);
    //     //     expect(notifier.errorCount).toBe(1)
    //     //     service.errorNotification(TEST_MESSAGE, TEST_TITLE, { autoDismiss: false });
    //     //     expect(notifier.errorCount).toBe(2)
    //     // });
    //     // it('warningNotification: show a warning notification', () => {
    //     //     service.warningNotification(TEST_MESSAGE);
    //     //     expect(notifier.warningCount).toBe(1)
    //     //     service.warningNotification(TEST_MESSAGE, TEST_TITLE, { autoDismiss: false });
    //     //     expect(notifier.warningCount).toBe(2)
    //     // });
    //     // it('infoNotification: show an info notification', () => {
    //     //     service.infoNotification(TEST_MESSAGE);
    //     //     expect(notifier.infoCount).toBe(1)
    //     //     service.infoNotification(TEST_MESSAGE, TEST_TITLE, { autoDismiss: false });
    //     //     expect(notifier.infoCount).toBe(2)
    //     // });
    // });
    describe('Code Coverage Phase [Utilities]', async function () {
        it('dateAdd: add time to a date instance', () => {
            expect(service.dateAdd(new Date(), 'year', 1)).toBeDefined();
            expect(service.dateAdd(new Date(), 'quarter', 1)).toBeDefined();
            expect(service.dateAdd(new Date(), 'month', 1)).toBeDefined();
            expect(service.dateAdd(new Date(), 'week', 1)).toBeDefined();
            expect(service.dateAdd(new Date(), 'day', 1)).toBeDefined();
            expect(service.dateAdd(new Date(), 'hour', 1)).toBeDefined();
            expect(service.dateAdd(new Date(), 'minute', 1)).toBeDefined();
            expect(service.dateAdd(new Date(), 'second', 1)).toBeDefined();
        });
    });
});
