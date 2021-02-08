// - DOMAIN
import { MarketOrderDto } from "@domain/industry/dto/MarketOrderDto.dto"
import { forEach } from "cypress/types/lodash"
import { EsiMarketsRegionsHistoryRecord } from "./EsiMarketsRegionsHistoryRecord.esi"
import { EsiNode } from "./EsiNode.esi"
import { UniverseMarketOrder } from "./UniverseMarketOrder.esi"

export class EsiMarketsRegionsHistory   extends EsiNode  {
    public records: EsiMarketsRegionsHistoryRecord[]

    public decode() {
        if (this.records) {
            const decodedRecords: EsiMarketsRegionsHistoryRecord[] = []
            for (let record of this.records)
            decodedRecords.push(new EsiMarketsRegionsHistoryRecord(record))
            this.records = decodedRecords
        }
  }
}
