import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1RegionRenderComponent } from './v1-region-render.component';

describe('V1RegionRenderComponent', () => {
  let component: V1RegionRenderComponent;
  let fixture: ComponentFixture<V1RegionRenderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1RegionRenderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1RegionRenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
