import { UUID, randomUUID } from "crypto"
import { V1HALLink } from "./V1.HALLink"

export class V2MiningOperation {
    public jsonClass: string = 'MiningOperation'
    public id: UUID = randomUUID()
    public date: string
    public quantity: number = 0
    public solarSystem: V1HALLink
    public typeId: V1HALLink
}
