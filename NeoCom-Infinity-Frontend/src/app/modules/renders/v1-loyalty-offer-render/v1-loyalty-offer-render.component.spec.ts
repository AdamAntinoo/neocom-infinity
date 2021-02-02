import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1LoyaltyOfferRenderComponent } from './v1-loyalty-offer-render.component';

describe('V1LoyaltyOfferRenderComponent', () => {
  let component: V1LoyaltyOfferRenderComponent;
  let fixture: ComponentFixture<V1LoyaltyOfferRenderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1LoyaltyOfferRenderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1LoyaltyOfferRenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
