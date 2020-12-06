import { ComponentFixture, TestBed } from '@angular/core/testing';

import { V1SelectedPlanetsPanelComponent } from './v1-selected-planets-panel.component';

describe('V1SelectedPlanetsPanelComponent', () => {
  let component: V1SelectedPlanetsPanelComponent;
  let fixture: ComponentFixture<V1SelectedPlanetsPanelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ V1SelectedPlanetsPanelComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(V1SelectedPlanetsPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
