import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V2PilotRenderComponent } from './v2-pilot-render.component';

xdescribe('V2PilotRenderComponent', () => {
  let component: V2PilotRenderComponent;
  let fixture: ComponentFixture<V2PilotRenderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V2PilotRenderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V2PilotRenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
