import { ComponentFixture, TestBed } from '@angular/core/testing';

import { V1BlueprintCategoryComponent } from './v1-blueprint-category.component';

describe('V1BlueprintCategoryComponent', () => {
  let component: V1BlueprintCategoryComponent;
  let fixture: ComponentFixture<V1BlueprintCategoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ V1BlueprintCategoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(V1BlueprintCategoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
