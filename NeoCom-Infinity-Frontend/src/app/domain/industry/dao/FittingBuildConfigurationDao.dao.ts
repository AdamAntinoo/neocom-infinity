// - DOMAIN
import { BaseNode } from '@domain/core/BaseNode.domain'
import { ICollaboration } from '@domain/interfaces/ICollaboration.interface'
import { NeoCom } from '@domain/NeoCom.domain'
import { BuildActionDao } from './BuildActionDao.dao'
import { FittingBuildContentDao } from './FittingBuildContentDao.dao'
import { HullDao } from './HullDao.dao'
import { FittingInfoDao } from './FittingInfoDao.dao'
import { HALResolver } from '@app/services/HALResolver.service'
import { FittingItem } from '@domain/FittingItem.domain'
/**
 * This class contains all the Fitting data hierarchy as processed from the backend.
 * It uses the Node factory to collaborate contents to the automatic node render factory so content management is simple to render and control.
 */
export class FittingBuildConfigurationDao {
    // public name: string
    // public description: string
    // public hullGroup: string
    // public hops: number
    // private hull: HullDao
    // private actions: BuildActionDao[]

    private fittingBuildId: string
    private fittingInfo: FittingInfoDao
    private contents: FittingBuildContentDao[]

    constructor(values: Object = {}) {
        // super()
        Object.assign(this, values)
        // this.jsonClass = 'FittingBuildConfiguration'
        this.transform()
    }
    /**
     * Transform json serialized data into DAO Typescript instances.
     */
    protected transform(): void {
        if (null != this.fittingInfo) this.fittingInfo = new FittingInfoDao(this.fittingInfo)
        if (null != this.contents) {
            const transformedContents: FittingBuildContentDao[] = []
            this.contents.forEach(content => {
                const buildContent = new FittingBuildContentDao(content)
                transformedContents.push(buildContent)
            })
            this.contents = transformedContents
        }
    }

    // - H A L C O M P L I A N T
    public isHalCompliant(): boolean {
        return true
    }
    public injectResolver(resolver: HALResolver): void {
        for (const content of this.contents) {
            content.fittingItem.setResolver(resolver)
            new FittingItem().fromHal(content.fittingItem)
                .then(item => {
                    content.realFittingItem = item
                })
        }
    }
    // - I C O L L A B O R A T I O N
    // public collaborate2View(): ICollaboration[] {
    //     return [this];
    // }
    // - G E T T E R S   &   S E T T E R S
    public getFittingId(): string {
        return this.fittingBuildId
    }
    public getFittingInfo(): FittingInfoDao {
        return this.fittingInfo
    }
    // public getHullURLIcon(): string {
    //     return this.hull.getHullURLIcon()
    // }
    // public getHullClass(): string {
    //     return this.hull.getHullClass()
    // }
    // public getFittingName(): string {
    //     return this.name
    // }
    // public getHullGroup(): string {
    //     return this.hullGroup
    // }
    public getContents(): FittingBuildContentDao[] {
        return this.contents
    }
    // - D E L E G A T E D
    // public getHullTypeId(): number {
    //     return this.hull.getHullTypeId()
    // }
    // public getHullTech(): string {
    //     return this.hull.getTech()
    // }
    // public getHullPrice(): number {
    //     return this.hull.getPrice()
    // }
    // public getMarketStation(): string {
    //     return this.hull.getMarketStation()
    // }
    // public getHullHops(): number {
    //     return this.hull.getHullHops()
    // }
    // public getHullHopTime(): number {
    //     return this.hull.getHullHopTime()
    // }
    // public getFittingItem(group: string, index: number): BuildActionDao {
    //     return new BuildActionDao(this.actions[index])
    // }
}
