import { ComponentFixture, TestBed } from '@angular/core/testing';
import { V1BlueprintCategoryRender } from './v1-blueprint-category.component';

describe('V1BlueprintCategoryComponent', () => {
  let component: V1BlueprintCategoryRender;
  let fixture: ComponentFixture<V1BlueprintCategoryRender>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ V1BlueprintCategoryRender ]
    })
    .compileComponents();
  });

//   beforeEach(() => {
//     fixture = TestBed.createComponent(V1BlueprintCategoryRender);
//     component = fixture.componentInstance;
//     fixture.detectChanges();
//   });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
