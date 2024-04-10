import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SpinnerPanelComponent } from './spinner-panel.component';

xdescribe('SpinnerPanelComponent', () => {
  let component: SpinnerPanelComponent;
  let fixture: ComponentFixture<SpinnerPanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SpinnerPanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SpinnerPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
