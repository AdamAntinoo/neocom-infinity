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

import { V1GroupContainerRenderComponent } from './v1-group-container-render.component';
import { GroupContainer } from '@domain/GroupContainer.domain';
import { AssetGroupIconReference } from '@domain/interfaces/IIconReference.interface';

describe('RENDER V1GroupContainerRenderComponent [Module: SHARED]', () => {
    let fixture: ComponentFixture<V1GroupContainerRenderComponent>;
    let component: V1GroupContainerRenderComponent;
    let isolation: SupportIsolationService;

    beforeEach(() => {
        TestBed.configureTestingModule({
            schemas: [NO_ERRORS_SCHEMA],
            // imports: [
            //     RouterTestingModule.withRoutes(routes)
            // ],
            declarations: [
                // RouteMockUpComponent,
                V1GroupContainerRenderComponent
            ],
            // providers: [
            //     { provide: IsolationService, useClass: SupportIsolationService },
            //     { provide: AppStoreService, useClass: SupportAppStoreService },
            //     { provide: BackendService, useClass: SupportBackendService }
            // ]
        })
            .compileComponents();
        fixture = TestBed.createComponent(V1GroupContainerRenderComponent);
        component = fixture.componentInstance;
        isolation = TestBed.get(SupportIsolationService);
    });

    // - C O N S T R U C T I O N   P H A S E
    describe('Construction Phase', () => {
        it('Should be created', () => {
            console.log('><[renders/V1GroupContainerRenderComponent]> should be created');
            expect(component).toBeDefined('component has not been created.');
        });
    });
    // - C O D E   C O V E R A G E   P H A S E
    describe('Code Coverage Phase [getters]', () => {
        it('getTitle.success: obtain the group title from the component contained node', () => {
            const expected = isolation.generateRandomString(32);
            const group = new GroupContainer();
            group.setTitle(expected);
            component.node = group;
            const obtained = component.getTitle();
            expect(obtained).toBe(expected);
        });
        it('getGroupIconReference.success: obtain the icon path from the contained node', () => {
            const expectedName = isolation.generateRandomString(32);
            const expected = AssetGroupIconReference.FITTING_SHIP_ASSET_LOCATION + expectedName.toLowerCase() + '.png';
            const icon = new AssetGroupIconReference(expectedName);
            const group: GroupContainer = new GroupContainer();
            group.setGroupIcon(icon);
            const obtained = group.getGroupIconReference();
            expect(obtained).toBe(expected);
        });
        it('getContentsCount.success: obtain the content of the contained node', () => {
            const group = new GroupContainer();
            group.addContent(new GroupContainer());
            group.addContent(new GroupContainer());
            group.addContent(new GroupContainer());
            component.node = group;
            let obtained = component.getContentsCount();
            expect(obtained).toBe(3);
        });
    });
});
