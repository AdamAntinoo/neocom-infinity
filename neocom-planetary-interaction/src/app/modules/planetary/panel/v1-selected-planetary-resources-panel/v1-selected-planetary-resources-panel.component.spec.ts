import { ComponentFixture, TestBed } from '@angular/core/testing';

import { V1SelectedPlanetaryResourcesPanelComponent } from './v1-selected-planetary-resources-panel.component';

describe('V1SelectedPlanetaryResourcesPanelComponent', () => {
  let component: V1SelectedPlanetaryResourcesPanelComponent;
  let fixture: ComponentFixture<V1SelectedPlanetaryResourcesPanelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ V1SelectedPlanetaryResourcesPanelComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(V1SelectedPlanetaryResourcesPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
