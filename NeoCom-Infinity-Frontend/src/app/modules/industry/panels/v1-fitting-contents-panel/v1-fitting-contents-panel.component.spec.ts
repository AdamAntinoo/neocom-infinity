import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1FittingContentsPanelComponent } from './v1-fitting-contents-panel.component';

describe('V1FittingContentsPanelComponent', () => {
  let component: V1FittingContentsPanelComponent;
  let fixture: ComponentFixture<V1FittingContentsPanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1FittingContentsPanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1FittingContentsPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
