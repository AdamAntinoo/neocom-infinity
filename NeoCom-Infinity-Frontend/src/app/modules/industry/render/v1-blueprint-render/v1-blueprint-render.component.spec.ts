import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1BlueprintRenderComponent } from './v1-blueprint-render.component';

describe('V1BlueprintRenderComponent', () => {
  let component: V1BlueprintRenderComponent;
  let fixture: ComponentFixture<V1BlueprintRenderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1BlueprintRenderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1BlueprintRenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
