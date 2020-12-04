import { Component, Input, OnInit } from '@angular/core';
import { PlanetaryDataRecord } from 'src/app/domain/planetary-data-record';
import { DataService } from 'src/app/services/data-service.service';
import { BackgroundEnabledComponent } from '@bit/innovative.innovative.innovative-core'
import { PlanetaryResource } from '@domain/planetary-resource';
import { GeneratedResource } from '@domain/generated-resource';
import { V1PlanetaryPageComponent } from '../../pages/v1-planetary-page/v1-planetary-page.component';
import { PlanetaryDataService } from '@app/services/planetary-data.service';

@Component({
    selector: 'npi-v1-t2-classification-panel',
    templateUrl: './v1-t2-classification-panel.component.html',
    styleUrls: ['./v1-t2-classification-panel.component.scss'],
})
/**
 * This panels shows the list of planets and for each one the list of possible T2 resources that can be generated along with the index calculation
 * 
 */
export class V1T2ClassificationPanelComponent extends BackgroundEnabledComponent implements OnInit {
    @Input() store: V1PlanetaryPageComponent | undefined
    public planetList: PlanetaryDataRecord[] = []
    public t2Resources: GeneratedResource[] = []

    constructor(
        protected dataService: DataService,
        protected planetaryDataService: PlanetaryDataService) {
        super();
    }

    public ngOnInit(): void {
        this.refresh();
    }
    private clear(): void { }
    public refresh(): void {
        this.planetList = this.planetaryDataService.getSelectedPlanets()
        this.t2Resources = this.sortByLevelDesc(this.processT2List()) // Get the list of T2 products available
        this.filterOutResources()
    }
    public getSelectedResources(): GeneratedResource[] {
        return this.t2Resources
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
    private filterOutResources(): void {
        const selectedResources = this.planetaryDataService.getSelectedResources()
        for (let resource of this.t2Resources) {
            if (this.isSelected(resource, selectedResources)) resource.select()
        }
    }
    private isSelected(resource: GeneratedResource, selectionList: GeneratedResource[]): boolean {
        for (let target of selectionList)
            if (resource.isEqual(target)) return true
        return false
    }
}
