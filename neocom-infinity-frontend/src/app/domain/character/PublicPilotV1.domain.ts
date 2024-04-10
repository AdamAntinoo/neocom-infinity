// - CORE
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from '@env/environment';
// - DOMAIN
import { Corporation } from '../Corporation.domain';
import { HALNode } from '../hal/HALNode.hal';
import { HALLink } from '../hal/HALLink.hal';
import { ResponseTransformer } from '@innovative/services/support/ResponseTransformer';
import { PlatformConstants } from '@env/PlatformConstants';
import { CorporationV1 } from '../corporation/CorporationV1.domain';
import { SpaceLocationV1 } from '@domain/location/SpaceLocationV1.domain';
import { UniverseRaceData } from '@domain/esi/UniverseRaceData.esi';
import { UniverseAncestryData } from '@domain/esi/UniverseAncestryData.esi';
import { UniverseBloodlineData } from '@domain/esi/UniverseBloodlineData.esi';
import { NeoComDelayed } from '@domain/core/NeoComDelayed.domain';
import { PublicCorporationV1 } from '@domain/corporation/PublicCorporationV1.domain';
import { NeoComConstants } from '@app/platform/NeocomConstants.platform';

export class PublicPilotV1 extends NeoComDelayed {
    public pilotId: number
    public name:string
    public corporation: PublicCorporationV1
    public birthday:string
    public description:string
    public gender:string
    public securityStatus:number
    public url4Icon: string
    public raceData: UniverseRaceData
    public ancestryData: UniverseAncestryData
    public bloodlineData: UniverseBloodlineData

    constructor(values: Object = {}) {
        super()
        Object.assign(this, values)
        this.jsonClass = 'PublicPilotV1';
    }

    // - G E T T E R S
    public getPilotId(): number {
        return this.pilotId
    }
    public getCorporationId(): number {
      if ( this.corporation)  return this.corporation.getCorporationId()
      else return -1
    }
    public getPilotIcon(): string {
        if (null == this.url4Icon) return NeoComConstants.DEFAULT_AVATAR_PLACEHOLDER;
        else return this.url4Icon;
    }
}
