import { Component } from "@angular/core";
import { AppStoreService } from "@app/services/appstore.service";
import { IsolationService } from "@innovative/services/isolation.service";

@Component({
    selector: 'v1-mining-operations-page',
    templateUrl: './v1-mining-operations-page.html',
    styleUrls: ['./v1-mining-operations-page.scss']
})
export class V1MiningOperationsPageComponent {
    public self: V1MiningOperationsPageComponent

    constructor(
        protected isolationService: IsolationService,
        protected appStore: AppStoreService) {
        this.self = this
    }
}
