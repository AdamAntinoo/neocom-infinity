import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1ResourceResearchPageComponent } from './v1-resource-research-page.component';

describe('V1ResourceResearchPageComponent', () => {
  let component: V1ResourceResearchPageComponent;
  let fixture: ComponentFixture<V1ResourceResearchPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1ResourceResearchPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1ResourceResearchPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
