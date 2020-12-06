import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1PlanetSearchPageComponent } from './v1-planet-search-page.component';

describe('V1PlanetSearchPageComponent', () => {
  let component: V1PlanetSearchPageComponent;
  let fixture: ComponentFixture<V1PlanetSearchPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1PlanetSearchPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1PlanetSearchPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
