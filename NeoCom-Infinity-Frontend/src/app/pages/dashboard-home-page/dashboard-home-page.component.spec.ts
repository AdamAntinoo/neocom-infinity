// - CORE
import { NO_ERRORS_SCHEMA } from '@angular/core';
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
import { HttpClientWrapperService } from '@innovative/services/httpclientwrapper.service';
import { ResponseTransformer } from '@innovative/services/support/ResponseTransformer';
// - PROVIDERS
import { AppStoreService } from '@app/services/appstore.service';
import { BackendService } from '@app/services/backend.service';
import { SupportIsolationService } from '@app/testing/SupportIsolation.service';
import { SupportAppStoreService } from '@app/testing/SupportAppStore.service';
import { SupportBackendService } from '@app/testing/SupportBackend.service';

import { AppInfoPanelComponent } from '@app/modules/header/app-info-panel/app-info-panel.component';
import { ServerInfoPanelComponent } from '@app/modules/header/server-info-panel/server-info-panel.component';
import { DashboardHomePageComponent } from './dashboard-home-page.component';

describe('PAGE DashboardHomePageComponent [Module: CORE]', () => {
    let component: DashboardHomePageComponent;
    let fixture: ComponentFixture<DashboardHomePageComponent>;

    beforeEach(() => {
        TestBed.configureTestingModule({
            schemas: [NO_ERRORS_SCHEMA],
            imports: [
                RouterTestingModule.withRoutes(routes)
            ],
            declarations: [
                RouteMockUpComponent,
                DashboardHomePageComponent,
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
        fixture = TestBed.createComponent(DashboardHomePageComponent);
        component = fixture.componentInstance;
    });
    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
