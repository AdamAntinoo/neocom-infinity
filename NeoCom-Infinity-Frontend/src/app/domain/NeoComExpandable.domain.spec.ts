// - DOMAIN
import { NeoComExpandable } from './NeoComExpandable.domain';

describe('CLASS NeoComExpandable [Module: DOMAIN]', () => {
    // - C O N S T R U C T I O N   P H A S E
    describe('Construction Phase', () => {
        it('Should be created', () => {
            console.log('><[domain/NeoComExpandable]> should be created');
            expect(new NeoComExpandable()).toBeTruthy();
        });
        it('Check the class of the created instance', () => {
            console.log('><[domain/NeoComExpandable]> should be created');
            expect(new NeoComExpandable().getJsonClass()).toBe('NeoCom');
        });
    });
    // - C O D E   C O V E R A G E   P H A S E
    describe('Validating interfaces [IExpandable]', () => {
        it('isExpandable.success: check the collapsing function', () => {
            const node: NeoComExpandable = new NeoComExpandable();
            expect(node.isExpandable()).toBeTruthy();
        });
        it('isExpanded.success: check collapsed state', () => {
            const node: NeoComExpandable = new NeoComExpandable();
            expect(node.isExpanded()).toBeFalsy();
            node.expand();
            expect(node.isExpanded()).toBeTruthy();
            node.collapse();
            expect(node.isExpanded()).toBeFalsy();
        });
        it('collapse.success: check the collapsing function', () => {
            const node: NeoComExpandable = new NeoComExpandable();
            const obtained = node.collapse();
            expect(obtained).toBeFalsy();
            expect(node.isExpanded()).toBeFalsy();
        });
        it('expand.success: check the expanding function', () => {
            const node: NeoComExpandable = new NeoComExpandable();
            const obtained = node.expand();
            expect(obtained).toBeTruthy();
            expect(node.isExpanded()).toBeTruthy();
        });
        it('expand.success: check the expanding function', () => {
            const node: NeoComExpandable = new NeoComExpandable();
            node.collapse();
            expect(node.isExpanded()).toBeFalsy();
            let obtained = node.toggleExpanded();
            expect(obtained).toBeTruthy();
            expect(node.isExpanded()).toBeTruthy();
            node.expand();
            expect(node.isExpanded()).toBeTruthy();
            obtained = node.toggleExpanded();
            expect(obtained).toBeFalsy();
            expect(node.isExpanded()).toBeFalsy();
        });
    });
});
