import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1LoyaltyRecommendationsPageComponent } from './v1-loyalty-recommendations-page.component';

xdescribe('V1LoyaltyRecommendationsPageComponent', () => {
  let component: V1LoyaltyRecommendationsPageComponent;
  let fixture: ComponentFixture<V1LoyaltyRecommendationsPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1LoyaltyRecommendationsPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1LoyaltyRecommendationsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
