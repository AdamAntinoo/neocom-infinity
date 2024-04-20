import { V1Constellation } from "./V1Constellation.interface"

export interface V1SolarSystem extends V1Constellation{
     solarSystemId?: number
     solarSystemName?: string
}
