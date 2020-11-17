import { ComponentFixture, TestBed } from '@angular/core/testing';

import { V1T2ClassificationPanelComponent } from './v1-t2-classification-panel.component';

describe('V1T2ClassificationPanelComponent', () => {
  let component: V1T2ClassificationPanelComponent;
  let fixture: ComponentFixture<V1T2ClassificationPanelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ V1T2ClassificationPanelComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(V1T2ClassificationPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
