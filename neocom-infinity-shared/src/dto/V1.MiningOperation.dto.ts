import { MANDATORY_FIELD_MISSING } from "../exceptions/NeoComSharedErrorCatalog"
import { NeoComError } from "../exceptions/NeoComError"
import { Record } from "../interfaces/Record.interface"
import { V1StackDto } from "./V1.Stack.dto"

export class V1MiningOperationDto extends Record {
    public override jsonClass: string = 'MiningOperationDto'
    public id?: string
    public date?: string
    public solarSystemLink?: string
    private resources: V1StackDto[] = []

    public getResources(): V1StackDto[] {
        return this.resources
    }
    public addMiningResource(miningResource: V1StackDto): V1StackDto {
        this.resources.push(miningResource)
        return this
    }
    public static Builder = class Builder {
        public operation: V1MiningOperationDto

        constructor(fields: object = {}) {
            this.operation = new V1MiningOperationDto(fields)
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
        public withSolarSystemLink(link: string): Builder {
            if (undefined == link) throw new NeoComError.Builder(MANDATORY_FIELD_MISSING).build()
            this.operation.solarSystemLink = link
            return this
        }
        public addMiningResource(miningResource: V1StackDto): Builder {
            if (undefined != miningResource)
                this.operation.resources.push(miningResource)
            return this
        }
        public build(): V1MiningOperationDto {
            if (undefined == this.operation.id) throw new NeoComError.Builder(MANDATORY_FIELD_MISSING).build()
            if (undefined == this.operation.date) throw new NeoComError.Builder(MANDATORY_FIELD_MISSING).build()
            if (undefined == this.operation.solarSystemLink) throw new NeoComError.Builder(MANDATORY_FIELD_MISSING).build()
            return this.operation
        }
    }
}
