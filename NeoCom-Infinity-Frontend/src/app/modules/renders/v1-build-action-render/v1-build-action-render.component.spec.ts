import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1BuildActionRenderComponent } from './v1-build-action-render.component';

xdescribe('V1BuildActionRenderComponent', () => {
  let component: V1BuildActionRenderComponent;
  let fixture: ComponentFixture<V1BuildActionRenderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1BuildActionRenderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1BuildActionRenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
