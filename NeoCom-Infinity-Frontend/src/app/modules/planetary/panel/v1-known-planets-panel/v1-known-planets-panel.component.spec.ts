import { ComponentFixture, TestBed } from '@angular/core/testing';

import { V1KnownPlanetsPanelComponent } from './v1-known-planets-panel.component';

describe('V1KnownPlanetsPanelComponent', () => {
  let component: V1KnownPlanetsPanelComponent;
  let fixture: ComponentFixture<V1KnownPlanetsPanelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ V1KnownPlanetsPanelComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(V1KnownPlanetsPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
