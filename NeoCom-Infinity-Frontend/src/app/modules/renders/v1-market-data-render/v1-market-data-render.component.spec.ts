import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1MarketDataRenderComponent } from './v1-market-data-render.component';

describe('V1MarketDataRenderComponent', () => {
  let component: V1MarketDataRenderComponent;
  let fixture: ComponentFixture<V1MarketDataRenderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1MarketDataRenderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1MarketDataRenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
