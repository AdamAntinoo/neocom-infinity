// - DOMAIN
import { EsiCategory } from "@domain/esi/EsiCategory.esi";
import { EsiGroup } from "@domain/esi/EsiGroup.esi";
import { EsiMarketData } from "@domain/esi/EsiMarketData.esi";
import { EsiType } from "@domain/esi/EsiType.esi";
import { HALLink } from "@domain/hal/HALLink.hal";
import { NeoCom } from "@domain/NeoCom.domain";
import { Resource } from "../domain/Resource.domain";
import { BOMResource } from "../domain/V1BOMResource.domain";
import { IndustryResource } from "../domain/V1IndustryResource.domain";

export class Resource2BOMResourceConverter{
    public convert(input : Resource): IndustryResource{
        return new IndustryResource({
             typeId: input.getTypeId(),
             name: input.getName(),
             quantity: input.getQuantity(),
             price: 2341.0           
        })
    }
}
