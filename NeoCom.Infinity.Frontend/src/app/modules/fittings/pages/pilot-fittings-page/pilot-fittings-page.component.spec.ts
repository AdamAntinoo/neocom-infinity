// - CORE
import { NO_ERRORS_SCHEMA } from '@angular/core';
// - TESTING
import { async } from '@angular/core/testing';
import { fakeAsync } from '@angular/core/testing';
import { tick } from '@angular/core/testing';
import { ComponentFixture } from '@angular/core/testing';
import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { RouteMockUpComponent } from '@app/testing/RouteMockUp.component';
import { routes } from '@app/testing/RouteMockUp.component';
// - PROVIDERS
import { IsolationService } from '@app/platform/isolation.service';
import { AppStoreService } from '@app/services/appstore.service';
import { BackendService } from '@app/services/backend.service';
import { SupportIsolationService } from '@app/testing/SupportIsolation.service';
import { SupportAppStoreService } from '@app/testing/SupportAppStore.service';
import { SupportBackendService } from '@app/testing/SupportBackend.service';

import { PilotFittingsPageComponent } from './pilot-fittings-page.component';
import { AppInfoPanelComponent } from '@app/modules/header/app-info-panel/app-info-panel.component';
import { ServerInfoPanelComponent } from '@app/modules/header/server-info-panel/server-info-panel.component';

describe('PilotFittingsPageComponent', () => {
    let fixture: ComponentFixture<PilotFittingsPageComponent>;
    let component: PilotFittingsPageComponent;

    beforeEach(() => {
        TestBed.configureTestingModule({
            schemas: [NO_ERRORS_SCHEMA],
            imports: [
                RouterTestingModule.withRoutes(routes)
            ],
            declarations: [
                RouteMockUpComponent,
                PilotFittingsPageComponent,
                AppInfoPanelComponent,
                ServerInfoPanelComponent,
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

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
