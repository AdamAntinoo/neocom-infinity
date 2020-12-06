import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1FeatureButtonComponent } from './v1-feature-button.component';

describe('V1FeatureButtonComponent', () => {
  let component: V1FeatureButtonComponent;
  let fixture: ComponentFixture<V1FeatureButtonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1FeatureButtonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1FeatureButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
