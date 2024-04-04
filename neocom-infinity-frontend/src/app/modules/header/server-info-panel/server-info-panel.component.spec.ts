// - CORE
import { NO_ERRORS_SCHEMA } from '@angular/core'
// - TESTING
import { async } from '@angular/core/testing'
import { fakeAsync } from '@angular/core/testing'
import { tick } from '@angular/core/testing'
import { ComponentFixture } from '@angular/core/testing'
import { TestBed } from '@angular/core/testing'
// - PROVIDERS
import { SupportIsolationService } from '@app/testing/SupportIsolation.service'
import { ServerInfoPanelComponent } from './server-info-panel.component'
import { ServerStatus } from '@domain/esi/ServerStatus.domain'
import { BackendService } from '@app/services/backend.service'
import { SupportBackendService } from '@app/testing/SupportBackend.service'
import { IsolationService } from '@innovative/services/isolation.service'
import { PublicService } from '@app/services/public.service'
import { SupportPublicService } from '@app/testing/SupportPublicService.service'

describe('PANEL ServerInfoPanelComponent [Module: HEADER]', () => {
    let component: ServerInfoPanelComponent
    let fixture: ComponentFixture<ServerInfoPanelComponent>
    let isolationService: SupportIsolationService

    beforeEach(() => {
        TestBed.configureTestingModule({
            schemas: [NO_ERRORS_SCHEMA],
            declarations: [
                ServerInfoPanelComponent,
            ],
            providers: [
                { provide: IsolationService, useClass: SupportIsolationService },
                { provide: PublicService, useClass: SupportPublicService }
            ]
        })
            .compileComponents()
        fixture = TestBed.createComponent(ServerInfoPanelComponent)
        component = fixture.componentInstance
        isolationService = TestBed.get(IsolationService)
    })

    // - C O N S T R U C T I O N   P H A S E
    describe('Construction Phase', () => {
        it('Should be created', () => {
            console.log('><[shared/ServerInfoPanelComponent]> should be created')
            expect(component).toBeDefined('component has not been created.')
        })
        it('Initial state', () => {
            expect(component.serverInfo).toBeUndefined()
            expect(component.downloading).toBeTrue()
        })
    })

    // - C O D E   C O V E R A G E   P H A S E
    describe('Code Coverage Phase [getters]', () => {
        it('getServerName.success: validate the name field', () => {
            const expected = isolationService.generateRandomString(12)
            let componentAsAny = component as any
            componentAsAny.serverInfo = new ServerStatus({ server: expected })
            const obtained = component.getServerName()
            expect(obtained).toBe(expected)
        })
        it('getServerName.failure: validate the name field', () => {
            const obtained = component.getServerName()
            expect(obtained).toBe('-')
        })
        it('getServerStatus.online: validate the server status field', () => {
            let componentAsAny = component as any
            componentAsAny.serverInfo = new ServerStatus()
            const obtained = component.getServerStatus()
            expect(obtained).toBe('ONLINE')
        })
        it('getServerStatus.offline: validate the server status field', () => {
            const obtained = component.getServerStatus()
            expect(obtained).toBe('OFFLINE')
        })
        it('getServerCapsuleers.success: validate the players field', () => {
            const expected = isolationService.generateRandomNum(100, 100000)
            let componentAsAny = component as any
            componentAsAny.serverInfo = new ServerStatus({ players: expected })
            const obtained = component.getServerCapsuleers()
            expect(obtained).toBe(expected)
        })
        it('getServerCapsuleers.failure: validate the players field', () => {
            const obtained = component.getServerCapsuleers()
            expect(obtained).toBe(-1)
        })
        it('getStartedAgo.failure: validate the time ago the game server was started', () => {
            const obtained = component.getStartedAgo()
            expect(obtained).toBe('-')
        })
        it('getStartedAgo.success: validate the time ago the game server was started', () => {
            const expected = isolationService.generateRandomString(30)
            let componentAsAny = component as any
            componentAsAny.serverInfo = new ServerStatus({ startAgo: expected })
            const obtained = component.getStartedAgo()
            expect(obtained).toBe(expected)
        })
        it('getNextDowntime.failure: check the time left until the next downtime', () => {
            const obtained = component.getNextDowntime()
            expect(obtained).toBe('-')
        })
        it('getNextDowntime.success: check the time left until the next downtime', () => {
            const expected = isolationService.generateRandomString(30)
            let componentAsAny = component as any
            componentAsAny.serverInfo = new ServerStatus({ nextDowntime: expected })
            const obtained = component.getNextDowntime()
            expect(obtained).toBe(expected)
        })
        it('getBackendVersion.failure: validate the backend version', () => {
            const obtained = component.getBackendVersion()
            expect(obtained).toBe('-')
        })
        it('getBackendVersion.success: validate the backend version', () => {
            const expected = isolationService.generateRandomString(30)
            let componentAsAny = component as any
            componentAsAny.serverInfo = new ServerStatus({ backendVersion: expected })
            const obtained = component.getBackendVersion()
            expect(obtained).toBe(expected)
        })
        it('getSDEversion.failure: validate the backend SDE version in use', () => {
            const obtained = component.getSDEversion()
            expect(obtained).toBe('-')
        })
        it('getSDEversion.success: validate the backend SDE version in use', () => {
            const expected = isolationService.generateRandomString(30)
            let componentAsAny = component as any
            componentAsAny.serverInfo = new ServerStatus({ SDEVersion: expected })
            const obtained = component.getSDEversion()
            expect(obtained).toBe(expected)
        })
    })
})
