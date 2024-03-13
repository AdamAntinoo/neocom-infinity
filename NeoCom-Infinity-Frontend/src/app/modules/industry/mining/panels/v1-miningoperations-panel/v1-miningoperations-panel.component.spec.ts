import { ComponentFixture, TestBed } from '@angular/core/testing';

import { V1MiningoperationsPanelComponent } from './v1-miningoperations-panel.component';

describe('V1MiningoperationsPanelComponent', () => {
  let component: V1MiningoperationsPanelComponent;
  let fixture: ComponentFixture<V1MiningoperationsPanelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ V1MiningoperationsPanelComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(V1MiningoperationsPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
