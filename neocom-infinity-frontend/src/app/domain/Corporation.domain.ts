// - DOMAIN
import { AllianceV1 } from './corporation/AllianceV1.domain';
import { Pilot } from './Pilot.domain';
import { NeoCom } from './NeoCom.domain';
import { NeoComConstants } from '../platform/NeocomConstants.platform';
import { HALLink } from './hal/HALLink.hal';
import { HALNode } from './hal/HALNode.hal';

export class Corporation extends HALNode  {
    public corporationId: number;
    public corporationPublicData: any;
    public ceoPilotData: Pilot;
    public allianceId: number;
    public alliance: AllianceV1;
    public url4Icon: string;
    // public homeStation: Location = new Location();
    public href: string

    constructor(values: Object = {}) {
        super();
        Object.assign(this, values);
        // this.jsonClass = 'Corporation';
        // Transform child instances to class objects.
        if (null != this.ceoPilotData) this.ceoPilotData = new Pilot(this.ceoPilotData);
        if (null != this.alliance) this.alliance = new AllianceV1(this.alliance);
    }
    public getName(): string {
        if (null != this.corporationPublicData) return this.corporationPublicData.name;
        else return '-';
    }
    public getTicker(): string {
        if (null != this.corporationPublicData) return this.corporationPublicData.ticker;
        else return '----';
    }
    public getMemberCount(): number {
        if (null != this.corporationPublicData) return this.corporationPublicData.member_count;
        else return 0;
    }
    public getAlliance(): AllianceV1 {
        return this.alliance;
    }
    public getIconUrl(): string {
        if (null != this.url4Icon) return this.url4Icon;
        else return NeoComConstants.DEFAULT_CORPORATION_AVATAR_PLACEHOLDER;
    }
    public getCeo(): Pilot {
        return this.ceoPilotData;
    }
}
