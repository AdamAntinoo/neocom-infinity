import { loadFeature, defineFeature } from 'jest-cucumber';
import { AssetEsi } from '../asset-esi';
import { DeltaCalculator } from '../../../src/application/dmos/delta-calculator';

const feature = loadFeature('./test/acceptance/features/NIN02.DeltaCalculator.feature');

defineFeature(feature, (test) => {
  let deltaCalculator = new DeltaCalculator();
  let initialList: AssetEsi[];
  let secondList: AssetEsi[];
  let output: AssetEsi[];

  beforeEach(() => {
    deltaCalculator = new DeltaCalculator();
  });

  test('[NIN02.01] Describe the output when the delta calculator receives a type 01 configuration.', ({ given, when, then, and }) => {
    given(/^a base asset list of type (\d+)$/, (type: string) => {
      console.info('type:' + type);
      switch (type) {
        case "01": {
          console.info('>>> Type 01');
          initialList = [
            {
              "is_singleton": false,
              "item_id": 100001,
              "location_flag": "AutoFit",
              "location_id": 1035124094434,
              "location_type": "item",
              "quantity": 1000,
              "type_id": 101
            },
            {
              "is_singleton": false,
              "item_id": 100002,
              "location_flag": "AutoFit",
              "location_id": 1035124094434,
              "location_type": "item",
              "quantity": 1000,
              "type_id": 102
            },
            {
              "is_singleton": false,
              "item_id": 100003,
              "location_flag": "AutoFit",
              "location_id": 1035124094434,
              "location_type": "item",
              "quantity": 1000,
              "type_id": 103
            }
          ];
        }
      }
      console.info("initialList:" + initialList);
      console.info("secondList:" + secondList);
    });

    given(/^a new asset list of type (\d+)$/, (type: string) => {
      console.info('type:' + type);
      switch (type) {
        case "01": {
          console.info('>>> Type 01');
          secondList = [
            {
              "is_singleton": false,
              "item_id": 100001,
              "location_flag": "AutoFit",
              "location_id": 1035124094434,
              "location_type": "item",
              "quantity": 1000,
              "type_id": 101
            },
            {
              "is_singleton": false,
              "item_id": 100002,
              "location_flag": "AutoFit",
              "location_id": 1035124094434,
              "location_type": "item",
              "quantity": 1000,
              "type_id": 102
            },
            {
              "is_singleton": false,
              "item_id": 100002,
              "location_flag": "AutoFit",
              "location_id": 1035124094434,
              "location_type": "item",
              "quantity": 1000,
              "type_id": 103
            }
          ];
        }
      }
      console.info("initialList:" + initialList);
      console.info("secondList:" + secondList);
    });

    when('both list are entered to the Delta Calculator', () => {
      output = deltaCalculator.apply(initialList, secondList);
      console.info("output:" + JSON.stringify(output));
    });

    then(/^the output asset list has (\d+) assets$/, (arg0) => {

    });

    and(/^the asset (\d+) quantity is (\d+)$/, (arg0, arg1) => {

    });
  });

});
