// - CORE
import { NO_ERRORS_SCHEMA } from '@angular/core'
import { NgZone } from '@angular/core'
import { Router } from '@angular/router'
import { Location } from '@angular/common'
// - TESTING
import { async } from '@angular/core/testing'
import { fakeAsync } from '@angular/core/testing'
import { tick } from '@angular/core/testing'
import { ComponentFixture } from '@angular/core/testing'
import { TestBed } from '@angular/core/testing'
import { RouterTestingModule } from '@angular/router/testing'
import { routes } from '@app/testing/RouteMockUp.component'
// - INNOVATIVE
import { RouteMockUpComponent } from '@app/testing/RouteMockUp.component'
import { IsolationService } from '@innovative/services/isolation.service'
// - PROVIDERS
import { SupportIsolationService } from '@app/testing/SupportIsolation.service'
import { NeoCom } from '@domain/NeoCom.domain'
// - DOMAIN
import { AppStoreService } from '@app/services/appstore.service'
import { SupportAppStoreService } from '@app/testing/SupportAppStore.service'
import { PlatformConstants } from '@env/PlatformConstants'
import { NeocomCredential } from '@domain/core/Credential.domain'
import { NeoComException } from '@innovative/domain/NeoComException'
import { ExceptionCodes } from '@app/platform/ExceptionCodes'
import { V1BlueprintListPageComponent } from './v1-blueprint-list-page.component'
import { ProcessedBlueprint } from '@app/industry/domain/v2.ProcessedBlueprint.domain'

describe('PAGE V1BlueprintListPageComponent [Module: INDUSTRY/MANUFACTURE]', () => {
	let component: V1BlueprintListPageComponent
	let fixture: ComponentFixture<V1BlueprintListPageComponent>

	beforeEach(() => {
		TestBed.configureTestingModule({
			schemas: [NO_ERRORS_SCHEMA],
			declarations: [V1BlueprintListPageComponent],
			providers: [
				{ provide: IsolationService, useClass: SupportIsolationService },
				{ provide: AppStoreService, useClass: SupportAppStoreService },
			],
		}).compileComponents()
		fixture = TestBed.createComponent(V1BlueprintListPageComponent)
		component = fixture.componentInstance
	})

	// - C O N S T R U C T I O N   P H A S E
	describe('Construction Phase', () => {
		it('Should be created', () => {
			expect(component).toBeDefined('component has not been created.')
		})
		it('Initial state', () => {
			expect(component.availableBlueprints).toBeUndefined('the view reference should be undefined')
			expect(component.self).toBeDefined('self is defined when constructing the component')
			expect(component.selectedBlueprint).toBeUndefined('there is no blueprint selected')
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
		it('signalBlueprintSelection: external api to select a blueprint (or clear selection) from a component', () => {
			expect(component.selectedBlueprint).toBeUndefined('there is no blueprint selected')
			component.signalBlueprintSelection(new ProcessedBlueprint())
			expect(component.selectedBlueprint).toBeDefined('there is a blueprint selected')
			component.signalBlueprintSelection(undefined)
			expect(component.selectedBlueprint).toBeUndefined('there is no blueprint selected')
		})
	})
})
