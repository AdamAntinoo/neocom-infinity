// - CORE
import { Component, Input } from '@angular/core';
import { OnInit } from '@angular/core';
import { CdkDragDrop } from '@angular/cdk/drag-drop';
import { moveItemInArray } from '@angular/cdk/drag-drop';
import { transferArrayItem } from '@angular/cdk/drag-drop';
// - DOMAIN
import { PlanetaryDataRecord } from '@domain/planetary/planetary-data-record';
import { PlanetaryDataService } from '../../service/PlanetaryData.service';
import { DashboardPageComponent } from '../../page/dashboard-page/dashboard-page.component';
import { PlanetaryData } from '@domain/planetary/PlanetaryData.domain';
import { NodeContainerRenderComponent } from '@shared/renders/node-container-render/node-container-render.component';
import { PlanetaryResource } from '@domain/planetary/planetary-resource';
import { GeneratedResource } from '@domain/planetary/generated-resource';
import { V1ResourceResearchPageComponent } from '../../page/v1-resource-research-page/v1-resource-research-page.component';

@Component({
    selector: 'v1-output-resources-panel',
    templateUrl: './v1-output-resources-panel.component.html',
    styleUrls: ['./v1-output-resources-panel.component.scss']
})
export class V1OutputResourcesPanelComponent extends NodeContainerRenderComponent {
    @Input() store: V1ResourceResearchPageComponent
    public t2Resources: GeneratedResource[] = []
    private planetList: PlanetaryData[] = []

    constructor(protected planetaryService: PlanetaryDataService) {
        super();
    }

    public ngOnInit(): void {
        this.refresh()
    }
    public clear(): void { }
    public refresh(): void {
        console.log('>[V1OutputResourcesPanelComponent.refresh]')
        this.planetList = this.planetaryService.getSelectedPlanets()
        this.t2Resources = this.sortByLevelDesc(this.processT2List(this.planetList)) // Get the list of T2 products available
        this.filterOutResources()
        console.log('-[V1OutputResourcesPanelComponent.refresh]>Resource count: ' + this.t2Resources.length)
        console.log('<[V1OutputResourcesPanelComponent.refresh]')
    }
    // public getSelectedResources(): GeneratedResource[] {
    //     return this.t2Resources
    // }

    private processT2List(planetList: PlanetaryData[]): GeneratedResource[] {
        const t2List: GeneratedResource[] = []
        for (let planet of planetList) {
            console.log('>[V1OutputResourcesPanelComponent.processT2List]>Processing planet: ' + planet.getPlanetName())
            const t2calculated = this.planetaryService.getT2Resources4Planet(planet)
            for (let t2 of t2calculated)
                t2List.push(t2)
        }
        return t2List
    }
    // private convertR02T1(resources: PlanetaryResource[]): PlanetaryResource[] {
    //     const t1Resources: PlanetaryResource[] = []
    //     for (let r of resources) {
    //         const t1Match = this.planetaryService.getT1Resource4R0(r)
    //         if (t1Match) t1Resources.push(t1Match)
    //     }
    //     return t1Resources
    // }
    private sortByLevelDesc(inputs: GeneratedResource[]): GeneratedResource[] {
        return inputs.sort((element1, element2) =>
            0 - (element2.getLevel() > element1.getLevel() ? -1 : 1)
        )
    }
    private filterOutResources(): void {
        // const selectedResources = this.planetaryService.getSelectedResources()
        // for (let resource of this.t2Resources) {
        //     if (this.isSelected(resource, selectedResources)) resource.select()
        // }
    }
    private isSelected(resource: GeneratedResource, selectionList: GeneratedResource[]): boolean {
        for (let target of selectionList)
            if (resource.isEqual(target)) return true
        return false
    }
}
