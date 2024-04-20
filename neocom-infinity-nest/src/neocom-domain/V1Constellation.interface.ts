import { V1Region } from "./V1Region.interface"

export interface V1Constellation extends V1Region {
    constellationId?: number
    constellationName?: string
}
