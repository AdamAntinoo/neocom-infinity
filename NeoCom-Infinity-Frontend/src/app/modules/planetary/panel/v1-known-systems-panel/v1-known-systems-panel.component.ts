// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { OnDestroy } from '@angular/core';
import { Input } from '@angular/core';
import { Subscription } from 'rxjs';
// - DOMAIN
import { IRefreshable } from '@innovative/domain/interfaces/core/IRefreshable.interface';
import { platformConstants } from '@env/platform-constants';
import { PlanetaryDataService } from '../../service/PlanetaryData.service';
import { ResponseTransformer } from '@innovative/services/support/ResponseTransformer';
import { KnownSystem } from '@domain/planetary/KnownSystem.domain';
import { environment } from '@env/environment';
import { HttpErrorResponse } from '@angular/common/http';
import { IsolationService } from '@innovative/services/isolation.service';
import { V1ResourceResearchPageComponent } from '../../page/v1-resource-research-page/v1-resource-research-page.component';
import { NCVariant } from '@env/NeoComVariants';
import { AppPanelComponent } from '@innovative/components/app-panel/app-panel.component';

@Component({
    selector: 'v1-known-systems-panel',
    templateUrl: './v1-known-systems-panel.component.html',
    styleUrls: ['./v1-known-systems-panel.component.scss']
})
export class V1KnownSystemsPanelComponent extends AppPanelComponent implements OnInit, IRefreshable {
    @Input() store: V1ResourceResearchPageComponent
    // private selectedSystem: KnownSystem

    constructor(
        protected isolationService: IsolationService,
        protected planetaryService: PlanetaryDataService) {
        super();
    }
    public ngOnInit(): void {
        console.log(">[V1KnownSystemsPanelComponent.ngOnInit]");
        this.startDownloading();
        this.setVariant(NCVariant.KNOWN_SYSTEMS)
        this.refresh();
        console.log("<[V1KnownSystemsPanelComponent.ngOnInit]");
    }
    // - A P I
    public setSelectedSystem(system: KnownSystem): void {
        // this.selectedSystem = system
        this.store.setSelectedSystem(system)
    }
    // - R E F R E S H A B L E
    public clean(): void {
    }
    public refresh(): void {
        this.clean()
        this.accessKnownSystems()
    }
    private accessKnownSystems(): void {
        console.log(">[V1KnownSystemsPanelComponent.accessKnownSystems]");
        this.backendConnections.push(
            this.planetaryService.apiv1_GetKnownPlanetSystems(new ResponseTransformer()
                .setDescription('Transforms System Universe data from backend into frontend instances.')
                .setTransformation((entrydata: any): KnownSystem[] => {
                    let results: KnownSystem[] = [];
                    if (entrydata instanceof Array) {
                        for (let key in entrydata)
                            results.push(new KnownSystem(entrydata[key]));
                    }
                    return results;
                }))
                .subscribe((response: KnownSystem[]) => {
                    const systemList = this.sortSystemByName(response);
                    console.log('-[V1KnownSystemsPanelComponent.accessKnownSystems]> Nodes downloaded: ' + systemList.length);
                    this.completeDowload(systemList); // Notify the completion of the download.
                }, (error) => {
                    console.log('-[V1KnownSystemsPanelComponent.accessKnownSystems.exception]> Error message: ' +
                        JSON.stringify(error.error))
                    if (environment.showexceptions)
                        if (error instanceof HttpErrorResponse)
                            this.isolationService.processException(error)
                })
        )
        console.log("<[V1KnownSystemsPanelComponent.accessKnownSystems]");
    }
    private sortSystemByName(inputs: KnownSystem[]): KnownSystem[] {
        return inputs.sort((element1, element2) =>
            0 - (element2.getName() > element1.getName() ? -1 : 1)
        )
    }
}
