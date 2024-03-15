import { setWorldConstructor } from "@cucumber/cucumber"

export class NIN01World {
   public characterId: number
}
setWorldConstructor(NIN01World)
