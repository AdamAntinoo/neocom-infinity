import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1IndustryFittingBuildConfigurationPageComponent } from './v1-industry-fitting-build-configuration-page.component';

describe('V1IndustryFittingBuildConfigurationPageComponent', () => {
  let component: V1IndustryFittingBuildConfigurationPageComponent;
  let fixture: ComponentFixture<V1IndustryFittingBuildConfigurationPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1IndustryFittingBuildConfigurationPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1IndustryFittingBuildConfigurationPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
