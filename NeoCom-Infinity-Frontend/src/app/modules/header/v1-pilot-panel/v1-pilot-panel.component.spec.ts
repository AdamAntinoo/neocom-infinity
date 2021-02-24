import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1PilotPanelComponent } from './v1-pilot-panel.component';

describe('V1PilotPanelComponent', () => {
  let component: V1PilotPanelComponent;
  let fixture: ComponentFixture<V1PilotPanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1PilotPanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1PilotPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
