import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1LoyaltyCorporationSelectionPanelComponent } from './v1-loyalty-corporation-selection-panel.component';

xdescribe('V1LoyaltyCorporationSelectionPanelComponent', () => {
  let component: V1LoyaltyCorporationSelectionPanelComponent;
  let fixture: ComponentFixture<V1LoyaltyCorporationSelectionPanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1LoyaltyCorporationSelectionPanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1LoyaltyCorporationSelectionPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
