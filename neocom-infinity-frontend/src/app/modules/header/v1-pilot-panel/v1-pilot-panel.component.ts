// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { OnDestroy } from '@angular/core';
import { Input } from '@angular/core';
import { environment } from '@env/environment';
import { Subscription } from 'rxjs';
// - SERVICES
import { BackendService } from '@app/services/backend.service';
import { AppStoreService } from '@app/services/appstore.service';
// - DOMAIN
import { Corporation } from '@app/domain/Corporation.domain';
import { AllianceV1 } from '@domain/corporation/AllianceV1.domain';
import { Pilot } from '@app/domain/Pilot.domain';
import { CorporationDataResponse } from '@app/domain/dto/CorporationDataResponse.dto';
import { BackgroundEnabledComponent } from '@innovative/components/background-enabled/background-enabled.component';
import { HttpErrorResponse } from '@angular/common/http';
import { ErrorToNeoComExceptionConverter } from '@innovative/domain/converters/ErrorToNeoComException.converter';
import { IsolationService } from '@innovative/services/isolation.service';
import { IRefreshable } from '@innovative/domain/interfaces/IRefreshable.interface';
import { PublicPilotV1 } from '@domain/character/PublicPilotV1.domain';

@Component({
    selector: 'v1-pilot-panel',
    templateUrl: './v1-pilot-panel.component.html',
    styleUrls: ['./v1-pilot-panel.component.scss']
})
export class V1PilotPanelComponent extends BackgroundEnabledComponent implements OnInit, IRefreshable {
    @Input() identifier: number
    public pilot: PublicPilotV1

    constructor(
        protected isolationService: IsolationService,
        protected backendService: BackendService) {
        super()
    }

    public ngOnInit(): void {
        console.log(">[V1PilotPanelComponent.ngOnInit]");
        this.refresh();
        console.log("<[V1PilotPanelComponent.ngOnInit]");
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
            this.downloadPilotData()
        } catch (exception) {
            this.exception = exception
        }
    }
    // - B A C K E N D
    private downloadPilotData(): void {
        console.log(">[V1PilotPanelComponent.downloadPilotData]")
        if (this.identifier)
            this.backendConnections.push(
                this.backendService.apiv1_GetPilotData(this.identifier)
                    .subscribe((response: PublicPilotV1) => {
                        this.pilot = response
                    }, (error) => {
                        console.log('-[V1PilotPanelComponent.downloadPilotData.exception]> Error message: ' +
                            JSON.stringify(error.error))
                        if (environment.showexceptions)
                            if (error instanceof HttpErrorResponse)
                                this.isolationService.processException(error)
                        throw new ErrorToNeoComExceptionConverter().convert(error)
                    })
            )
        console.log("<[V1PilotPanelComponent.downloadPilotData]");
    }
}
