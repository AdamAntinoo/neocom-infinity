import { ComponentFixture, TestBed } from '@angular/core/testing'

import { V1BlueprintCategoriesPanel } from './v1-blueprint-categories.component'
import { Router } from 'express'
import { RouterTestingModule } from '@angular/router/testing'

describe('V1BlueprintCategoriesComponent', () => {
	let component: V1BlueprintCategoriesPanel
	let fixture: ComponentFixture<V1BlueprintCategoriesPanel>

	beforeEach(async () => {
		await TestBed.configureTestingModule({
			declarations: [V1BlueprintCategoriesPanel, RouterTestingModule],
		}).compileComponents()
	})

	beforeEach(() => {
		fixture = TestBed.createComponent(V1BlueprintCategoriesPanel)
		component = fixture.componentInstance
		fixture.detectChanges()
	})

	it('should create', () => {
		expect(component).toBeTruthy()
	})
})
