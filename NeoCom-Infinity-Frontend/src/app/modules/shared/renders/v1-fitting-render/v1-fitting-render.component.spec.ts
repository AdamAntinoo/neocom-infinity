import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { V1FittingRenderComponent } from './v1-fitting-render.component';

xdescribe('V1FittingRenderComponent', () => {
    let component: V1FittingRenderComponent;
    let fixture: ComponentFixture<V1FittingRenderComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [V1FittingRenderComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(V1FittingRenderComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
