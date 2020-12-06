import { Component, OnInit } from '@angular/core';
import { NeoComFeature } from '@domain/ui/NeoComFeature.domain';
import { Feature } from 'cucumber';

@Component({
  selector: 'dashboard-page',
  templateUrl: './dashboard-page.component.html',
  styleUrls: ['./dashboard-page.component.scss']
})
export class DashboardPageComponent {
    public planetDefinitionFeature : NeoComFeature
    public planetSearchFeature : NeoComFeature
}
