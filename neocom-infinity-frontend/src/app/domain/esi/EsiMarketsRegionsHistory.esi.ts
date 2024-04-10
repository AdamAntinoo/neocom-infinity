// - DOMAIN
import { EsiMarketsRegionsHistoryRecord } from "./EsiMarketsRegionsHistoryRecord.esi"
import { EsiNode } from "./EsiNode.esi"

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
