import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1GroupContainerRenderComponent } from './v1-group-container-render.component';

describe('V1GroupContainerRenderComponent', () => {
  let component: V1GroupContainerRenderComponent;
  let fixture: ComponentFixture<V1GroupContainerRenderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1GroupContainerRenderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1GroupContainerRenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
