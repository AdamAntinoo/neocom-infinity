import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1LoyaltyCorporationRenderComponent } from './v1-loyalty-corporation-render.component';

xdescribe('V1LoyaltyCorporationRenderComponent', () => {
  let component: V1LoyaltyCorporationRenderComponent;
  let fixture: ComponentFixture<V1LoyaltyCorporationRenderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1LoyaltyCorporationRenderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1LoyaltyCorporationRenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
