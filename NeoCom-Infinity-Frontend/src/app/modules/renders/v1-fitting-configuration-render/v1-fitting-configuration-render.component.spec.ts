import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1FittingConfigurationRenderComponent } from './v1-fitting-configuration-render.component';

describe('V1FittingConfigurationRenderComponent', () => {
  let component: V1FittingConfigurationRenderComponent;
  let fixture: ComponentFixture<V1FittingConfigurationRenderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1FittingConfigurationRenderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1FittingConfigurationRenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
