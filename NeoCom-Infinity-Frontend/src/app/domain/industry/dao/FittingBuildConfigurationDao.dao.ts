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
    private fittingBuildId: string
    private fittingInfo: FittingInfoDao
    private contents: FittingBuildContentDao[]

    constructor(values: Object = {}) {
        Object.assign(this, values)
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
    // public isHalCompliant(): boolean {
    //     return true
    // }
    // public injectResolver(resolver: HALResolver): void {
    //     for (const content of this.contents) {
    //         content.fittingItem.setResolver(resolver)
    //     }
    // }

    // - G E T T E R S   &   S E T T E R S
    public getFittingId(): string {
        return this.fittingBuildId
    }
    public getFittingInfo(): FittingInfoDao {
        return this.fittingInfo
    }
    public getContents(): FittingBuildContentDao[] {
        return this.contents
    }
}
