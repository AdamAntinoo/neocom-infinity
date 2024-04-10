// - CORE
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { Subject } from 'rxjs';
// - TESTING
import { async } from '@angular/core/testing';
import { fakeAsync } from '@angular/core/testing';
import { tick } from '@angular/core/testing';
import { ComponentFixture } from '@angular/core/testing';
import { inject } from '@angular/core/testing';
import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { RouteMockUpComponent, routes } from '@app/testing/RouteMockUp.component';
// - PROVIDERS
import { TokenAuthorizationGuard } from './token-authorization.guard';
import { AuthenticationService } from './authentication.service';
import { SupportAuthenticationService } from '@app/testing/SupportAuthentication.service';
import { IsolationService } from '@innovative/services/isolation.service';
import { SupportIsolationService } from '@app/testing/SupportIsolation.service';

describe('SERVICE AuthenticationService [Module: CORE]', () => {
    // let 
    let authenticationService: SupportAuthenticationService;

    beforeEach(() => {
        TestBed.configureTestingModule({
            schemas: [NO_ERRORS_SCHEMA],
            imports: [
                RouterTestingModule.withRoutes(routes)
            ],
            providers: [
                RouteMockUpComponent,
                TokenAuthorizationGuard,
                { provide: IsolationService, useClass: SupportIsolationService },
                { provide: AuthenticationService, useClass: SupportAuthenticationService }]
        })
            .compileComponents();
        authenticationService = TestBed.get(AuthenticationService);
    });
    // - C O N S T R U C T I O N   P H A S E
    describe('Construction Phase', () => {
        it('TokenAuthorizationGuard exists', inject([TokenAuthorizationGuard], (guard: TokenAuthorizationGuard) => {
            console.log('><[security/TokenAuthorizationGuard.success]> The guard should exist');
            expect(guard).toBeTruthy('The guard should exist.');
        }));
    });

    // - G U A R D   O P E R A T I O N
    describe('GUARD TokenAuthorizationGuard.canActivate', () => {
        it('TokenAuthorizationGuard.canActivate.true', inject([TokenAuthorizationGuard], (guard: TokenAuthorizationGuard) => {
            console.log('><[security/TokenAuthorizationGuard.canActivate.true]> The guard should allow access');
            authenticationService.setResult(true);
            const obtained = guard.canActivate(null, null);
            expect(obtained).toBe(true, 'pass true because token validation is true.');
        }));
        it('TokenAuthorizationGuard.canActivate.false', inject([TokenAuthorizationGuard], (guard: TokenAuthorizationGuard) => {
            console.log('><[security/TokenAuthorizationGuard.canActivate.false]> The guard should allow access');
            authenticationService.setResult(false);
            const obtained = guard.canActivate(null, null);
            expect(obtained).toBe(false, 'pass false because token validation fails.');
        }));
    });
    describe('GUARD TokenAuthorizationGuard.canActivateChild', () => {
        it('TokenAuthorizationGuard.canActivateChild.true', inject([TokenAuthorizationGuard], (guard: TokenAuthorizationGuard) => {
            console.log('><[security/TokenAuthorizationGuard.canActivateChild.true]> The guard should allow access');
            const obtained = guard.canActivateChild(null, null);
            expect(obtained).toBe(true, 'pass true because result hardcoded.');
        }));
    });
    describe('GUARD TokenAuthorizationGuard.canLoad', () => {
        it('TokenAuthorizationGuard.canLoad.true', inject([TokenAuthorizationGuard], (guard: TokenAuthorizationGuard) => {
            console.log('><[security/TokenAuthorizationGuard.canLoad.true]> The guard should allow access');
            const obtained = guard.canLoad(null, []);
            expect(obtained).toBe(true, 'pass true because result hardcoded.');
        }));
    });
});
