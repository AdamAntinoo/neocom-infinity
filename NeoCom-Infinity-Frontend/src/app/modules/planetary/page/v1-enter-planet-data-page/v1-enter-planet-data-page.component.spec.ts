import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1EnterPlanetDataPageComponent } from './v1-enter-planet-data-page.component';

describe('V1EnterPlanetDataPageComponent', () => {
  let component: V1EnterPlanetDataPageComponent;
  let fixture: ComponentFixture<V1EnterPlanetDataPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1EnterPlanetDataPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1EnterPlanetDataPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
