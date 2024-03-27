import { ComponentFixture, TestBed } from '@angular/core/testing';

import { V1T2GeneratedResourceRenderComponent } from './v1-t2-generated-resource-render.component';

describe('V1T2GeneratedResourceRenderComponent', () => {
  let component: V1T2GeneratedResourceRenderComponent;
  let fixture: ComponentFixture<V1T2GeneratedResourceRenderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ V1T2GeneratedResourceRenderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(V1T2GeneratedResourceRenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
