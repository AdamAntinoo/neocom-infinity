// - CORE
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from '@env/environment';
// - DOMAIN
import { NeoCom } from './NeoCom.domain';
import { is } from 'date-fns/locale';
import { Pilot } from './Pilot.domain';
import { Corporation } from './Corporation.domain';
import { HALNode } from './hal/HALNode.hal';
import { HALLink } from './hal/HALLink.hal';
import { ResponseTransformer } from '@innovative/services/support/ResponseTransformer';

export class PilotV2 extends HALNode {
    public pilotId: number
    public pilotPublicData: any
    private lastKnownLocation: any
    private url4Icon: string
    // public corporationId: number
    private corporation: Corporation
    public raceData: object
    public ancestryData: object
    public bloodlineData: object

    // private corpObservable: Observable<Corporation>

    constructor(values: Object = {}) {
        super()
        Object.assign(this, values)
        // this.jsonClass = 'Pilot';
    }

    // - G E T T E R S   &   S E T T E R S
    public getPilotId(): number {
        return this.pilotId
    }
    public getCorporationId(): number {
        return this.pilotPublicData.corporation_id
    }
    public getPilotIcon(): string {
        if (null == this.url4Icon) return environment.DEFAULT_AVATAR_PLACEHOLDER;
        else return this.url4Icon;
    }
    public getLastKnownLocation(): string {
        if (null == this.lastKnownLocation) return '&gt;&gt;&gt; Last Location undefined'
        else {
            return 'pending baclend access'
        }
    }

    // - H A L
    public async getCorporation(): Promise<any> {
        if (undefined == this.corporation.href)
            return await new Observable(subscriber => {
                subscriber.next(this.corporation)
                subscriber.complete();
            }).toPromise()
        else {
            return await new Observable(subscriber => {
                this.resolve(this.corporation.href)
                    .pipe(map((data: any): Corporation => {
                        // console.log(">[HALResolver.resolve]> Transformation: " + transformer.description)
                        const response = new Corporation(data)
                        return response
                    }))
                    .subscribe((entrydata: Corporation): void => {
                        this.corporation = entrydata
                        subscriber.next(this.corporation)
                        subscriber.complete();
                        // this.corpObservable.
                        // return this.corporation
                    })
            }).toPromise()
            // this.corpObservable = new Observable(subscriber => {
            //     // subscriber.next(this.corporation)
            //     // subscriber.complete();
            // })
            // return this.corpObservable
        }
    }
}
