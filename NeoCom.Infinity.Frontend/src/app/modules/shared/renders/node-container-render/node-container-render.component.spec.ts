import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NodeContainerRenderComponent } from './node-container-render.component';

describe('NodeContainerRenderComponent', () => {
  let component: NodeContainerRenderComponent;
  let fixture: ComponentFixture<NodeContainerRenderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NodeContainerRenderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NodeContainerRenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
