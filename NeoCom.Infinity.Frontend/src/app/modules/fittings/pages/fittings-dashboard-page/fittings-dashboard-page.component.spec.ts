import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FittingsDashboardPageComponent } from './fittings-dashboard-page.component';

describe('FittingsDashboardPageComponent', () => {
  let component: FittingsDashboardPageComponent;
  let fixture: ComponentFixture<FittingsDashboardPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FittingsDashboardPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FittingsDashboardPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
