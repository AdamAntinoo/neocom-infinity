// - TESTING
import { TestBed } from '@angular/core/testing';
// - PROVIDERS
import { SupportIsolationService } from '@app/testing/SupportIsolation.service';
// - DOMAIN
import { FittingItem } from './FittingItem.domain';

fdescribe('CLASS FittingItem [Module: DOMAIN]', () => {
    let isolation: SupportIsolationService;

    beforeEach(() => {
        isolation = TestBed.get(SupportIsolationService);
    });
    // - C O N S T R U C T I O N   P H A S E
    fdescribe('Construction Phase', () => {
        it('Should be created', () => {
            console.log('><[domain/Fitting]> should be created');
            expect(new FittingItem()).toBeTruthy();
        });
    });
    // - C O D E   C O V E R A G E   P H A S E
    xdescribe('Code Coverage Phase [getters]', () => {
        it('getName.success: check the name field', () => {
            const expectedName = isolation.generateRandomString(32);
            const expected = new Object({ name: expectedName });
            // const corporation = new Corporation({ corporationPublicData: expected })
            // const obtained = corporation.getName();
            // expect(obtained).toBe(expectedName);
        });
    });
});
