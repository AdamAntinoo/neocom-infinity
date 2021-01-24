// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Input } from '@angular/core';
// - ROUTER
import { Router } from '@angular/router';
// - DOMAIN
import { NeoComFeature } from '@domain/ui/NeoComFeature.domain';
import { ResponseTransformer } from '@innovative/services/support/ResponseTransformer';
import { Feature } from 'cucumber';
import { PlanetaryDataService } from '../../service/PlanetaryData.service';

@Component({
    selector: 'dashboard-page',
    templateUrl: './dashboard-page.component.html',
    styleUrls: ['./dashboard-page.component.scss']
})
export class DashboardPageComponent  {
    public planetaryNewData:NeoComFeature
    public planetaryDataAnalysis:NeoComFeature

    constructor(private planetaryService: PlanetaryDataService) {
        // Build the page features.
        this.planetaryNewData = new NeoComFeature({
            id: "planetary-enter-data",
            label: "Nuevos Datos de Planetas",
            enabled: true,
            interaction: 'PAGEROUTE',
            route: "/planetary/planet-data",
            imageRef: 'assets/media/planetary-enter-data.jpeg'
        })
        this.planetaryDataAnalysis = new NeoComFeature({
            id: "planetary-analyze-data",
            label: "Analisis de Objetivos Planetarios",
            enabled: true,
            interaction: 'PAGEROUTE',
            route: "/planetary/planetary-research",
            imageRef: 'assets/media/planetary-analyze-data.jpeg'
        })
     }
}
