import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1FittingInfoRenderComponent } from './v1-fitting-info-render.component';

describe('V1FittingInfoRenderComponent', () => {
  let component: V1FittingInfoRenderComponent;
  let fixture: ComponentFixture<V1FittingInfoRenderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1FittingInfoRenderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1FittingInfoRenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
