import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1BlueprintBOMPanelComponent } from './v1-blueprint-bompanel.component';

describe('V1BlueprintBOMPanelComponent', () => {
  let component: V1BlueprintBOMPanelComponent;
  let fixture: ComponentFixture<V1BlueprintBOMPanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1BlueprintBOMPanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1BlueprintBOMPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
