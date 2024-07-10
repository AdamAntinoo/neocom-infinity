import { ComponentFixture, TestBed } from '@angular/core/testing';

import { V1ExtendedBlueprintRenderComponent } from './v1-extended-blueprint-render.component';

describe('V1ExtendedBlueprintRenderComponent', () => {
  let component: V1ExtendedBlueprintRenderComponent;
  let fixture: ComponentFixture<V1ExtendedBlueprintRenderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ V1ExtendedBlueprintRenderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(V1ExtendedBlueprintRenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
