// - CORE
import { Component } from '@angular/core';
import { ViewChild } from '@angular/core';
import { EsiMarketData } from '@domain/esi/EsiMarketData.esi';
import { EsiType } from '@domain/esi/EsiType.esi';
import { HALLink } from '@domain/hal/HALLink.hal';
import { MarketOrderDto } from '@domain/industry/dto/MarketOrderDto.dto';
import { NeoCom } from '@domain/NeoCom.domain';

export class LoyaltyOfferV1 extends NeoCom {
    public offerId: number
    public typeId: number
    public type: EsiType
    public typeName: string
    public corporationId: number
    public corporationName: string
    public lpValue: number
    public iskCost: number
    public lpCost: number
    public quantity: number
    public marketRegionId: number
    public price: number
    public marketData: EsiMarketData

    constructor(values: Object = {}) {
        super()
        Object.assign(this, values)
        this.jsonClass = 'LoyaltyOfferV1'
        // Warning. References objects can come as links so should be cleared on construction
        this.type=undefined
        this.marketData=undefined
    }

    public getType(): EsiType {
        return this.type
    }
}
