import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1BOMResourceRenderComponent } from './v1-bomresource-render.component';

describe('V1BOMResourceRenderComponent', () => {
  let component: V1BOMResourceRenderComponent;
  let fixture: ComponentFixture<V1BOMResourceRenderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1BOMResourceRenderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1BOMResourceRenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
