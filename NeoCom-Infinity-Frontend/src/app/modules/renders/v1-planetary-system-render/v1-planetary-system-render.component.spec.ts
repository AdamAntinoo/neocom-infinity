import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1PlanetarySystemRenderComponent } from './v1-planetary-system-render.component';

describe('V1PlanetarySystemRenderComponent', () => {
  let component: V1PlanetarySystemRenderComponent;
  let fixture: ComponentFixture<V1PlanetarySystemRenderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1PlanetarySystemRenderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1PlanetarySystemRenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
