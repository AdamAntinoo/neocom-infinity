import { MANDATORY_FIELD_MISSING, NeoComError } from "neocom-domain";
import { V2UniverseType } from "./V2.UniverseType.domain";
import { NeoCom } from "@domain/NeoCom.domain";

export class V1Stack extends NeoCom {
    public quantity: number
    public type: V2UniverseType

    public decode(): void { }

    public static Builder = class Builder {
        public stack: V1Stack

        constructor(fields: Object = {}) {
            this.stack = new V1Stack(fields)
        }
        public withQuantity(quantity: number): Builder {
            if (undefined == quantity) throw new NeoComError.Builder(MANDATORY_FIELD_MISSING).build()
            this.stack.quantity = quantity
            return this
        }
        public withType(type: V2UniverseType): Builder {
            if (undefined == type) throw new NeoComError.Builder(MANDATORY_FIELD_MISSING).build()
            this.stack.type = type
            return this
        }
        public build(): V1Stack {
            if (undefined == this.stack.quantity) throw new NeoComError.Builder(MANDATORY_FIELD_MISSING).build()
            if (undefined == this.stack.type) throw new NeoComError.Builder(MANDATORY_FIELD_MISSING).build()
            return this.stack
        }
    }
}
