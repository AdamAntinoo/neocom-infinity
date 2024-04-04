import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1OutputResourceRenderComponent } from './v1-output-resource-render.component';

xdescribe('V1OutputResourceRenderComponent', () => {
  let component: V1OutputResourceRenderComponent;
  let fixture: ComponentFixture<V1OutputResourceRenderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1OutputResourceRenderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1OutputResourceRenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
