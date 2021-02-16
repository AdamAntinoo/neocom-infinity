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
import { PilotV2 } from '@domain/character/PilotV2.domain'
import { ResponseTransformer } from '@innovative/services/support/ResponseTransformer';
import { HALResolver } from '@app/services/HALResolver.service';
import { NCVariant } from '@env/NeoComVariants';
import { IRefreshable } from '@innovative/domain/interfaces/IRefreshable.interface';
import { NeoComException } from '@innovative/domain/NeoComException';
import { ErrorToNeoComExceptionConverter } from '@innovative/domain/converters/ErrorToNeoComException.converter';
import { PilotV2Dto } from '@domain/dto/PilotV2Dto.dto';
import { PublicPilotV1 } from '@domain/character/PublicPilotV1.domain';

/**
 * Get the current plot identifier from the authentication token. With this identifier go to the backend and download a fresh instance of the public pilot data. Once we have the data we can then render it with the Dashboard requirements.
 */
@Component({
    selector: 'v2-pilot-public-data-panel',
    templateUrl: './v2-pilot-public-data-panel.component.html',
    styleUrls: ['./v2-pilot-public-data-panel.component.scss']
})
export class V2PilotPublicDataPanelComponent extends BackgroundEnabledComponent implements OnInit, IRefreshable {
    @Input() variant: string = NCVariant.DEFAULT
    @Input() identifier: number
    public pilot: PublicPilotV1
    // private transformer: ResponseTransformer

    constructor(
        protected isolationService: IsolationService,
        protected backendService: BackendService) {
        super()
        // this.transformer = new ResponseTransformer()
        //     .setDescription('Transform response and resolve any HAL links.')
        //     .setTransformation((entrydata: any) => {
        //         return new PilotV2Dto(entrydata).transform(halResolver)
        //     })
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

    // - I R E F R E S H A B L E
    public clean(): void {
        this.pilot = undefined
        this.exception = undefined
    }
    public refresh(): void {
        this.clean()
        try {
            this.downloadPilotPublicData()
        } catch (exception) {
            this.exception = exception
        }
    }

    // - B A C K E N D
    public downloadPilotPublicData(): void {
        console.log(">[PilotPublicDataPavelV2Component.downloadPilotPublicData]")
        if (this.identifier)
            this.backendConnections.push(
                this.backendService.apiv1_GetPublicPilotData(this.identifier)
                    .subscribe((response: PublicPilotV1) => {
                        this.pilot = response
                    }, (error) => {
                        console.log('-[PilotPublicDataPavelV2Component.downloadPilotPublicData.exception]> Error message: ' +
                            JSON.stringify(error.error))
                        if (environment.showexceptions)
                            if (error instanceof HttpErrorResponse)
                                this.isolationService.processException(error)
                        throw new ErrorToNeoComExceptionConverter().convert(error)
                    })
            )
        console.log("<[PilotPublicDataPavelV2Component.downloadPilotPublicData]");
    }

    // public getPilotIdentifier(): number {
    //     return this.getCredential().getAccountId()
    // }
    // public getCredential(): Credential {
    //     const credentialJson = this.isolationService.getFromSession(PlatformConstants.CREDENTIAL_KEY)
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
