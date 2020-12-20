import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1IndustryNodeContainerRenderComponent } from './v1-industry-node-container-render.component';

describe('V1IndustryNodeContainerRenderComponent', () => {
  let component: V1IndustryNodeContainerRenderComponent;
  let fixture: ComponentFixture<V1IndustryNodeContainerRenderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1IndustryNodeContainerRenderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1IndustryNodeContainerRenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
