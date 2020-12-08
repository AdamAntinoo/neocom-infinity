// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Input } from '@angular/core';
// - ROUTER
import { Router } from '@angular/router';
// - DOMAIN
import { NeoComFeature } from '@domain/ui/NeoComFeature.domain';
import { ResponseTransformer } from '@innovative/services/support/ResponseTransformer';
import { PlanetaryDataService } from '../../service/PlanetaryData.service';

@Component({
    selector: 'dashboard-page',
    templateUrl: './dashboard-page.component.html',
    styleUrls: ['./dashboard-page.component.scss']
})
export class DashboardPageComponent implements OnInit {
    private planetaryFeatures: NeoComFeature[] = [];

    constructor(private planetaryService: PlanetaryDataService) { }

    public ngOnInit(): void {
        console.log('>[DashboardPageComponent.ngOnInit]');
        this.planetaryService.readPlanetaryFeatures(
            new ResponseTransformer().setDescription('Do configuration transformation to a "NeoComFeature" list.')
                .setTransformation((entrydata: any): NeoComFeature[] => {
                    console.log('<[DashboardPageComponent.ngOnInit.setTransformation]');
                    let results: NeoComFeature[] = [];
                    if (entrydata instanceof Array) {
                        for (let key in entrydata)
                            results.push(new NeoComFeature(entrydata[key]));
                    }
                    return results;
                }))
            .subscribe((featureList: NeoComFeature[]) => {
                console.log('<[DashboardPageComponent.ngOnInit.subscribe]');
                this.planetaryFeatures = featureList
                console.log('->[DashboardPageComponent.ngOnInit]> Feature count: ' + this.planetaryFeatures.length)
                this.planetaryService.clean()
            });
        console.log('<[DashboardPageComponent.ngOnInit]');
    }
    // - I N T E R A C T I O N
    public getActiveFeatures(): NeoComFeature[] {
        if (this.planetaryFeatures) return this.planetaryFeatures;
        else return [];
    }
    public processEvent () : void{}
}
