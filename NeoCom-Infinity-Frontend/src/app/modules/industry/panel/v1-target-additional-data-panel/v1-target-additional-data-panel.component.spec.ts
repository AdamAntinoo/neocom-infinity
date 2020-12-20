import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1TargetAdditionalDataPanelComponent } from './v1-target-additional-data-panel.component';

describe('V1TargetAdditionalDataPanelComponent', () => {
  let component: V1TargetAdditionalDataPanelComponent;
  let fixture: ComponentFixture<V1TargetAdditionalDataPanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1TargetAdditionalDataPanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1TargetAdditionalDataPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
