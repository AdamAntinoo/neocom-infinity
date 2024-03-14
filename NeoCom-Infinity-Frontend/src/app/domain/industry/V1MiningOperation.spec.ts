// - TESTING
import { TestBed } from '@angular/core/testing';
// - PROVIDERS
import { SupportIsolationService } from '@app/testing/SupportIsolation.service';
// - DOMAIN
import { V1MiningOperation } from './V1.MiningOperation.domain';

describe('DOMAIN V1MiningOperation [Module: DOMAIN]', () => {
    let isolation: SupportIsolationService;
    let fitting4Test = {
        "jsonClass": "Fitting",
        "fittingId": 60320161,
        "name": "VM Clearer A1",
        "description": "",
        "hullGroup": "frigate",
        "shipHullInfo": {
            "jsonClass": "NeoItem",
            "typeId": 32880,
            "name": "Venture",
            "groupId": 25,
            "groupName": "Frigate",
            "categoryId": 6,
            "categoryName": "Ship",
            "tech": "Tech I",
            "volume": 29500.0,
            "price": 193941.12,
            "isBlueprint": false,
            "urlforItem": "https://image.eveonline.com/Type/32880_64.png"
        },
        "items": [{
            "jsonClass": "FittingItem",
            "typeId": 2048,
            "name": "Damage Control II",
            "location": "LoSlot0"
        },
        {
            "jsonClass": "FittingItem",
            "typeId": 5245,
            "name": "Particle Bore Compact Mining Laser",
            "location": "HiSlot0"
        },
        {
            "jsonClass": "FittingItem",
            "typeId": 5245,
            "name": "Particle Bore Compact Mining Laser",
            "location": "HiSlot1"
        },
        {
            "jsonClass": "FittingItem",
            "typeId": 8517,
            "name": "Medium F-S9 Regolith Compact Shield Extender",
            "location": "MedSlot1"
        },
        {
            "jsonClass": "FittingItem",
            "typeId": 8517,
            "name": "Medium F-S9 Regolith Compact Shield Extender",
            "location": "MedSlot2"
        },
        {
            "jsonClass": "FittingItem",
            "typeId": 10872,
            "name": "Medium C5-L Compact Shield Booster",
            "location": "MedSlot0"
        },
        {
            "jsonClass": "FittingItem",
            "typeId": 31358,
            "name": "Small Ancillary Current Router I",
            "location": "RigSlot0"
        }
        ]
    };
    let fitting4Failure = {
        "jsonClass": "Fitting",
        "fittingId": 60320161,
        "name": "VM Clearer A1",
        "description": "",
        "hullGroup": "frigate"
    };

    beforeEach(() => {
        isolation = TestBed.inject(SupportIsolationService);
    });
    // - C O N S T R U C T I O N   P H A S E
    describe('Constructor contract phase', () => {
        it('when empty hould be created', () => {
            expect(new V1MiningOperation()).toBeDefined
            expect(new V1MiningOperation().identify()).toBe('MiningOperation');
            expect(new V1MiningOperation().getJsonClass()).toBe('MiningOperation');
            expect(new V1MiningOperation().getIdentifier()).toBeDefined
        });
        it('when only set a property it matches', () => {
            const operation: V1MiningOperation = new V1MiningOperation({ quantity: 325 })
            expect(operation).toBeDefined
            expect(operation.getQuantity).toBe(325)

        });
        it('when constructed from a ESIMiningOperation', () => {
            const operation: V1MiningOperation = new V1MiningOperation({ date: '01/01/1990', quantity: 325, })
            expect(operation).toBeDefined
            expect(operation.getQuantity).toBe(325)
        });
    });
    // - C O D E   C O V E R A G E   P H A S E
//     describe('Code Coverage Phase [getters]', () => {
//         it('getFittingId.success: check the fitting identifier', () => {
//             const expected = 60320161;
//             const fitting: Fitting = new Fitting(fitting4Test);
//             const obtained = fitting.getFittingId();
//             expect(obtained).toBe(expected);
//         });
//         it('getName.success: check the fitting identifier', () => {
//             const expected = "VM Clearer A1";
//             const fitting: Fitting = new Fitting(fitting4Test);
//             const obtained = fitting.getName();
//             expect(obtained).toBe(expected);
//         });
//         it('getShipTypeId.success: check the ship type identifier', () => {
//             const expected = 32880;
//             const fitting: Fitting = new Fitting(fitting4Test);
//             const obtained = fitting.getShipTypeId();
//             expect(obtained).toBe(expected);
//         });
//         it('getShipTypeId.failure: check the ship type identifier', () => {
//             const fitting: Fitting = new Fitting(fitting4Failure);
//             const obtained = fitting.getShipTypeId();
//             expect(obtained).toBe(-1);
//         });
//         it('getShipClassName.success: check the ship class name', () => {
//             const expected = "Venture";
//             const fitting: Fitting = new Fitting(fitting4Test);
//             const obtained = fitting.getShipClassName();
//             expect(obtained).toBe(expected);
//         });
//         it('getShipClassName.failure: check the ship class name', () => {
//             const fitting: Fitting = new Fitting(fitting4Failure);
//             const obtained = fitting.getShipClassName();
//             expect(obtained).toBe("-");
//         });
//         it('getHullCategory.success: check the hull category name', () => {
//             const expected = "frigate";
//             const fitting: Fitting = new Fitting(fitting4Test);
//             const obtained = fitting.getHullCategory();
//             expect(obtained).toBe(expected);
//         });
//         it('getShipGroup.success: check the ship class name', () => {
//             const expected = "Frigate";
//             const fitting: Fitting = new Fitting(fitting4Test);
//             const obtained = fitting.getShipGroup();
//             expect(obtained).toBe(expected);
//         });
//         it('getShipGroup.failure: check the ship class name', () => {
//             const fitting: Fitting = new Fitting(fitting4Failure);
//             const obtained = fitting.getShipGroup();
//             expect(obtained).toBe("-SHIP-");
//         });
//         it('getShipGroupId.success: check the ship class identifier', () => {
//             const expected = 25;
//             const fitting: Fitting = new Fitting(fitting4Test);
//             const obtained = fitting.getShipGroupId();
//             expect(obtained).toBe(expected);
//         });
//         it('getShipGroupId.failure: check the ship class identifier', () => {
//             const fitting: Fitting = new Fitting(fitting4Failure);
//             const obtained = fitting.getShipGroupId();
//             expect(obtained).toBe(0);
//         });
//         it('getUrl4Item.success: check the ship class identifier', () => {
//             const expected = "https://image.eveonline.com/Type/32880_64.png";
//             const fitting: Fitting = new Fitting(fitting4Test);
//             const obtained = fitting.getUrl4Item();
//             expect(obtained).toBe(expected);
//         });
//         it('getUrl4Item.failure: check the ship class identifier', () => {
//             const fitting: Fitting = new Fitting(fitting4Failure);
//             const obtained = fitting.getUrl4Item();
//             expect(obtained).toBe('/assets/res-ui/drawable/defaulticonplaceholder.png');
//         });
//     });
});
