// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Input } from '@angular/core';
import { Router } from '@angular/router';
// - DOMAIN
import { NeoComFeature } from '@domain/ui/NeoComFeature.domain';

@Component({
    selector: 'v2-feature',
    templateUrl: './v2-feature-render.component.html',
    styleUrls: ['./v2-feature-render.component.scss']
})
export class V2FeatureRenderComponent {
    @Input() node: NeoComFeature;

    constructor(protected router: Router) { }

    public isMarkVisible(): boolean {
        if (this.node.interaction == 'DIALOG') return true
        if (this.node.modifier == 'DROP') return true
        return false
    }
    public getLabel(): string {
        return this.node.label
    }
    /**
     * If the Feature is of the type PAGEROUTE then we should send a message to the Dock to report the change on the Feature active.
     * If the feature is of type DIALOG then we can process the click here by opening the dialog requested.
     */
    public onClick() {
        if (this.node) {
            console.log('><[V2FeatureRenderComponent.onClick]> Label: ' + this.node.label)
            if (this.node.enabled) // Only interact with enabled Features
                switch (this.node.interaction) {
                    case 'PAGEROUTE':
                        console.log('><[V2FeatureRenderComponent,onClick]> PAGEROUTE')
                        this.activateFeature(this.node);
                        break;
                }
        }
    }
    private activateFeature(target: NeoComFeature): void {
        console.log('><[V2FeatureRenderComponent.activateFeature]> Feature: ' + JSON.stringify(target));
        if (target)
            this.pageChange(target.getRoute());
    }
    /**
     * Save the new dock configuration so if the applciation is restarted this is the new default start point.
     * @param route the new route path to be set as destination.
     */
    private pageChange(route: string): void {
        console.log('><[V2FeatureRenderComponent.pageChange]> Route: ' + route);
        this.router.navigate([route]);
    }
}
