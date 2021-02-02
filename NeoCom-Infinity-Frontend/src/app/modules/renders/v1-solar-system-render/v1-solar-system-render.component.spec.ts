import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1SolarSystemRenderComponent } from './v1-solar-system-render.component';

xdescribe('V1SolarSystemRenderComponent', () => {
  let component: V1SolarSystemRenderComponent;
  let fixture: ComponentFixture<V1SolarSystemRenderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1SolarSystemRenderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1SolarSystemRenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
