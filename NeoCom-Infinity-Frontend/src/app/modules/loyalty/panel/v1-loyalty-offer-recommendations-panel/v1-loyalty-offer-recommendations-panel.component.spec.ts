import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1LoyaltyOfferRecommendationsPanelComponent } from './v1-loyalty-offer-recommendations-panel.component';

xdescribe('V1LoyaltyOfferRecommendationsPanelComponent', () => {
  let component: V1LoyaltyOfferRecommendationsPanelComponent;
  let fixture: ComponentFixture<V1LoyaltyOfferRecommendationsPanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1LoyaltyOfferRecommendationsPanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1LoyaltyOfferRecommendationsPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
