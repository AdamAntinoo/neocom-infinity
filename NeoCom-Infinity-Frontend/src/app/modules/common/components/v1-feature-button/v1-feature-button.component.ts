import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EInteraction } from '@domain/ui/EInteraction.enum';
import { NeoComFeature } from '@domain/ui/NeoComFeature.domain';
import { platformConstants } from '@env/platform-constants';

@Component({
    selector: 'v1-feature-button',
    templateUrl: './v1-feature-button.component.html',
    styleUrls: ['./v1-feature-button.component.scss']
})
export class V1FeatureButtonComponent {
    @Input() feature: NeoComFeature // NeoCom feature class instance that defines the feature action

    constructor(protected router: Router) { }

    public getFeatureImage(): string {
        if (this.feature) return this.feature.getImageRef()
        else return platformConstants.DEFAULT_FEATURE_REF
    }
    public getFeatureLabel(): string {
        if (this.feature) return this.feature.getLabel()
        else return platformConstants.DEFAULT_FEATURE_LABEL
    }
    // - I N T E R A C T I O N S
    public onClick(): void {
        if (this.feature) {
            if (this.feature.interaction == EInteraction.PAGEROUTE)
                this.pageChange(this.feature.route)
        }
    }
    private pageChange(route: string): void {
        console.log('><[V1FeatureButtonComponent.pageChange]> Route: ' + route);
        this.router.navigate([route]);
    }
}
