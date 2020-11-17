import { ComponentFixture, TestBed } from '@angular/core/testing';

import { V1ResourceRenderComponent } from './v1-resource-render.component';

describe('V1ResourceRenderComponent', () => {
  let component: V1ResourceRenderComponent;
  let fixture: ComponentFixture<V1ResourceRenderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ V1ResourceRenderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(V1ResourceRenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
