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
/**
 * This is the landing page for a login. The page has teh next structure:
 * ROW 1 - The application header and the current server status.
 * ROW 2 - The current Pilot, along with the Corporation, the corproation CEO if applies and and corporation Alliance if exists
 * ROW 3 - The list of available functionalities for this pilot.
 *
 * The component needs the current logged pilot identifier. This is obtained from the authentication credential on the session store. From this identifer we go to to backend to get the Pilot's public data. From it we can get the pilot's corporation and other data on the next fashion:
 * Current Pilot --> Corporation --> Corporation CEO
 *                               --> Alliance is exists
 * Because of HAL data contents instead using specific code to download the dependand data the new component can use the HATEOAS specification to access the other detencencies through the parent instance, on this case we can get the pilot's corporation from the pilot instance with the method 'getCorporation()'
 */
@Component({
    selector: 'dashboard-home-page',
    templateUrl: './dashboard-home-page.component.html',
    styleUrls: ['./dashboard-home-page.component.scss']
})
export class DashboardHomePageComponent {
    public features: NeoComFeature[] = []
    public exception : NeoComException

    constructor(
        protected isolationService: IsolationService,
        protected appStore:AppStoreService) {
         // Build the page features.
        this.features.push(new NeoComFeature({
            id: "planetary-dashboard",
            label: "Interacciones Planetarias",
            enabled: true,
            interaction: 'PAGEROUTE',
            route: "/planetary/dashboard",
            imageRef: 'assets/media/planetary-feature.jpeg'
        }))
        this.features.push(new NeoComFeature({
            id: "blueprint-analysis",
            label: "Gestion de Industria",
            enabled: true,
            interaction: 'PAGEROUTE',
            route: "/industry/dashboard",
            imageRef: 'assets/media/blueprints-feature.jpeg'
        }))
        this.features.push(new NeoComFeature({
            id: "loyalty-recommendations",
            label: "Recomendaciones de Ofertas Loyalty",
            enabled: true,
            interaction: 'PAGEROUTE',
            route: "/loyalty",
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
