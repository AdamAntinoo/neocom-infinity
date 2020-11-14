// - CORE
import { NO_ERRORS_SCHEMA } from '@angular/core';
// - WEBSTORAGE
// import { StorageServiceModule } from 'angular-webstorage-service';
// import {  InMemoryStorageService } from 'angular-webstorage-service';
// import { WebStorageService } from 'angular-webstorage-service';
// - TESTING
import { async } from '@angular/core/testing';
import { fakeAsync } from '@angular/core/testing';
import { inject } from '@angular/core/testing';
import { tick } from '@angular/core/testing';
import { ComponentFixture } from '@angular/core/testing';
import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { RouteMockUpComponent } from '@app/testing/RouteMockUp.component';
import { routes } from '@app/testing/RouteMockUp.component';
import { StorageServiceModule } from 'ngx-webstorage-service';
import { WebStorageService } from 'ngx-webstorage-service';
// - PROVIDERS
import { IsolationService } from './isolation.service';
// import { ToastrModule } from 'ng6-toastr-notifications';
// import { ToastrManager } from 'ng6-toastr-notifications';
// import { ToastrOptions } from 'ng6-toastr-notifications/lib/toastr.options';

describe('SERVICE IsolationService [Module: PLATFORM]', () => {
    let service: IsolationService;

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
            ],
            providers: [
                { provide: WebStorageService, useClass: StorageServiceModule },
                // { provide: ToastrManager, useClass: ToastrManager },
                // { provide: ToastrService, useClass: ToastrService }
            ]
        })
            .compileComponents();
        service = TestBed.get(IsolationService);
    });

    // - C O N S T R U C T I O N   P H A S E
    describe('Construction Phase', () => {
        it('Should be created', () => {
            console.log('><[app/IsolationService]> should be created');
            expect(service).toBeTruthy('component has not been created.');
        });
    });

    // - C O D E   C O V E R A G E   P H A S E
    describe('Code Coverage Phase [JWT]', () => {
        it('JWTDecode: check JWT decode api', () => {
            const token = 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJFU0kgT0F1dGgyIEF1dGhlbnRpY2F0aW9uIiwiY29ycG9yYXRpb25JZCI6OTgzODQ3MjYsImFjY291bnROYW1lIjoiVGVzdGluZyBDaGFyYWN0ZXIgQWNjb3VudCIsImlzcyI6Ik5lb0NvbS5JbmZpbml0eS5CYWNrZW5kIiwidW5pcXVlSWQiOiJ0cmFucXVpbGl0eS85MzgxMzMxMCIsInBpbG90SWQiOjkzODEzMzEwfQ.eJvBC2144s7sKv5rxSUVEjNbP2BpQJlJhmTOu4AJ9eJj9so_WcrAthbvwgYM4BqyBSNZAjw7bVegieWqx8IX8Q';
            expect(service.JWTDecode(token)).toBeDefined();
        });
    });
    describe('Code Coverage Phase [STORAGE]', () => {
        it('getFromStorage: check Storage api', () => {
            const key = service.generateRandomString(12);
            const value = service.generateRandomString(12);
            expect(service.getFromStorage(key)).toBeNull()
            service.setToStorage(key, value);
            expect(service.getFromStorage(key)).toBe(value);
        });
        it('removeFromStorage: check Storage api', () => {
            const key = service.generateRandomString(12);
            const value = service.generateRandomString(12);
            expect(service.getFromStorage(key)).toBeNull();
            service.setToStorage(key, value);
            expect(service.getFromStorage(key)).toBe(value);
            service.removeFromStorage(key);
            expect(service.getFromStorage(key)).toBeNull();
        });
        it('getFromSession: check Session api', () => {
            const key = service.generateRandomString(12);
            const value = service.generateRandomString(12);
            expect(service.getFromSession(key)).toBeNull();
            service.setToSession(key, value);
            expect(service.getFromSession(key)).toBe(value);
        });
        it('removeFromSession: check Session api', () => {
            const key = service.generateRandomString(12);
            const value = service.generateRandomString(12);
            expect(service.getFromSession(key)).toBeNull();
            service.setToSession(key, value);
            expect(service.getFromSession(key)).toBe(value);
            service.removeFromSession(key);
            expect(service.getFromSession(key)).toBeNull();
        });
    });
});
