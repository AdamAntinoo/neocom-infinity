// - CORE
import { NO_ERRORS_SCHEMA } from '@angular/core'
import { NgZone } from '@angular/core'
import { Router } from '@angular/router';
import { Location } from "@angular/common"
// - TESTING
import { async } from '@angular/core/testing';
import { fakeAsync } from '@angular/core/testing';
import { tick } from '@angular/core/testing';
import { ComponentFixture } from '@angular/core/testing';
import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
// - INNOVATIVE
import { RouteMockUpComponent } from '@innovative/testing/RouteMockUp.component';
import { routes } from '@innovative/testing/RouteMockUp.component';
import { IsolationService } from '@innovative/services/isolation.service';
// - PROVIDERS
import { SupportIsolationService } from '@app/testing/SupportIsolation.service'
import { NeoCom } from '@domain/NeoCom.domain'
// - DOMAIN
import { V1StartPageComponent } from './v1-start-page.component'
import { BackendService } from '@app/services/backend.service';
import { SupportBackendService } from '@app/testing/SupportBackend.service';

describe('PANEL V1StartPageComponent [Module: APP]', () => {
    let component: V1StartPageComponent
    let fixture: ComponentFixture<V1StartPageComponent>
    let router: Router
    let location: Location
    let zone: NgZone

    beforeEach(() => {
        TestBed.configureTestingModule({
            schemas: [NO_ERRORS_SCHEMA],
            imports: [
                RouterTestingModule.withRoutes(routes)
            ],
            declarations: [
                RouteMockUpComponent,
                V1StartPageComponent,
            ],
            providers: [
                { provide: IsolationService, useClass: SupportIsolationService },
                { provide: BackendService, useClass: SupportBackendService }
            ]
        })
            .compileComponents()
        zone = TestBed.inject<NgZone>(NgZone)
        router = TestBed.inject<Router>(Router)
        location = TestBed.inject<Location>(Location)
        router.initialNavigation()
        fixture = TestBed.createComponent(V1StartPageComponent)
        component = fixture.componentInstance
    })
    // - C O N S T R U C T I O N   P H A S E
    describe('Construction Contract', () => {
        it('Should be created', () => {
            expect(component).toBeDefined('component has not been created.')
        })
        it('Initial state', () => {
            expect(component.validating).toBeTruthy()
        })
    })
    // - O N I N I A T I Z A T I O N   P H A S E
    describe('On Initialization Phase', async () => {
        it('ngOnInit.success: validate the application session existence and validity', async () => {
            jasmine.clock().install()
            const componentAsAny = component as any
            expect(componentAsAny.backendConnections.length).toBe(0)
            await zone.run(() => component.ngOnInit())
            jasmine.clock().tick(2000)
            expect(componentAsAny.backendConnections.length).toBe(1)
            expect(component.validating).toBeFalsy()
            expect(location.path()).toBe('/dashboard')
            jasmine.clock().uninstall()
        })
    })
})    
