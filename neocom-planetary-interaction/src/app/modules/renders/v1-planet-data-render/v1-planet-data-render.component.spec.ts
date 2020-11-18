import { ComponentFixture, TestBed } from '@angular/core/testing';

import { V1PlanetDataRenderComponent } from './v1-planet-data-render.component';

describe('V1PlanetDataRenderComponent', () => {
  let component: V1PlanetDataRenderComponent;
  let fixture: ComponentFixture<V1PlanetDataRenderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ V1PlanetDataRenderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(V1PlanetDataRenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
