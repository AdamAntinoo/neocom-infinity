// - DOMAIN
import { HALResolver } from '@app/services/HALResolver.service';
import { Observable } from 'rxjs';
import { EveItemDao } from './core/dao/EveItemDao.dao';
import { FittingItemHAL } from './industry/hal/FittingItemHAL.hal';
import { NeoCom } from './NeoCom.domain';

export class FittingItem extends NeoCom {
    private typeId: number;
    private name: string;
    private location: string;
    private item: EveItemDao

    constructor(values: Object = {}) {
        super();
        Object.assign(this, values);
        this.jsonClass = 'FittingItem';
    }
    // public fromHal(fittingItemHal: FittingItemHAL, resolver: HALResolver): Observable<FittingItem> {
    //     this.typeId = fittingItemHal.typeId
    //     this.name = fittingItemHal.name
    //     this.location = fittingItemHal.location
    //     fittingItemHal.setResolver(resolver)
    //     return fittingItemHal.resolve(fittingItemHal.item)
    // }

    // - G E T T E R S
    public getName(): string {
        if (null != this.item)
            return this.item.getName()
    }
    public getItem(): EveItemDao {
        if (null != this.item)
            return this.item
    }
}
