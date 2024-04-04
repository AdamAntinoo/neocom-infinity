// - TESTING
import { TestBed } from '@angular/core/testing';
// - PROVIDERS
import { SupportIsolationService } from '@app/testing/SupportIsolation.service';
// - DOMAIN
import { FittingItem } from './FittingItem.domain';

describe('CLASS FittingItem [Module: DOMAIN]', () => {
    let isolation: SupportIsolationService;
    let fittingItem4Test = {
        "jsonClass": "FittingItem",
        "typeId": 2048,
        "name": "Damage Control II",
        "location": "LoSlot0"
    };
    beforeEach(() => {
        isolation = TestBed.get(SupportIsolationService);
    });
    // - C O N S T R U C T I O N   P H A S E
    describe('Construction Phase', () => {
        it('Should be created', () => {
            console.log('><[domain/Fitting]> should be created');
            expect(new FittingItem()).toBeTruthy();
        });
        it('Check the class of the created instance', () => {
            console.log('><[domain/Fitting]> should be created');
            expect(new FittingItem().getJsonClass()).toBe('FittingItem');
        });
        it('Should validate the construction transformation', () => {
            console.log('><[domain/Fitting]> Should validate the construction transformation');
            expect(new FittingItem(fittingItem4Test)).toBeTruthy();
        });
    });
});
