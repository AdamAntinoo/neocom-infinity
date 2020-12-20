import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1BOMGroupRenderComponent } from './v1-bomgroup-render.component';

describe('V1BOMGroupRenderComponent', () => {
  let component: V1BOMGroupRenderComponent;
  let fixture: ComponentFixture<V1BOMGroupRenderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1BOMGroupRenderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1BOMGroupRenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
