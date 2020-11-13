// - CORE
import { Observable } from 'rxjs';
// - DOMAIN
import { HALLink } from '@domain/hal/HALLink.hal';
import { HALNode } from '@domain/hal/HALNode.hal';
import { EveItemDao } from '@domain/core/dao/EveItemDao.dao';
import { NeoCom } from '@domain/NeoCom.domain';

export class FittingItemHAL extends HALNode {
    public typeId: number
    public name: string
    public location: string
    public item: HALLink<EveItemDao>

    constructor(values: Object = {}) {
        super()
        Object.assign(this, values)
        this.transform()
    }
    private transform(): void {
        if (null != this.item) this.item = new HALLink<EveItemDao>(this.item)
    }
    // public resolve(): Observable<FittingItemHAL> {
    //     this.item.resolve()
    //         .subscribe(itemData => {
    //             this.item.isDownloaded = true
    //         })
    // }
    public async getItem(): Promise<EveItemDao> {
        console.log('Resolver: ' + this.getResolver())
        return await this.item.access(this.getResolver())
    }
    public async getName(): Promise<string> {
        const halItem: EveItemDao = new EveItemDao(await this.getItem())
        if (null != halItem) return halItem.getName()
    }
    // public async getSubName(): string {
    //     const item = await this.getItem()
    //     return item.getName()
    // }
    public async getURLIcon(): Promise<string> {
        return await (await this.getItem()).getURLIcon()
    }
    //     public async accessItem () : Promise<EveItemDao>{
    // if ( this.item.isDownloaded)
    //         // if (undefined == this.corporation.href)
    //         return await new Observable(subscriber => {
    //             subscriber.next(this.item.ge)
    //             subscriber.complete()
    //         }).toPromise()
    //     // else {
    //     //     return await new Observable(subscriber => {
    //     //         this.resolve(this.corporation.href)
    //     //             .pipe(map((data: any): Corporation => {
    //     //                 // console.log(">[HALResolver.resolve]> Transformation: " + transformer.description)
    //     //                 const response = new Corporation(data)
    //     //                 return response
    //     //             }))
    //     //             .subscribe((entrydata: Corporation): void => {
    //     //                 this.corporation = entrydata
    //     //                 subscriber.next(this.corporation)
    //     //                 subscriber.complete();
    //     //                 // this.corpObservable.
    //     //                 // return this.corporation
    //     //             })
    //     //     }).toPromise()


    //     }
}
