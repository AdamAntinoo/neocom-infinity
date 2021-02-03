import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1SystemSelectionPanelComponent } from './v1-system-selection-panel.component';

describe('V1SystemSelectionPanelComponent', () => {
  let component: V1SystemSelectionPanelComponent;
  let fixture: ComponentFixture<V1SystemSelectionPanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1SystemSelectionPanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1SystemSelectionPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
