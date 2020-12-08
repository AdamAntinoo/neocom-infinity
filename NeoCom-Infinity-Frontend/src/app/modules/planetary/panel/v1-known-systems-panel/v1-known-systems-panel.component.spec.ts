import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1KnownSystemsPanelComponent } from './v1-known-systems-panel.component';

describe('V1KnownSystemsPanelComponent', () => {
  let component: V1KnownSystemsPanelComponent;
  let fixture: ComponentFixture<V1KnownSystemsPanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1KnownSystemsPanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1KnownSystemsPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
