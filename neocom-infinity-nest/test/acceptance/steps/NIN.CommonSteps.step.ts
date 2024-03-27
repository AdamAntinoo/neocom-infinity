import { Before, setWorldConstructor } from "@cucumber/cucumber";
import { NIN01World } from "../worlds/NIN01World";

setWorldConstructor(NIN01World);

Before(async function (scenario) {
    await this.init(scenario);
})
