// - CORE
import { Component } from '@angular/core'
import { OnInit } from '@angular/core'
// - SERVICES
import { environment } from '@env/environment';
import { IsolationService } from '@innovative/services/isolation.service';
import { BackendService } from '@app/services/backend.service'
// - INNOVATIVE
import { BackgroundEnabledComponent } from '@innovative/components/background-enabled/background-enabled.component';
// - DOMAIN
import { NeoComCredential } from '@domain/NeoComCredential.domain';
import { NeoComFeature } from '@domain/ui/NeoComFeature.domain';
import { NeoComException } from '@innovative/domain/NeoComException';
import { PlatformConstants } from '@env/PlatformConstants';
import { AppStoreService } from '@app/services/appstore.service';

@Component({
    selector: 'industry-dashboard-page',
    templateUrl: './industry-dashboard-page.component.html',
    styleUrls: ['./industry-dashboard-page.component.scss']
})
export class IndustryDashboardPageComponent {
    public features: NeoComFeature[] = []

    constructor(
        protected isolationService: IsolationService,
        protected appStore: AppStoreService) {
        // Build the page features.
        this.features.push(new NeoComFeature({
            id: "blueprint-analysis",
            label: "Gestion de Blueprints",
            enabled: true,
            interaction: 'PAGEROUTE',
            route: "/industry/manufacture/blueprints",
            imageRef: 'assets/media/blueprints-feature.jpeg'
        }))
    }

    // - I N T E R A C T I O N S
    public getPilotId(): number {
        try {
            return this.appStore.getPilotId()
        } catch (exception) {
            this.isolationService.processException(exception)
            return undefined
        }
    }
}
