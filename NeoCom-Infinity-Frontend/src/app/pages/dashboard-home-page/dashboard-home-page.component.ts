// - CORE
import { Component } from '@angular/core'
import { OnInit } from '@angular/core'
import { Input } from '@angular/core'
import { HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
// - SERVICES
import { environment } from '@env/environment';
import { IsolationService } from '@app/platform/isolation.service'
import { BackendService } from '@app/services/backend.service'
// - INNOVATIVE
import { BackgroundEnabledComponent } from '@bit/innovative.innovative.innovative-core';
// - DOMAIN
import { PilotV2 } from '@domain/PilotV2.domain'
import { ResponseTransformer } from '@app/services/support/ResponseTransformer';
import { HALResolver } from '@app/services/HALResolver.service';
import { Credential } from '@app/domain/Credential.domain';
import { NeoComException } from '@domain/core/NeoComException.core';
import { NeoComCredential } from '@domain/NeoComCredential.domain';
import { Corporation } from '@domain/Corporation.domain';
import { Alliance } from '@domain/Alliance.domain';
/**
 * This is the landing page for a login. The page has teh next structure:
 * ROW 1 - The application header and the current server status.
 * ROW 2 - The current Pilot, along with the Corporation, the corproation CEO if applies and and corporation Alliance if exists
 * ROW 3 - The list of available functionalities for this pilot.
 * 
 * The component needs the current logged pilot identifier. This is obtained from the authentication credential on the session store. From this identifer we go to to backend to get the Pilot's public data. From it we can get the pilot's corporation and other data on the next fashion:
 * Current Pilot --> Corporation --> Corporation CEO
 *                               --> Alliance is exists
 * Because of HAL data contents instead using specific code to download the dependand data the new component can use the HATEOAS specification to access the other detencencies through the parent instance, on this case we can get the pilot's corporation from the pilot instance with the method 'getCorporation()'
 */
@Component({
    selector: 'dashboard-home-page',
    templateUrl: './dashboard-home-page.component.html',
    styleUrls: ['./dashboard-home-page.component.scss']
})
export class DashboardHomePageComponent extends BackgroundEnabledComponent implements OnInit {
    public exception: NeoComException
    private pilot: PilotV2
    public corporation: Corporation
    private ceo: PilotV2
    private alliance: Alliance

    private credential: NeoComCredential
    public corpFuture: Promise<Corporation>

    constructor(
        protected isolationService: IsolationService,
        protected backendService: BackendService,
        protected halResolver: HALResolver) {
        super()
    }

    public ngOnInit() {
        this.refresh()
    }
    protected refresh(): void {
        try {
            this.credential = this.getCredential()
            this.downloadPilotPublicData(this.getPilotId())
        } catch (exception) {
            this.exception = exception
        }
    }
    public getPilotId(): number {
        if (null != this.credential) return this.getCredential().getAccountId()
        else throw new NeoComException()
            .withTitle('Rendering Dashboard Page. No Credential Found.')
            .withMessage('Unable to display Pilot data. There is no credential available to access.')
            .withCause('Unexpected Exception. At this point the ever should exist a local session valid credential.')
    }
    private getCredential(): NeoComCredential {
        const credentialJson = this.isolationService.getFromSession(environment.CREDENTIAL_KEY)
        console.log('-[DashboardHomePageComponent.getCredential]> Credential data: ' + JSON.stringify(credentialJson))
        if (null == credentialJson)
            throw new NeoComException()
                .withTitle('Rendering Dashboard Page. No Credential Found.')
                .withMessage('Unable to display Pilot data. There is no credential available to access.')
                .withCause('Unexpected Exception. At this point the ever should exist a local session valid credential.')
        else {
            const credential = new NeoComCredential(JSON.parse(credentialJson))
            return credential
        }
    }
    public downloadPilotPublicData(pilotId: number): void {
        console.log(">[DashboardHomePageComponent.downloadPilotPublicData]")
        this.backendConnections.push(
            this.backendService.apiGetPilotPublicData_v2(pilotId,
                new ResponseTransformer().setDescription('Transform response to a HATEOAS based Pilot Public Data instance.')
                    .setTransformation((entrydata: any): PilotV2 => {
                        return this.halResolver.connectResolver(new PilotV2(entrydata)) as PilotV2
                    }))
                .subscribe((response: PilotV2) => {
                    this.pilot = response
                    // this.testPromise()
                    this.corpFuture = this.pilot.getCorporation()
                    const i = 6
                    // this.downloadCorporation(this.pilot.getCorporationId())
                    // TODO - Create the code to download the CEO and the alliance using also HAL code.
                    // this.download
                    // this.ceo = this.corporation.getCeo()
                    // this.alliance = this.corporation.getAlliance()
                }, (error) => {
                    console.log('-[DashboardHomePageComponent.downloadPilotPublicData.exception]> Error message: ' +
                        JSON.stringify(error.error))
                    if (environment.showexceptions)
                        if (error instanceof HttpErrorResponse)
                            this.isolationService.processException(error)
                })
        )
        console.log("<[DashboardHomePageComponent.downloadPilotPublicData]");
    }
    private downloadCorporation(corporationId: number): void {
        console.log(">[DashboardHomePageComponent.downloadCorporation]")
        // this.backendConnections.push(
        //     this.pilot.getCorporation()
        //         .subscribe((corporation: Corporation) => {
        //             this.corporation = corporation
        //         }, (error) => {
        //             console.log('-[DashboardHomePageComponent.downloadCorporation.exception]> Error message: ' +
        //                 JSON.stringify(error.error))
        //             if (environment.showexceptions)
        //                 if (error instanceof HttpErrorResponse)
        //                     this.isolationService.processException(error)
        //         })
        // )
        this.corpFuture = this.pilot.getCorporation()
    }
    public async testPromise () {
        this.corpFuture = await this.pilot.getCorporation()
        const i = 6
    }
}
