// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Input } from '@angular/core';
import { CdkDragDrop } from '@angular/cdk/drag-drop';
import { moveItemInArray } from '@angular/cdk/drag-drop';
import { transferArrayItem } from '@angular/cdk/drag-drop';
// - APP
// - DOMAIN
import { BackgroundEnabledComponent } from '@innovative/components/background-enabled/background-enabled.component';
import { PlanetaryDataRecord } from '@domain/planetary/planetary-data-record';
import { PlanetaryDataService } from '../../service/PlanetaryData.service';
import { KnownSystem } from '@domain/planetary/KnownSystem.domain';
import { ResponseTransformer } from 'neocom-domain/ResponseTransformer';
import { IRefreshable } from '@innovative/domain/interfaces/IRefreshable.interface';
import { PlanetaryData } from '@domain/planetary/PlanetaryData.domain';

@Component({
    selector: 'v1-known-planets-panel',
    templateUrl: './v1-known-planets-panel.component.html',
    styleUrls: ['./v1-known-planets-panel.component.scss']
})
export class V1KnownPlanetsPanelComponent extends BackgroundEnabledComponent implements OnInit, IRefreshable {
    @Input() system: KnownSystem
    public planetList: PlanetaryData[] = []

    constructor(protected planetaryDataService: PlanetaryDataService) {
        super();
    }

    public ngOnInit(): void {
        this.refresh();
    }
    // - I R E F R E S H A B L E
    public clean(): void { }
    public refresh(): void {
        this.clean()
        this.backendConnections.push(
            this.planetaryDataService.apiv1_GetPlanets4System(this.system.getUniqueId(), new ResponseTransformer()
                .setDescription('Transforms Planetary packed data into planet data records.')
                .setTransformation((entrydata: any): PlanetaryData[] => {
                    let results: PlanetaryData[] = [];
                    if (entrydata instanceof Array) {
                        for (let key in entrydata) {
                            const planetary = new PlanetaryData(entrydata[key])
                            results.push(planetary.transform(this.planetaryDataService))
                        }
                    }
                    return results;
                })).subscribe((dataList) => {
                    // console.log('Processing planet data')
                    // for (let index = 0; index < dataList.length; index++) {
                    //     const element = dataList[index];
                    //     const planetData: PlanetaryDataRecord = new PlanetaryDataRecord(element)
                    //     this.planetList.push(planetData)
                    // }
                    this.planetList = dataList
                })
        )
    }
    // - I N T E R A C T I O N S
    public getSystemName(): string {
        if (this.system) return this.system.getName()
        else return '-'
    }
    public getPlanetList(): PlanetaryData[] {
        if (this.planetList.length > 0) return this.planetList
        else return []
    }
}
