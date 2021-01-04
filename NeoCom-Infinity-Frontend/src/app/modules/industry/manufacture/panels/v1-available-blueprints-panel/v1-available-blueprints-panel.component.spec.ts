import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1AvailableBlueprintsPanelComponent } from './v1-available-blueprints-panel.component';

describe('V1AvailableBlueprintsPanelComponent', () => {
  let component: V1AvailableBlueprintsPanelComponent;
  let fixture: ComponentFixture<V1AvailableBlueprintsPanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1AvailableBlueprintsPanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1AvailableBlueprintsPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
