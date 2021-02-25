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

describe('PAGE DashboardHomePageComponent [Module: CORE]', () => {
    let component: DashboardHomePageComponent
    let fixture: ComponentFixture<DashboardHomePageComponent>
    // let isolationService: SupportIsolationService

    beforeEach(() => {
        TestBed.configureTestingModule({
            schemas: [NO_ERRORS_SCHEMA],
            declarations: [
                DashboardHomePageComponent,
            ],
            providers: [
                { provide: IsolationService, useClass: SupportIsolationService }
            ]
        })
            .compileComponents()
        fixture = TestBed.createComponent(DashboardHomePageComponent)
        component = fixture.componentInstance
        // isolationService = TestBed.inject<SupportIsolationService>(SupportIsolationService)
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
    describe('Interactions Contract', () => {
        xit('getPilotId.success: get the pilot identifier contained on the credential', () => {
            const isolationService = TestBed.inject<SupportIsolationService>(SupportIsolationService)
            const identifier = isolationService.generateRandomNum(123456, 234567)
            console.log('identifier=' + identifier)
            isolationService.setToSession(PlatformConstants.CREDENTIAL_KEY, new NeocomCredential({ accountId: 123456 }))
            const localComponent = TestBed.createComponent(DashboardHomePageComponent).componentInstance
            expect(localComponent.getPilotId()).toBe(123456)
        });
    })
})
