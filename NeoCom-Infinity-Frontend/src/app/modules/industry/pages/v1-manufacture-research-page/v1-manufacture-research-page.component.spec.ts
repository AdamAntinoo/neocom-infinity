import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1ManufactureResearchPageComponent } from './v1-manufacture-research-page.component';

describe('V1ManufactureResearchPageComponent', () => {
  let component: V1ManufactureResearchPageComponent;
  let fixture: ComponentFixture<V1ManufactureResearchPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1ManufactureResearchPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1ManufactureResearchPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
