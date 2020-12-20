import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1TopBOMPanelComponent } from './v1-top-bompanel.component';

describe('V1TopBOMPanelComponent', () => {
  let component: V1TopBOMPanelComponent;
  let fixture: ComponentFixture<V1TopBOMPanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ V1TopBOMPanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(V1TopBOMPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
