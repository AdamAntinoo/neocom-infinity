import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1FittingConfigurationPanelComponent } from './v1-fitting-configuration-panel.component';

describe('V1FittingConfigurationPanelComponent', () => {
  let component: V1FittingConfigurationPanelComponent;
  let fixture: ComponentFixture<V1FittingConfigurationPanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1FittingConfigurationPanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1FittingConfigurationPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
