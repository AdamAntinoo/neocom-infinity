// - TESTING
import { TestBed } from '@angular/core/testing';
// - PROVIDERS
import { SupportIsolationService } from '@app/testing/SupportIsolation.service';
// - DOMAIN
import { NeoComExpandable } from './NeoComExpandable.domain';
import { Separator } from './Separator.model';
import { ESeparator } from './interfaces/EPack.enumerated';

describe('CLASS Separator [Module: DOMAIN]', () => {
    // - C O N S T R U C T I O N   P H A S E
    describe('Construction Phase', () => {
        it('Should be created', () => {
            console.log('><[domain/Separator]> should be created');
            expect(new Separator()).toBeTruthy();
        });
        it('Check the class of the created instance', () => {
            console.log('><[domain/Separator]> should be created');
            expect(new Separator().getJsonClass()).toBe('Separator');
        });
        it('Should validate the construction transformation', () => {
            console.log('><[domain/Separator]> Should validate the construction transformation');
            expect(new Separator({ variation: ESeparator.BLACK })).toBeTruthy();
        });
    });
    // - C O D E   C O V E R A G E   P H A S E
    describe('Code Coverage Phase [setters]', () => {
        it('setVariation.success: check the setting for the separator variation', () => {
            let expected: ESeparator = ESeparator.BLACK;
            const separator: Separator = new Separator();
            separator.setVariation(expected);
            expect(separator.getVariation()).toBe(expected);
        });
    });
    describe('Code Coverage Phase [getters]', () => {
        it('getVariation.success: check the separator variation', () => {
            let expected: ESeparator = ESeparator.WHITE;
            const separator: Separator = new Separator();
            const obtained = separator.getVariation();
            expect(obtained).toBe(expected);
        });
    });
});
