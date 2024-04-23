import { IConstructor, V1EsiTypeDto, V1StackDto } from "neocom-domain";
import { V1Stack } from "@domain/esi/V1.Stack.domain";
import { UnsecuredProxy } from "@adapter/outbound/UnsecuredProxy/V1.UnsecuredProxy.adapter";

export class StackConstructor implements IConstructor<V1StackDto, V1Stack>{
    constructor(private readonly resolver: UnsecuredProxy) { }

    public async construct(stackDto: V1StackDto): Promise<V1Stack> {
        const type: V1EsiTypeDto = await this.resolver.apiv3_GetUnsecuredLink<V1EsiTypeDto>(stackDto.typeLink)
        const stack: V1Stack = new V1Stack({
            quantity: stackDto.quantity,
            type: type
        })
        return stack
    }
}
