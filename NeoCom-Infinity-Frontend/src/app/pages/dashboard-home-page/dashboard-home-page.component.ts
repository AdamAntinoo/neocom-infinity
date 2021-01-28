// - CORE
import { Component } from '@angular/core'
import { OnInit } from '@angular/core'
import { Input } from '@angular/core'
import { HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
// - SERVICES
import { environment } from '@env/environment';
import { IsolationService } from '@innovative/services/isolation.service';
import { BackendService } from '@app/services/backend.service'
// - INNOVATIVE
import { BackgroundEnabledComponent } from '@innovative/components/background-enabled/background-enabled.component';
// - DOMAIN
import { PilotV2 } from '@domain/character/PilotV2.domain'
import { ResponseTransformer } from '@innovative/services/support/ResponseTransformer';
import { HALResolver } from '@app/services/HALResolver.service';
import { NeoComCredential } from '@domain/NeoComCredential.domain';
import { Corporation } from '@domain/Corporation.domain';
import { AllianceV1 } from '@domain/corporation/AllianceV1.domain';
import { NeoComFeature } from '@domain/ui/NeoComFeature.domain';
import { NeoComException } from '@innovative/domain/NeoComException';
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
    private pilot: PilotV2
    public corporation: Corporation
    // private ceo: PilotV2
    // private alliance: AllianceV1
    public planetaryFeature: NeoComFeature // The feature to open the dashboard page for Planetary
    public blueprintListFeature: NeoComFeature // The feature to open the list of current pilot blueprints
    public features: NeoComFeature[] = []

    private credential: NeoComCredential
    public corpFuture: Promise<Corporation>

    constructor(
        protected isolationService: IsolationService,
        protected backendService: BackendService,
        protected halResolver: HALResolver) {
        super()
        // Build the page features.
        this.features.push(new NeoComFeature({
            id: "planetary-dashboard",
            label: "Interacciones Planetarias",
            enabled: true,
            interaction: 'PAGEROUTE',
            route: "/planetary/dashboard",
            imageRef: 'assets/media/planetary-feature.jpeg'
        }))
        this.features.push(new NeoComFeature({
            id: "blueprint-analysis",
            label: "Catalogo de Blueprints",
            enabled: true,
            interaction: 'PAGEROUTE',
            route: "/industry/manufacture/blueprints",
            imageRef: 'assets/media/blueprints-feature.jpeg'
        }))
        this.features.push(new NeoComFeature({
            id: "loyalty-recommendations",
            label: "Recomendaciones de Ofertas Loyalty",
            enabled: true,
            interaction: 'PAGEROUTE',
            route: "/industry/manufacture/blueprints",
            imageRef: 'assets/media/blueprints-feature.jpeg'
        }))
    }

    public ngOnInit() {
        this.refresh()
    }
    protected refresh(): void {
        try {
            this.credential = this.getCredential()
            // this.downloadPilotPublicData(this.getPilotId())
        } catch (exception) {
            this.exception = exception
        }
    }

    // - I N T E R A C T I O N S
    public getPilotId(): number {
        // if (null != this.credential) 
        return this.getCredential().getAccountId()
        // else throw new NeoComException()
        //     .withTitle('Rendering Dashboard Page. No Credential Found.')
        //     .withMessage('Unable to display Pilot data. There is no credential available to access.')
        //     .withCause('Unexpected Exception. At this point there should exist a local session valid credential.')
    }
    // public downloadPilotPublicData(pilotId: number): void {
    //     console.log(">[DashboardHomePageComponent.downloadPilotPublicData]")
    //     this.backendConnections.push(
    //         this.backendService.apiGetPilotPublicData_v2(pilotId,
    //             new ResponseTransformer().setDescription('Transform response to a HATEOAS based Pilot Public Data instance.')
    //                 .setTransformation((entrydata: any): PilotV2 => {
    //                     return this.halResolver.connectResolver(new PilotV2(entrydata)) as PilotV2
    //                 }))
    //             .subscribe((response: PilotV2) => {
    //                 this.pilot = response
    //                 // this.testPromise()
    //                 this.corpFuture = this.pilot.getCorporation()
    //                 const i = 6
    //                 // this.downloadCorporation(this.pilot.getCorporationId())
    //                 // TODO - Create the code to download the CEO and the alliance using also HAL code.
    //                 // this.download
    //                 // this.ceo = this.corporation.getCeo()
    //                 // this.alliance = this.corporation.getAlliance()
    //             }, (error) => {
    //                 console.log('-[DashboardHomePageComponent.downloadPilotPublicData.exception]> Error message: ' +
    //                     JSON.stringify(error.error))
    //                 if (environment.showexceptions)
    //                     if (error instanceof HttpErrorResponse)
    //                         this.isolationService.processException(error)
    //             })
    //     )
    //     console.log("<[DashboardHomePageComponent.downloadPilotPublicData]");
    // }
    // private downloadCorporation(corporationId: number): void {
    //     console.log(">[DashboardHomePageComponent.downloadCorporation]")
    //     // this.backendConnections.push(
    //     //     this.pilot.getCorporation()
    //     //         .subscribe((corporation: Corporation) => {
    //     //             this.corporation = corporation
    //     //         }, (error) => {
    //     //             console.log('-[DashboardHomePageComponent.downloadCorporation.exception]> Error message: ' +
    //     //                 JSON.stringify(error.error))
    //     //             if (environment.showexceptions)
    //     //                 if (error instanceof HttpErrorResponse)
    //     //                     this.isolationService.processException(error)
    //     //         })
    //     // )
    //     this.corpFuture = this.pilot.getCorporation()
    // }
    // public async testPromise() {
    //     this.corpFuture = await this.pilot.getCorporation()
    //     const i = 6
    // }
    private getCredential(): NeoComCredential {
        const credentialJson = this.isolationService.getFromSession(environment.CREDENTIAL_KEY)
        // console.log('-[DashboardHomePageComponent.getCredential]> Credential data: ' + JSON.stringify(credentialJson))
        if (null == credentialJson)
            throw new NeoComException()
                .withTitle('Rendering Dashboard Page. No Credential Found.')
                .withMessage('Unable to display Pilot data. There is no credential available to access data.')
                .withCause('Unexpected Exception. At this point then should exist a local session valid credential.')
        else {
            const credential = new NeoComCredential(JSON.parse(credentialJson))
            return credential
        }
    }
}
