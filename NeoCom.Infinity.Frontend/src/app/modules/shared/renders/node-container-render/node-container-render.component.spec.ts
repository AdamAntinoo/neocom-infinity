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

import { NodeContainerRenderComponent } from './node-container-render.component';
import { GroupContainer } from '@domain/GroupContainer.domain';

describe('RENDER NodeContainerRenderComponent [Module: SHARED]', () => {
    let fixture: ComponentFixture<NodeContainerRenderComponent>;
    let component: NodeContainerRenderComponent;
    let isolation: SupportIsolationService;

    beforeEach(() => {
        TestBed.configureTestingModule({
            schemas: [NO_ERRORS_SCHEMA],
            declarations: [
                NodeContainerRenderComponent
            ],
        })
            .compileComponents();
        fixture = TestBed.createComponent(NodeContainerRenderComponent);
        component = fixture.componentInstance;
        isolation = TestBed.get(SupportIsolationService);
    });

    // - C O N S T R U C T I O N   P H A S E
    describe('Construction Phase', () => {
        it('Should be created', () => {
            console.log('><[renders/NodeContainerRenderComponent]> should be created');
            expect(component).toBeDefined('component has not been created.');
        });
    });
    // - C O D E   C O V E R A G E   P H A S E
    describe('Code Coverage Phase [getters]', () => {
        it('getNode.success: obtain the contained node', () => {
            const expectedName = isolation.generateRandomString(32);
            const expected = new GroupContainer();
            expected.setTitle(expectedName);
            component.node = expected;
            const obtained = component.getNode() as GroupContainer;
            expect(obtained).toBeDefined();
            expect(obtained.getGroupTitle()).toBe(expectedName);
        });
    });
});
