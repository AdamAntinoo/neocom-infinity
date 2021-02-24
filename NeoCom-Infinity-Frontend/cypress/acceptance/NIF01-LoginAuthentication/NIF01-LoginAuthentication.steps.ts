// - CUCUMBER-PREPROCESSOR
import { Given } from "cypress-cucumber-preprocessor/steps";
import { When } from "cypress-cucumber-preprocessor/steps";
import { Then } from "cypress-cucumber-preprocessor/steps";
// - SERVICES
import { SupportService } from '../../support/SupportService.support';
import { NeoComCredential } from "../../../src/app/domain/NeoComCredential.domain";
import { PlatformConstants } from "../../../src/environments/PlatformConstants";

const supportService = new SupportService();

// - N E W   I M P L E M E N T A T I O N
