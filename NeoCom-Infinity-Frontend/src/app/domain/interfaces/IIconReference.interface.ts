export interface IIconReference {
    getReference(): string;
}
export interface IGroupIconReference {
    getReference(): string;
}
export class URLGroupIconReference implements IIconReference {
    private static FITTING_SHIP_URL_BASE = "http://image.eveonline.com/Type/";

    constructor(private iconType: number) { }
    public getReference(): string {
        return URLGroupIconReference.FITTING_SHIP_URL_BASE + this.iconType + "_64.png";
    }
}
export class AssetGroupIconReference implements IIconReference {
    public static FITTING_SHIP_ASSET_LOCATION: string = "/assets/res-fitting/drawable/";

    constructor(private iconName: string) { }
    public getReference(): string {
        return AssetGroupIconReference.FITTING_SHIP_ASSET_LOCATION + this.iconName.toLowerCase() + ".png";
    }
}
export class IndustryIconReference implements IIconReference {
    private static INDUSTRY_ASSET_LOCATION: string = "/assets/res-industry/drawable/";

    constructor(private iconName: string) { }
    public getReference(): string {
        return IndustryIconReference.INDUSTRY_ASSET_LOCATION + this.iconName.toLowerCase() + ".png";
    }
}
export class SDEIconReference implements IIconReference {
    private static SDE_ASSET_LOCATION: string = "/assets/res-sde/drawable/";

    constructor(private iconName: string) { }
    public getReference(): string {
        return SDEIconReference.SDE_ASSET_LOCATION + this.iconName.toLowerCase() + ".png";
    }
}
export class UIIconReference implements IIconReference {
    private static UI_ASSET_LOCATION: string = "/assets/res-ui/drawable/";

    constructor(private iconName: string) { }
    public getReference(): string {
        return UIIconReference.UI_ASSET_LOCATION + this.iconName.toLowerCase() + ".png";
    }
}
export class AwesomeIconReference implements IIconReference {
    private _reference: string;

    constructor(private iconReference: string) { }
    public getReference(): string {
        switch (this._reference) {
            case 'save':
                return 'fas fa-save';
            default:
                return '';
        }
    }
}
