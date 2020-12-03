// - CORE
import { CdkDragDrop } from '@angular/cdk/drag-drop';
import { moveItemInArray } from '@angular/cdk/drag-drop';
import { transferArrayItem } from '@angular/cdk/drag-drop';
import { Component, ViewChild } from '@angular/core';
import { OnInit } from '@angular/core';
import { PlanetaryDataRecord } from 'src/app/domain/planetary-data-record';
import { BackgroundEnabledComponent } from '@bit/innovative.innovative.innovative-core'
import { PlanetaryResource } from '@domain/planetary-resource';
import { GeneratedResource } from '@domain/generated-resource';
import { DataService } from '@app/services/data-service.service';
import { V1SelectedPlanetsPanelComponent } from '../../panel/v1-selected-planets-panel/v1-selected-planets-panel.component';
import { V1T2ClassificationPanelComponent } from '../../panel/v1-t2-classification-panel/v1-t2-classification-panel.component';


@Component({
    selector: 'npi-v1-planetary-page',
    templateUrl: './v1-planetary-page.component.html',
    styleUrls: ['./v1-planetary-page.component.scss']
})
export class V1PlanetaryPageComponent extends BackgroundEnabledComponent implements OnInit {
    // @ViewChild(V1SelectedPlanetsPanelComponent) selectedPlanetsComponent: V1SelectedPlanetsPanelComponent | undefined
    @ViewChild(V1T2ClassificationPanelComponent) selectedT2ResourcesComponent: V1T2ClassificationPanelComponent | undefined
    public self : V1PlanetaryPageComponent|undefined
    public planetList: PlanetaryDataRecord[] = []
    public t2Resources: GeneratedResource[] = []
    public selectedResources: GeneratedResource[] = []

    constructor(protected dataService: DataService) {
        super();
        this.self =this
    }

    public ngOnInit(): void {
        // this.refresh();
    }
    public getSelectedPlanets(): PlanetaryDataRecord[] {
        return this.planetList
    }
    public getSelectedResources(): GeneratedResource[] {
        return this.selectedResources
    }
    public updatePlanetList(newSelectedPlanetList: PlanetaryDataRecord[]): void {
        console.log('V1PlanetaryPageComponent.updatePlanetList')
        this.planetList = newSelectedPlanetList
        if (this.selectedT2ResourcesComponent) this.selectedT2ResourcesComponent.refresh()
    }
    private clear(): void { }
    private refresh(): void {
        this.backendConnections.push(
            this.dataService.apiGetPlanetPIInformation().subscribe((dataList) => {
                for (let index = 0; index < dataList.length; index++) {
                    const element = dataList[index];
                    const planetData: PlanetaryDataRecord = new PlanetaryDataRecord(element)
                    this.planetList.push(planetData)
                }
                this.t2Resources = this.sortByLevelDesc(this.processT2List()) // Get the list of T2 products available
                // this.selectedResources.push ( this.t2Resources[0])
            })
        )
    }
    private processT2List(): GeneratedResource[] {
        const t2List: GeneratedResource[] = []
        for (let planet of this.planetList) {
            console.log('>processT2List>Processing planet: ' + planet.getPlanetName())
            const t2calculated = this.dataService.getT2Resources4Planet(planet)
            for (let t2 of t2calculated)
                t2List.push(t2)
        }
        return t2List
    }
    private convertR02T1(resources: PlanetaryResource[]): PlanetaryResource[] {
        const t1Resources: PlanetaryResource[] = []
        for (let r of resources) {
            const t1Match = this.dataService.getT1Resource4R0(r)
            if (t1Match) t1Resources.push(t1Match)
        }
        return t1Resources
    }
    private sortByLevelDesc(inputs: GeneratedResource[]): GeneratedResource[] {
        return inputs.sort((element1, element2) =>
            0 - (element2.getLevel() > element1.getLevel() ? -1 : 1)
        )
    }

    public drop(event: CdkDragDrop<GeneratedResource[]>) {
        if (event.previousContainer === event.container) {
            moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
        } else {
            transferArrayItem(event.previousContainer.data,
                event.container.data,
                event.previousIndex,
                event.currentIndex);
        }
    }
}
