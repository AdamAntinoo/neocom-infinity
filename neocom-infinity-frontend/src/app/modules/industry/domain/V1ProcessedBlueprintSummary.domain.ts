// - DOMAIN
import { Station } from "@domain/location/Station.domain"
import { NeoCom } from "@domain/NeoCom.domain"

export class V1ProcessedBlueprintSummary extends NeoCom {
    public uid: string
    public blueprintTypeId: number
    public blueprintTypeName: string
    public blueprintTypeIconURL: string
    public outputTypeId: number
    public outputTypeName: string
    public outputTypeIconURL: string
    public outputPrice: number
    public tradeStation: Station
    public manufactureMaterialCost: number
    public costIndex: number

    public decode(): void {
        this.jsonClass = 'ProcessedBlueprintSummary'
        if (this.tradeStation) this.tradeStation = new Station(this.tradeStation)
    }
    // - G E T T E R S
    public getUniqueId(): string {
        return this.uid
    }
    public getBlueprintName(): string {
        return this.blueprintTypeName
    }
    public getOutputName(): string {
        return this.outputTypeName
    }
}
