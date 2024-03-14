import { ComponentFixture, TestBed } from '@angular/core/testing';

import { V1MiningOperationsPanelComponent } from './v1-miningoperations-panel.component';

xdescribe('V1MiningoperationsPanelComponent', () => {
    let component: V1MiningOperationsPanelComponent;
    let fixture: ComponentFixture<V1MiningOperationsPanelComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [V1MiningOperationsPanelComponent]
        })
            .compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(V1MiningOperationsPanelComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
