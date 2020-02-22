// - CORE
import { NO_ERRORS_SCHEMA } from '@angular/core';
// - TESTING
import { async } from '@angular/core/testing';
import { fakeAsync } from '@angular/core/testing';
import { tick } from '@angular/core/testing';
import { ComponentFixture } from '@angular/core/testing';
import { TestBed } from '@angular/core/testing';
// - PROVIDERS
import { SupportIsolationService } from '@app/testing/SupportIsolation.service';
import { SeparatorComponent } from './separator-render.component';

describe('RENDER SeparatorComponent [Module: SHARED]', () => {
    let fixture: ComponentFixture<SeparatorComponent>;
    let component: SeparatorComponent;
    let isolation: SupportIsolationService;

    beforeEach(() => {
        TestBed.configureTestingModule({
            schemas: [NO_ERRORS_SCHEMA],
            declarations: [
                SeparatorComponent
            ],
        })
            .compileComponents();
        fixture = TestBed.createComponent(SeparatorComponent);
        isolation = TestBed.get(SupportIsolationService);
    });

    // - C O N S T R U C T I O N   P H A S E
    describe('Construction Phase', () => {
        it('Should be created', () => {
            console.log('><[renders/SeparatorComponent]> should be created');
            component = fixture.componentInstance;
           expect(component).toBeDefined('component has not been created.');
        });
    });
    // - C O D E   C O V E R A G E   P H A S E
    describe('Code Coverage Phase [getters]', () => {
        it('getTitle.success: obtain the group title from the component contained node', () => {
            const expected = isolation.generateRandomString(32);
            const group = new GroupContainer();
            group.setTitle(expected);
            component = fixture.componentInstance;
            component.node = group;
            const obtained = component.getTitle();
            expect(obtained).toBe(expected);
        });
    });
});
