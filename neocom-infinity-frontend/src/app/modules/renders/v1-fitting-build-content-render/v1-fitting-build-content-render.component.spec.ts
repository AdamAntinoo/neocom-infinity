import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1FittingBuildContentRenderComponent } from './v1-fitting-build-content-render.component';

xdescribe('V1FittingBuildContentRenderComponent', () => {
  let component: V1FittingBuildContentRenderComponent;
  let fixture: ComponentFixture<V1FittingBuildContentRenderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1FittingBuildContentRenderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1FittingBuildContentRenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
