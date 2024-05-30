import { ComponentFixture, TestBed } from '@angular/core/testing'

import { V1BlueprintCategoriesPanel } from './v1-blueprint-categories.component'

describe('V1BlueprintCategoriesComponent', () => {
	let component: V1BlueprintCategoriesPanel
	let fixture: ComponentFixture<V1BlueprintCategoriesPanel>

	beforeEach(async () => {
		await TestBed.configureTestingModule({
			declarations: [V1BlueprintCategoriesPanel],
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
