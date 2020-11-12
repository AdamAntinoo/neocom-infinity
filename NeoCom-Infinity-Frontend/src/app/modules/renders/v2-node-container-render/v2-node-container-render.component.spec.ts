import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V2NodeContainerRenderComponent } from './v2-node-container-render.component';

describe('V2NodeContainerRenderComponent', () => {
  let component: V2NodeContainerRenderComponent;
  let fixture: ComponentFixture<V2NodeContainerRenderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V2NodeContainerRenderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V2NodeContainerRenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
