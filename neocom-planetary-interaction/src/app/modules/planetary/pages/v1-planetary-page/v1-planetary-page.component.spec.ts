import { ComponentFixture, TestBed } from '@angular/core/testing';

import { V1PlanetaryPageComponent } from './v1-planetary-page.component';

describe('V1PlanetaryPageComponent', () => {
  let component: V1PlanetaryPageComponent;
  let fixture: ComponentFixture<V1PlanetaryPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ V1PlanetaryPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(V1PlanetaryPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
