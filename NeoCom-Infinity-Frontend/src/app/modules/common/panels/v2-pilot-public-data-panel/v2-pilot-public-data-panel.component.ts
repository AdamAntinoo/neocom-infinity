// - CORE
import { Component } from '@angular/core'
import { OnInit } from '@angular/core'
import { Input } from '@angular/core'
import { HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
// - SERVICES
import { environment } from '@env/environment';
import { BackendService } from '@app/services/backend.service'
// - INNOVATIVE
import { IsolationService } from '@innovative/services/isolation.service';
import { BackgroundEnabledComponent } from '@innovative/components/background-enabled/background-enabled.component';
// - DOMAIN
import { PilotV2 } from '@domain/PilotV2.domain'
import { ResponseTransformer } from '@innovative/services/support/ResponseTransformer';
import { HALResolver } from '@app/services/HALResolver.service';
import { Credential } from '@app/domain/Credential.domain';
import { NeoComException } from '@domain/core/NeoComException.core';

/**
 * Get the current plot identifier from the authentication token. With this identifier go to the backend and download a fresh instance of the public pilot data. Once we have the data we can then render it with the Dashboard requirements.
 */
@Component({
    selector: 'v2-pilot-public-data-panel',
    templateUrl: './v2-pilot-public-data-panel.component.html',
    styleUrls: ['./v2-pilot-public-data-panel.component.scss']
})
export class V2PilotPublicDataPanelComponent extends BackgroundEnabledComponent implements OnInit {
    @Input() variant: string = 'DEFAULT'
    @Input() identifier: number
    public pilot: PilotV2
    // public exception: NeoComException
    // public s: Subscription

    constructor(
        protected isolationService: IsolationService,
        protected backendService: BackendService,
        protected halResolver: HALResolver) {
        super()
    }

    public ngOnInit(): void {
        console.log(">[PilotPublicDataPavelV2Component.ngOnInit]");
        this.refresh();
        console.log("<[PilotPublicDataPavelV2Component.ngOnInit]");
    }

    // - I N T E R A C T I O N S
    public getUniqueId(): number {
        return this.identifier
    }
    // - R E F R E S H A B L E
    public clean(): void {
        this.pilot = undefined
        // this.exception = undefined
    }
    public refresh(): void {
        this.clean()
        this.downloadPilotPublicData()
    }
    // - B A C K E N D
    public downloadPilotPublicData(): void {
        console.log(">[PilotPublicDataPavelV2Component.downloadPilotPublicData]")
        // try {
        if (this.identifier)
            this.backendConnections.push(
                this.backendService.apiGetPilotPublicData_v2(this.identifier,
                    new ResponseTransformer().setDescription('Transform response to a HATEOAS based Pilot Publc Data instance.')
                        .setTransformation((entrydata: any): PilotV2 => {
                            return this.halResolver.connectResolver(new PilotV2(entrydata)) as PilotV2
                        }))
                    .subscribe((response: PilotV2) => {
                        this.pilot = response
                    }, (error) => {
                        console.log('-[PilotPublicDataPavelV2Component.downloadPilotPublicData.exception]> Error message: ' +
                            JSON.stringify(error.error))
                        // this.exception = error.error.message
                        if (environment.showexceptions)
                            if (error instanceof HttpErrorResponse)
                                this.isolationService.processException(error)
                    })
            )
        // } catch (exception) {
        //     this.exception = exception
        // }
        console.log("<[PilotPublicDataPavelV2Component.downloadPilotPublicData]");
    }

    // public getPilotIdentifier(): number {
    //     return this.getCredential().getAccountId()
    // }
    // public getCredential(): Credential {
    //     const credentialJson = this.isolationService.getFromSession(environment.CREDENTIAL_KEY)
    //     if (null == credentialJson)
    //         throw new NeoComException()
    //             .withTitle('Rendering Pilot Public Data. No Credential Found.')
    //             .withMessage('Unable to display Pilot data. There is no credential available to access.')
    //             .withCause('Unexpected Exception. At this point the ever should exist a local session valid credential.')
    //     else {
    //         const credential = new Credential(JSON.parse(credentialJson))
    //         return credential
    //     }
    // }
}
