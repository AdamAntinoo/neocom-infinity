import { loadFeature, defineFeature } from 'jest-cucumber';
import { AssetEsi } from './asset-esi';
// import { PasswordValidator } from 'src/password-validator';

const feature = loadFeature('./test/acceptance/features/NIN02.DeltaCalculator.feature');

defineFeature(feature, (test) => {
  let passwordValidator = new AssetEsi();
  let accessGranted = false;

  beforeEach(() => {
    passwordValidator = new AssetEsi();
  });

  test('[NIN02.01] Describe the output when the delta calculator receives a type 01 configuration.', ({ given, when, then, and }) => {
    	given(/^a base asset list of type (\d+)$/, (arg0) => {

    	});

    	given(/^a new asset list of type (\d+)$/, (arg0) => {

    	});

    	when('both list are entered to the Delta Calculator', () => {

    	});

    	then(/^the output asset list has (\d+) assets$/, (arg0) => {

    	});

    	and(/^the asset (\d+) quantity is (\d+)$/, (arg0, arg1) => {

    	});
    });

});
