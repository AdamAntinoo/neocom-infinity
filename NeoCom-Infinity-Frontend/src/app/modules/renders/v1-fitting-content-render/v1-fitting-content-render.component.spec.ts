import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1FittingContentRenderComponent } from './v1-fitting-content-render.component';

describe('V1FittingContentRenderComponent', () => {
  let component: V1FittingContentRenderComponent;
  let fixture: ComponentFixture<V1FittingContentRenderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1FittingContentRenderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1FittingContentRenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
