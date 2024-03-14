import { v4 as uuid4 } from 'uuid'
import { NeoCom } from "@domain/NeoCom.domain";
import { LookupSolarSystem } from '@app/modules/planetary/domain/LookupSolarSystem.domain';
import { EveItemDto } from '@domain/core/dto/EveItemDto.dto';
import { HALLink } from '@domain/hal/HALLink.hal';

export class V1MiningOperation extends NeoCom {
    private id: string = uuid4()
    private date: string
    private quantity:number=0
    public solarSystem: HALLink<LookupSolarSystem>
    public type: HALLink<EveItemDto>

    public identify(): string {
        return 'MiningOperation'
    }
    public getIdentifier(): string {
        return this.id
    }
    public getQuantity():number{
        return this.quantity
    }
}
