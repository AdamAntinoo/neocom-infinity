import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1FittingGroupRenderComponent } from './v1-fitting-group-render.component';

describe('V1FittingGroupRenderComponent', () => {
  let component: V1FittingGroupRenderComponent;
  let fixture: ComponentFixture<V1FittingGroupRenderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1FittingGroupRenderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1FittingGroupRenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
