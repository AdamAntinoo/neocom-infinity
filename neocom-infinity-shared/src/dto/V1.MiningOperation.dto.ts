import { MANDATORY_FIELD_MISSING } from "../exceptions/NeoComSharedErrorCatalog"
import { NeoComError } from "../exceptions/NeoComError"
import { V1MiningResourceDto } from "./V1.MiningResource.dto"
import { Record } from "../public-api"

export class V1MiningOperationDto extends Record {
    public override jsonClass: string = 'MiningOperationDto'
    public id?: string
    public date?: string
    public solarSystemId?: number
    private resources: V1MiningResourceDto[] = []

    public getResources(): V1MiningResourceDto[] {
        return this.resources
    }

    public static Builder = class Builder {
        public operation: V1MiningOperationDto

        constructor(fields: object = {}) {
            this.operation = new V1MiningOperationDto(fields)
            if (undefined != this.operation.resources) this.operation.resources = []
        }
        public withId(id: string): Builder {
            if (undefined == id) throw new NeoComError.Builder(MANDATORY_FIELD_MISSING).build()
            this.operation.id = id
            return this
        }
        public withDate(date: string): Builder {
            if (undefined == date) throw new NeoComError.Builder(MANDATORY_FIELD_MISSING).build()
            this.operation.date = date
            return this
        }
        public withSolarSystem(solarSytem: number): Builder {
            if (undefined == solarSytem) throw new NeoComError.Builder(MANDATORY_FIELD_MISSING).build()
            this.operation.solarSystemId = solarSytem
            return this
        }
        public addMiningResource(miningResource: V1MiningResourceDto): Builder {
            if (undefined != miningResource)
                this.operation.resources.push(miningResource)
            return this
        }
        public build(): V1MiningOperationDto {
            if (undefined == this.operation.id) throw new NeoComError.Builder(MANDATORY_FIELD_MISSING).build()
            if (undefined == this.operation.date) throw new NeoComError.Builder(MANDATORY_FIELD_MISSING).build()
            if (undefined == this.operation.solarSystemId) throw new NeoComError.Builder(MANDATORY_FIELD_MISSING).build()
            return this.operation
        }
    }
}
