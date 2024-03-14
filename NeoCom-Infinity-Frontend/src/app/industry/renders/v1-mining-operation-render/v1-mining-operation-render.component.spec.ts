import { ComponentFixture, TestBed } from '@angular/core/testing';

import { V1MiningOperationRenderComponent } from './v1-mining-operation-render.component';

describe('V1MiningOperationRenderComponent', () => {
  let component: V1MiningOperationRenderComponent;
  let fixture: ComponentFixture<V1MiningOperationRenderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ V1MiningOperationRenderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(V1MiningOperationRenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
