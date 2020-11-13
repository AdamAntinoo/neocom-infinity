// - CORE
import { Observable } from 'rxjs';
// - DOMAIN
import { HALLink } from '@domain/hal/HALLink.hal';
import { HALNode } from '@domain/hal/HALNode.hal';
import { EveItemDao } from '@domain/core/dao/EveItemDao.dao';

export class FittingItemHAL extends HALNode {
    public typeId: number
    public name: string
    public location: string
    public item: HALLink<EveItemDao>

    constructor(values: Object = {}) {
        super()
        Object.assign(this, values)
    }
    // public resolve(): Observable<FittingItemHAL> {
    //     this.item.resolve()
    //         .subscribe(itemData => {
    //             this.item.isDownloaded = true
    //         })
    // }
    public getItem(): Observable<EveItemDao> {
        return this.item.access(this.getResolver())
    }
}
