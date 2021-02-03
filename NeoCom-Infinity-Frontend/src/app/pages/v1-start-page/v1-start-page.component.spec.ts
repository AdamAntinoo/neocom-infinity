import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1StartPageComponent } from './v1-start-page.component';

describe('V1StartPageComponent', () => {
  let component: V1StartPageComponent;
  let fixture: ComponentFixture<V1StartPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1StartPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1StartPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
