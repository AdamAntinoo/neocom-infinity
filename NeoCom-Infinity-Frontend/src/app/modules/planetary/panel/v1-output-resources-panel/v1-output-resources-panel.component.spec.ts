import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1OutputResourcesPanelComponent } from './v1-output-resources-panel.component';

describe('V1OutputResourcesPanelComponent', () => {
  let component: V1OutputResourcesPanelComponent;
  let fixture: ComponentFixture<V1OutputResourcesPanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1OutputResourcesPanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1OutputResourcesPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
