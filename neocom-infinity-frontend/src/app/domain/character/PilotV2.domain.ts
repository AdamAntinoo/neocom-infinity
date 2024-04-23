// - CORE
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from '@env/environment';
// - DOMAIN
import { Corporation } from '../Corporation.domain';
import { HALNode } from '../hal/HALNode.hal';
import { HALLink } from '../hal/HALLink.hal';
import { ResponseTransformer } from 'neocom-domain/ResponseTransformer';
import { PlatformConstants } from '@env/PlatformConstants';
import { CorporationV1 } from '../corporation/CorporationV1.domain';
import { SpaceLocationV1 } from '@domain/location/SpaceLocationV1.domain';
import { UniverseRaceData } from '@domain/esi/UniverseRaceData.esi';
import { UniverseAncestryData } from '@domain/esi/UniverseAncestryData.esi';
import { UniverseBloodlineData } from '@domain/esi/UniverseBloodlineData.esi';
import { NeoComConstants } from '@app/platform/NeocomConstants.platform';
import { PublicPilotV1 } from './PublicPilotV1.domain';

export class PilotV2 extends PublicPilotV1 {
    public totalSkillpoints: number
    public walletBalance: number
    public currentShipName: string
    public currentShipTypeName: string
    public lastKnownLocation: SpaceLocationV1

    constructor(values: Object = {}) {
        super(values)
        this.jsonClass = 'Pilot';
    }

    // - G E T T E R S
    public getPilotId(): number {
        return this.pilotId
    }
    public getCorporationId(): number {
        if (this.corporation) return this.corporation.getCorporationId()
        else return -1
    }
    public getPilotIcon(): string {
        if (null == this.url4Icon) return NeoComConstants.DEFAULT_AVATAR_PLACEHOLDER;
        else return this.url4Icon;
    }
    public getLastKnownLocation(): string {
        if (null == this.lastKnownLocation) return '&gt;&gt;&gt; Last Location undefined'
        else {
            return 'pending baclend access'
        }
    }

    // // - H A L
    // public async getCorporation(): Promise<any> {
    //     if (undefined == this.corporation.href)
    //         return await new Observable(subscriber => {
    //             subscriber.next(this.corporation)
    //             subscriber.complete();
    //         }).toPromise()
    //     else {
    //         return await new Observable(subscriber => {
    //             this.resolve(this.corporation.href)
    //                 .pipe(map((data: any): Corporation => {
    //                     // console.log(">[HALResolver.resolve]> Transformation: " + transformer.description)
    //                     const response = new Corporation(data)
    //                     return response
    //                 }))
    //                 .subscribe((entrydata: Corporation): void => {
    //                     this.corporation = entrydata
    //                     subscriber.next(this.corporation)
    //                     subscriber.complete();
    //                     // this.corpObservable.
    //                     // return this.corporation
    //                 })
    //         }).toPromise()
    //         // this.corpObservable = new Observable(subscriber => {
    //         //     // subscriber.next(this.corporation)
    //         //     // subscriber.complete();
    //         // })
    //         // return this.corpObservable
    //     }
    // }
}
