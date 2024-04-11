import { MANDATORY_FIELD_MISSING } from "../../application/domain/NeoComErrorCatalog"
import { NeoComError } from "../../application/domain/NeocomError"
import { V1MiningResourceDto } from "./V1MiningResourceDto.dto"

export class V1MiningOperationDto {
    public jsonClass: string = 'MiningOperationDto'
    public id: string
    public date: string
    public solarSystemId: number
    private resources: V1MiningResourceDto[] = []

    constructor(fields: object = {}) {
        Object.assign(this, fields)
    }

    public transform(): V1MiningOperationDto {
        console.log('transform->no need transformation')
        return this
    }
    public getResources ():V1MiningResourceDto[]{
        return this.resources
    }
    public addMiningResource(miningResource: V1MiningResourceDto): V1MiningOperationDto {
        this.resources.push(miningResource)
        return this
    }

    public static Builder = class Builder {
        public operation: V1MiningOperationDto

        constructor() {
            this.operation = new V1MiningOperationDto()
            this.operation.resources = []
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
        public build(): V1MiningOperationDto {
            if (undefined == this.operation.id) throw new NeoComError.Builder(MANDATORY_FIELD_MISSING).build()
            if (undefined == this.operation.date) throw new NeoComError.Builder(MANDATORY_FIELD_MISSING).build()
            if (undefined == this.operation.solarSystemId) throw new NeoComError.Builder(MANDATORY_FIELD_MISSING).build()
            return this.operation
        }
    }
}
