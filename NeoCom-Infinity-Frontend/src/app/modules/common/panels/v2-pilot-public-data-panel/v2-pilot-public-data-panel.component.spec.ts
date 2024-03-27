import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V2PilotPublicDataPanelComponent } from './v2-pilot-public-data-panel.component';

describe('PilotPublicDataPavelV2Component', () => {
  let component: V2PilotPublicDataPanelComponent;
  let fixture: ComponentFixture<V2PilotPublicDataPanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V2PilotPublicDataPanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V2PilotPublicDataPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
