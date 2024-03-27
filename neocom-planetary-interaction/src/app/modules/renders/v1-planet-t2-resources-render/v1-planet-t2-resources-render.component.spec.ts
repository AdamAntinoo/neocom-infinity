import { ComponentFixture, TestBed } from '@angular/core/testing';

import { V1PlanetT2ResourcesRenderComponent } from './v1-planet-t2-resources-render.component';

describe('V1PlanetT2ResourcesRenderComponent', () => {
  let component: V1PlanetT2ResourcesRenderComponent;
  let fixture: ComponentFixture<V1PlanetT2ResourcesRenderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ V1PlanetT2ResourcesRenderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(V1PlanetT2ResourcesRenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
