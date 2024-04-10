// - DOMAIN
import { FittingBuildContentDto } from './FittingBuildContentDto.dto'
import { FittingInfoDto } from './FittingInfoDto.dto'
/**
 * This class contains all the Fitting data hierarchy as processed from the backend.
 * It uses the Node factory to collaborate contents to the automatic node render factory so content management is simple to render and control.
 */
export class FittingBuildConfigurationDto {
    private fittingBuildId: string
    private fittingInfo: FittingInfoDto
    private contents: FittingBuildContentDto[]

    constructor(values: Object = {}) {
        Object.assign(this, values)
        this.transform()
    }
    /**
     * Transform json serialized data into DAO Typescript instances.
     */
    protected transform(): void {
        if (null != this.fittingInfo) this.fittingInfo = new FittingInfoDto(this.fittingInfo)
        if (null != this.contents) {
            const transformedContents: FittingBuildContentDto[] = []
            this.contents.forEach(content => {
                const buildContent = new FittingBuildContentDto(content)
                transformedContents.push(buildContent)
            })
            this.contents = transformedContents
        }
    }

    // - G E T T E R S   &   S E T T E R S
    public getFittingId(): string {
        return this.fittingBuildId
    }
    public getFittingInfo(): FittingInfoDto {
        return this.fittingInfo
    }
    public getContents(): FittingBuildContentDto[] {
        return this.contents
    }
}
