import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1SelectedResourcesPanelComponent } from './v1-selected-resources-panel.component';

describe('V1SelectedResourcesPanelComponent', () => {
  let component: V1SelectedResourcesPanelComponent;
  let fixture: ComponentFixture<V1SelectedResourcesPanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1SelectedResourcesPanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1SelectedResourcesPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
