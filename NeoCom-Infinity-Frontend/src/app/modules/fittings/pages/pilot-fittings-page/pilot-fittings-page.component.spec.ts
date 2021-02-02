// - CORE
import { NO_ERRORS_SCHEMA } from '@angular/core';
// - TESTING
import { async } from '@angular/core/testing';
import { fakeAsync } from '@angular/core/testing';
import { tick } from '@angular/core/testing';
import { ComponentFixture } from '@angular/core/testing';
import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
// - PROVIDERS
import { IsolationService } from '@innovative/services/isolation.service';
import { AppStoreService } from '@app/services/appstore.service';
import { BackendService } from '@app/services/backend.service';
import { SupportIsolationService } from '@app/testing/SupportIsolation.service';
import { SupportAppStoreService } from '@app/testing/SupportAppStore.service';
import { SupportBackendService } from '@app/testing/SupportBackend.service';

import { PilotFittingsPageComponent } from './pilot-fittings-page.component';
import { RouteMockUpComponent } from '@innovative/testing/RouteMockUp.component';
import { routes } from '@innovative/testing/RouteMockUp.component';

describe('PAGE PilotFittingsPageComponent [Module: FITTINGS]', () => {
    let fixture: ComponentFixture<PilotFittingsPageComponent>;
    let component: PilotFittingsPageComponent;
    let appStoreService: SupportAppStoreService;

    beforeEach(() => {
        TestBed.configureTestingModule({
            schemas: [NO_ERRORS_SCHEMA],
            imports: [
                RouterTestingModule.withRoutes(routes)
            ],
            declarations: [
                RouteMockUpComponent,
                PilotFittingsPageComponent,
                // AppInfoPanelComponent,
                // ServerInfoPanelComponent,
            ],
            providers: [
                { provide: IsolationService, useClass: SupportIsolationService },
                { provide: AppStoreService, useClass: SupportAppStoreService },
                { provide: BackendService, useClass: SupportBackendService }
            ]
        })
            .compileComponents();
        fixture = TestBed.createComponent(PilotFittingsPageComponent);
        component = fixture.componentInstance;
    });

    // - C O N S T R U C T I O N   P H A S E
    describe('Construction Phase', () => {
        it('Should be created', () => {
            console.log('><[fittings/PilotFittingsPageComponent]> should be created');
            expect(component).toBeDefined('component has not been created.');
        });
    });
    // - L I F E C Y C L E   P H A S E
    xdescribe('Lifecycle Phase', () => {
        it('Lifecycle: OnInit: process pilot fittings', fakeAsync(() => {
            spyOn(component, 'ngOnInit');
            console.log('><[fittings/PilotFittingsPageComponent]> Lifecycle: OnInit: process pilot fittings');
            // Set up the route for a new template.
            component.ngOnInit();
            expect(component.ngOnInit).toHaveBeenCalledTimes(1);
        }));
    });

    // - I N I T I A L I Z A T I O N   P H A S E
    xdescribe('Initialization Phase', () => {
        it('Initialization: check the result for the OnInit', fakeAsync(() => {
            console.log('><[fittings/PilotFittingsPageComponent]> Initialization: check the result for the OnInit. State before');
            expect(component.getVariant()).toEqual('-DEFAULT-')
            expect(component.getNodes2Render()).toBeDefined();
            expect(component.getNodes2Render().length).toBe(0);
            expect(component.isDownloading()).toBeTruthy();
            console.log('><[fittings/PilotFittingsPageComponent]> Initialization: check the result for the OnInit. State after');
            component.ngOnInit();
            // fixture.whenStable().then(() => {
            fixture.detectChanges();
            tick(2000);
            console.log('><[fittings/PilotFittingsPageComponent]> completed awaiting');
            expect(component.getNodes2Render().length).toBe(4);
            // });
        }));
    });
    // - R E N D E R I N G   P H A S E
    // describe('Rendering Phase', () => {
    //     it('Rendering Phase: some panels should be visible', () => {
    //         fixture.detectChanges();
    //         console.log('><[citaciones/CitacionesPageComponent]> "phase" should be -IDENTIFICATION-');
    //         expect(component.phase).toBe('-IDENTIFICATION-', '"phase" should be -IDENTIFICATION-');
    //         console.log('><[citaciones/CitacionesPageComponent]> Rendering Phase: some panels should be visible');
    //         let testPanel = fixture.debugElement.query(By.css('#app-header'));
    //         console.log('><[citaciones/CitacionesPageComponent]> "app-header" should be visible');
    //         expect(testPanel).toBeDefined('"app-header" should be visible');
    //         testPanel = fixture.debugElement.query(By.css('#ui-menu-bar'));
    //         console.log('><[citaciones/CitacionesPageComponent]> "ui-menu-bar" should be visible');
    //         expect(testPanel).toBeDefined('"ui-menu-bar" should be visible');
    //         testPanel = fixture.debugElement.query(By.css('#c-patient-panelv2'));
    //         console.log('><[citaciones/CitacionesPageComponent]> "c-patient-panelv2" should be visible');
    //         expect(testPanel).toBeDefined('"c-patient-panelv2" should be visible');
    //         testPanel = fixture.debugElement.query(By.css('#c-patient-appointments-panel'));
    //         console.log('><[citaciones/CitacionesPageComponent]> "c-patient-appointments-panel" should not be visible');
    //         expect(testPanel).toBeNull('"c-patient-appointments-panel" should not be visible');
    //         testPanel = fixture.debugElement.query(By.css('#c-specialities-panel'));
    //         console.log('><[citaciones/CitacionesPageComponent]> "c-specialities-panel" should not be visible');
    //         expect(testPanel).toBeNull('"c-specialities-panel" should not be visible');
    //         testPanel = fixture.debugElement.query(By.css('#medical-acts-panel'));
    //         console.log('><[citaciones/CitacionesPageComponent]> "medical-acts-panel" should not be visible');
    //         expect(testPanel).toBeNull('"medical-acts-panel" should not be visible');
    //         testPanel = fixture.debugElement.query(By.css('#c-calendar-free-slots-panel'));
    //         console.log('><[citaciones/CitacionesPageComponent]> "c-calendar-free-slots-panel" should not be visible');
    //         expect(testPanel).toBeNull('"c-calendar-free-slots-panel" should not be visible');
    //         testPanel = fixture.debugElement.query(By.css('#c-appointment-list-panel'));
    //         console.log('><[citaciones/CitacionesPageComponent]> "c-appointment-list-panel" should not be visible');
    //         expect(testPanel).toBeNull('"c-appointment-list-panel" should not be visible');
    //     });
    // });

});
