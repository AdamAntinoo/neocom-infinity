// - TESTING
import { TestBed } from '@angular/core/testing';
// - PROVIDERS
import { SupportIsolationService } from '@app/testing/SupportIsolation.service';
// - DOMAIN
import { GroupContainer } from './GroupContainer.domain';
import { AssetGroupIconReference } from './interfaces/IIconReference.interface';

describe('CLASS GroupContainer [Module: DOMAIN]', () => {
    let isolation: SupportIsolationService;
    let groupContainer4Test = {
        "jsonClass": "GroupContainer",
        "id": 60320161,
        "title": "Frigate",
        "contents": []
    };

    beforeEach(() => {
        isolation = TestBed.get(SupportIsolationService);
   });
    // - C O N S T R U C T I O N   P H A S E
    describe('Construction Phase', () => {
        it('Should be created', () => {
            console.log('><[domain/GroupContainer]> should be created');
            expect(new GroupContainer()).toBeTruthy();
        });
        it('Check the class of the created instance', () => {
            console.log('><[domain/GroupContainer]> should be created');
            expect(new GroupContainer().getJsonClass()).toBe('GroupContainer');
        });
        it('Should validate the construction transformation', () => {
            console.log('><[domain/GroupContainer]> Should validate the construction transformation');
            expect(new GroupContainer(groupContainer4Test)).toBeTruthy();
        });
    });
    // - C O D E   C O V E R A G E   P H A S E
    describe('Code Coverage Phase [methods]', () => {
        it('addContent.success: add new contents to the group', () => {
            const group: GroupContainer = new GroupContainer(groupContainer4Test);
            expect(group.getContents().length).toBe(0);
            group.addContent(new GroupContainer(groupContainer4Test));
            expect(group.getContents().length).toBe(1);
        });
    });
    describe('Code Coverage Phase [setters]', () => {
        it('setId.success: check the setting for the group id', () => {
            const expected = isolation.generateRandomNum(100, 200)
            const group: GroupContainer = new GroupContainer(groupContainer4Test);
            group.setId(expected);
            const obtained = group.getId();
            expect(obtained).toBe(expected);
        });
        it('setTitle.success: check the setting for the group title', () => {
            const expected = isolation.generateRandomString(32);
            const group: GroupContainer = new GroupContainer(groupContainer4Test);
            group.setTitle(expected);
            const obtained = group.getGroupTitle();
            expect(obtained).toBe(expected);
        });
        it('setGroupIcon.success: check the group identifier', () => {
            const expectedName = isolation.generateRandomString(32);
            const expected = AssetGroupIconReference.FITTING_SHIP_ASSET_LOCATION + expectedName.toLowerCase() + '.png';
            const group: GroupContainer = new GroupContainer(groupContainer4Test);
            group.setGroupIcon(new AssetGroupIconReference(expectedName));
            const obtained = group.getGroupIconReference();
            expect(obtained).toBe(expected);
        });
    });
    describe('Code Coverage Phase [getters]', () => {
        it('getId.success: check the group identifier', () => {
            let expected = 60320161;
            const group: GroupContainer = new GroupContainer(groupContainer4Test);
            const obtained = group.getId();
            expect(obtained).toBe(expected);
        });
        it('getGroupTitle.success: check the group title', () => {
            let expected = "Frigate";
            const group: GroupContainer = new GroupContainer(groupContainer4Test);
            const obtained = group.getGroupTitle();
            expect(obtained).toBe(expected);
        });
        it('getGroupIconReference.success: check the icon reference', () => {
            let expected = AssetGroupIconReference.FITTING_SHIP_ASSET_LOCATION + 'rookie_64.png' + '.png';
            const group: GroupContainer = new GroupContainer(groupContainer4Test);
            const obtained = group.getGroupIconReference();
            expect(obtained).toBe(expected);
        });
        it('getContentsCount.success: check the number of contents', () => {
            const group4Count: GroupContainer = new GroupContainer();
            let obtained = group4Count.getContentsCount();
            expect(obtained).toBe(0);
            group4Count.addContent(new GroupContainer(groupContainer4Test));
            group4Count.addContent(new GroupContainer(groupContainer4Test));
            obtained = group4Count.getContentsCount();
            expect(obtained).toBe(2);
        });
        it('getContents.success: check the contents', () => {
            const group4Content: GroupContainer = new GroupContainer();
            let obtained = group4Content.getContents();
            expect(obtained.length).toBe(0);
            group4Content.addContent(new GroupContainer(groupContainer4Test));
            group4Content.addContent(new GroupContainer(groupContainer4Test));
            obtained = group4Content.getContents();
            expect(obtained.length).toBe(2);
        });
    });
    // TODO - Pending methods that use services.
});
