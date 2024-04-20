import { V1SolarSystem } from "./V1SolarSystem.interface"

export interface V1Station extends V1SolarSystem {
    stationId?: number
    stationName?: string
    securityClass?: string
    securityStatus?: number
}
