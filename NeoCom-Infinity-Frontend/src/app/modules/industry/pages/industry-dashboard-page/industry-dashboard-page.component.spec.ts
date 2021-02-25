import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IndustryDashboardPageComponent } from './industry-dashboard-page.component';

describe('IndustryDashboardPageComponent', () => {
  let component: IndustryDashboardPageComponent;
  let fixture: ComponentFixture<IndustryDashboardPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IndustryDashboardPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IndustryDashboardPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
