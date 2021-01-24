// - DOMAIN
import { NeoComFeatureConverter } from '@domain/converter/NeoComFeature.converter';
import { NeoCom } from '@domain/NeoCom.domain';
import { EInteraction } from './EInteraction.enum';

export class NeoComFeature extends NeoCom {
    public id: string
    /** The contents of the feature name label. */
    public label: string = '-' 
    /** If this feature is currently active this flag is then set to <code>true</code>. The rendering should be also resalted. */
    public active: boolean = false
    /** If the feature can be activated then this field is true. Else the feature cannot fire the event and is dimmed. */
    public enabled: boolean = false
    public interaction: EInteraction = EInteraction.PAGEROUTE;
   /** If the <code>interaction</code> is of type PAGEROUTE then this is the destination route to open when the feature is activated. */
    public route: string = '/'
     /** The path to the image that should be rendeered inside the feature area. */
    public imageRef :string
    public modifier: string
    public dialog: string

    constructor(values: Object = {}) {
        super();
        const convertedValues: object = new NeoComFeatureConverter().convertInstance(values);
        Object.assign(this, convertedValues);
        this.jsonClass = 'Feature';
    }
    public getRoute(): string {
        return this.route;
    }
    public getImageRef ():string {
        if ( this.imageRef)return this.imageRef
        else return 'assets/media/default-feature.jpeg'
    }
    public getLabel ():string{
        return this.label
    }
    public equals(target: NeoComFeature): boolean {
        if (this.label != target.label) return false;
        if (this.active != target.active) return false;
        if (this.interaction != target.interaction) return false;
        if (this.route != target.route) return false;
        return true;
    }
    public activate(): boolean {
        const result = this.active;
        this.active = true;
        return result;
    }
    public deactivate(): boolean {
        const result = this.active;
        this.active = false;
        return result;
    }
}
