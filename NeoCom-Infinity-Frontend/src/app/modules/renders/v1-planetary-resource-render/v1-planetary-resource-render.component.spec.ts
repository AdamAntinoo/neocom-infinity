import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1PlanetaryResourceRenderComponent } from './v1-planetary-resource-render.component';

describe('V1PlanetaryResourceRenderComponent', () => {
  let component: V1PlanetaryResourceRenderComponent;
  let fixture: ComponentFixture<V1PlanetaryResourceRenderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1PlanetaryResourceRenderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1PlanetaryResourceRenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
