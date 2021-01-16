// - DOMAIN
import { NeoCom } from '../NeoCom.domain';

export class AllianceV1 extends NeoCom {
    public allianceId: number = -5;
    public name: string = "-NAME-";
    public ticker: string = "TICK";
    public url4Icon: string;

    constructor(values: Object = {}) {
        super();
        Object.assign(this, values);
        this.jsonClass = "Alliance";
    }

    // - G E T T E R S
    public getId(): number {
        return this.allianceId;
    }
    public getName(): string {
        return this.name;
    }
    public getAllianceIcon(): string {
        if (this.isEmpty(this.url4Icon)) return 'https://image.eveonline.com/Alliance/' + this.allianceId + '_128.jpg';
        else return this.url4Icon;
    }
}
