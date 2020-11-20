import { Component, OnInit } from '@angular/core';

@Component({
	selector: 'npi-v1-selected-planetary-resources-panel',
	templateUrl: './v1-selected-planetary-resources-panel.component.html',
	styleUrls: ['./v1-selected-planetary-resources-panel.component.scss']
})
export class V1SelectedPlanetaryResourcesPanelComponent {
	public getSelectedResources(): string[] {
		return ["Selecte one", "Selected two"]
	}
}
