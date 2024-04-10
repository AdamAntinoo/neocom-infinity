// - CORE
import { NO_ERRORS_SCHEMA } from '@angular/core'
// - TESTING
import { async } from '@angular/core/testing'
import { fakeAsync } from '@angular/core/testing'
import { tick } from '@angular/core/testing'
import { ComponentFixture } from '@angular/core/testing'
import { TestBed } from '@angular/core/testing'
import { RouterTestingModule } from '@angular/router/testing'
// - INNOVATIVE
import { IsolationService } from '@innovative/services/isolation.service'
// - PROVIDERS
import { SupportIsolationService } from '@app/testing/SupportIsolation.service'
import { DashboardHomePageComponent } from './dashboard-home-page.component'
import { PlatformConstants } from '@env/PlatformConstants'
import { NeocomCredential } from '@domain/core/Credential.domain'
import { ExceptionCodes } from '@app/platform/ExceptionCodes'
import { NeoComException } from '@innovative/domain/NeoComException'
import { AppStoreService } from '@app/services/appstore.service'
import { SupportAppStoreService } from '@app/testing/SupportAppStore.service'

describe('PAGE DashboardHomePageComponent [Module: CORE]', () => {
    let component: DashboardHomePageComponent
    let fixture: ComponentFixture<DashboardHomePageComponent>

    beforeEach(() => {
        TestBed.configureTestingModule({
            schemas: [NO_ERRORS_SCHEMA],
            declarations: [
                DashboardHomePageComponent,
            ],
            providers: [
                { provide: IsolationService, useClass: SupportIsolationService },
                { provide: AppStoreService, useClass: SupportAppStoreService }
            ]
        })
            .compileComponents()
        fixture = TestBed.createComponent(DashboardHomePageComponent)
        component = fixture.componentInstance
    })

    // - C O N S T R U C T I O N   P H A S E
    describe('Construction Contract', () => {
        it('Should be created', () => {
            expect(component).toBeDefined('component has not been created.')
        })
        it('Initial state', () => {
            expect(component.features).toBeDefined()
            expect(component.features.length).toBe(3)
        })
    })

    // - I N T E R A C T I O N S   P H A S E
    describe('Interactions Phase', () => {
        it('getPilotId.success: read the currect selected pilot identifier', () => {
            const componentAsAny = component as any
            const isolationService = componentAsAny.isolationService
            const appStore = componentAsAny.appStore
            const identifier = isolationService.generateRandomNum(123456, 234567)
            appStore.setPilotId(identifier)
            console.log('identifier=' + identifier)
            isolationService.setToSession(PlatformConstants.CREDENTIAL_KEY, new NeocomCredential({ accountId: identifier }))
            expect(component.getPilotId()).toBe(identifier)
        })
        it('getPilotId.failure: read the currect selected pilot identifier', () => {
            const componentAsAny = component as any
            const appStore = componentAsAny.appStore
            appStore.getPilotId = () => {
                throw new NeoComException()
                    .withCode(ExceptionCodes.AUTHENTICATION_EXCEPTION)
                    .withTitle('Rendering Dashboard Page. No Credential Found.')
                    .withMessage('Unable to display Pilot data. There is no credential available to access data.')
                    .withCause('Unexpected Exception. At this point then should exist a local session valid credential.')
            }
            expect(component.getPilotId()).toBeUndefined()
        })
    })
})
