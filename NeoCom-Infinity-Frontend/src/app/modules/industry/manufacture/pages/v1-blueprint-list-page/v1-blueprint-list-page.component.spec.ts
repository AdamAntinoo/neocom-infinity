import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1BlueprintListPageComponent } from './v1-blueprint-list-page.component';

describe('V1BlueprintListPageComponent', () => {
  let component: V1BlueprintListPageComponent;
  let fixture: ComponentFixture<V1BlueprintListPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1BlueprintListPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1BlueprintListPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
