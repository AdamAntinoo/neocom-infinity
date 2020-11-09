import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1FittingItemRenderComponent } from './v1-fitting-item-render.component';

describe('V1FittingItemRenderComponent', () => {
  let component: V1FittingItemRenderComponent;
  let fixture: ComponentFixture<V1FittingItemRenderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1FittingItemRenderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1FittingItemRenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
