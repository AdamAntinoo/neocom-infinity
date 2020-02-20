// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Input } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
// - SERVICES
import { AppStoreService } from '@app/services/appstore.service';
// - DOMAIN
import { TabDefinition } from '@app/domain/TabDefinition.domain';


@Component({
    selector: 'tab-container-panel',
    templateUrl: './tab-container-panel.component.html',
    styleUrls: ['./tab-container-panel.component.scss']
})
export class TabContainerPanelComponent implements OnInit {
    @Input() selectedTabName: string = '-NONE-'; // The name of the tab to be selected.
    @Input() tabDefinitionFile: string; // The name of the propeties fiel that define the tab list
    public tabs: TabDefinition[] = [];

    constructor(protected appStoreService: AppStoreService) { }

    public ngOnInit() {
        // Read the tab definitions.
        this.propertiesTabDefinitions()
            .subscribe(tabs => {
                this.tabs = tabs;
            });
    }
    private propertiesTabDefinitions(): Observable<TabDefinition[]> {
        console.log("><[TabContainerPanelComponent.propertiesTabDefinitions]");
        // Construct the request to call the backend.
        if (null != this.tabDefinitionFile)
            return this.appStoreService.accessProperties(this.tabDefinitionFile)
                .pipe(map(data => {
                    console.log("><[TabContainerPanelComponent.propertiesTabDefinitions]> Converting data to TabDefinition's");
                    let results: TabDefinition[] = [];
                    if (data instanceof Array) {
                        for (let key in data) {
                            const tab = new TabDefinition(data[key]);
                            if (tab.getName() === this.selectedTabName) tab.select();
                            results.push(tab);
                        }
                    } else {
                        console.log("-[TabContainerPanelComponent.propertiesTabDefinitions]> Detected single tab.");
                    const tab = new TabDefinition(data);
                        if (tab.getName() === this.selectedTabName) tab.select();
                        results.push(tab);
                    }
                    return results;
                }));
        else return Observable.create((observer) => {
            observer.next([]);
            observer.complete();;
        });
    }
}
