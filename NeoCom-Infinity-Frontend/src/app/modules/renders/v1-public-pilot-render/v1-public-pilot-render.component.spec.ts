import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1PublicPilotRenderComponent } from './v1-public-pilot-render.component';

xdescribe('V1PublicPilotRenderComponent', () => {
  let component: V1PublicPilotRenderComponent;
  let fixture: ComponentFixture<V1PublicPilotRenderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1PublicPilotRenderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1PublicPilotRenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
