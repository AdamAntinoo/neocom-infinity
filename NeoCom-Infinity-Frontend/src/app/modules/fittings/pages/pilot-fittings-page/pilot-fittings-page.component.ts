// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
// - SERVICES
import { AppPanelComponent } from '@shared/core/app-panel/app-panel.component';
import { AppStoreService } from '@app/services/appstore.service';
import { ResponseTransformer } from '@innovative/services/support/ResponseTransformer';
// - DOMAIN
import { Fitting } from '../../../../domain/Fitting.domain';
import { GroupContainer } from '@domain/GroupContainer.domain';
import { URLGroupIconReference, AssetGroupIconReference } from '@domain/interfaces/IIconReference.interface';
import { NCVariant } from '@env/NeoComVariants';

@Component({
    selector: 'pilot-fittings-page',
    templateUrl: './pilot-fittings-page.component.html',
    styleUrls: ['./pilot-fittings-page.component.scss']
})
export class PilotFittingsPageComponent extends AppPanelComponent implements OnInit {
    private hullCategories: Map<string, GroupContainer> = new Map<string, GroupContainer>();
    private shipClasses: Map<string, GroupContainer> = new Map<string, GroupContainer>();

    constructor(protected appStoreService: AppStoreService) { super(); }

    public ngOnInit() {
        console.log(">[PilotFittingsPageComponent.ngOnInit]");
        this.setVariant(NCVariant.FITTING_LIST);
        // Download the list of fittings for this Pilot.
        console.log('-[PilotFittingsPageComponent.ngOnInit]> Starting to download pilot fittings');
        this.appStoreService.accessPilotFittings(new ResponseTransformer()
            .setDescription('Do response transformation to "Fitting List".')
            .setTransformation((entrydata: any): Fitting[] => {
                let results: Fitting[] = [];
                if (entrydata instanceof Array) {
                    for (let key in entrydata)
                        results.push(new Fitting(entrydata[key]));
                } else
                    results.push(new Fitting(entrydata));

                return results;
            }))
            .subscribe((response: Fitting[]) => {
                // console.log('-[PilotFittingsPageComponent.ngOnInit]> response: ' + JSON.stringify(response));
                // Process the list of fittings into the ship type groupings.
                this.classifyFittings(response);
                console.log('-[PilotFittingsPageComponent.ngOnInit]> nodes processed: ' + this.dataModelRoot.length);
                this.completeDowload();    // Notify the completion of the download.
            });
        console.log("<[PilotFittingsPageComponent.ngOnInit]");
    }
    /**
     * The processing gets each fitting in turna and then search for the ship type on the already stored list of ships. If found the just adds the new fitting to this ship type. If not then searchs on the list of hull categories. If the hull category is found then the new ship class gets the fitting and then is added to the hull category. If the hull category is not found then it is created and the fitting is first added to the ship that then is added to to hull.
     * The hierarchy then is Hull Categories / Ship class / Fitting.
     * Hull categories are stored on the 'hullCategories' global field while the shipClasses are stored on the 'shipClasses' field.
     * @param fittings the list of fittings to process
     */
    private classifyFittings(fittings: Fitting[]): void {
        console.log('>[PilotFittingsPage.classifyFittings]');
        let hitShip = new GroupContainer()
        // Process the fittings and classify them into Ship categories, then ship type end then fitting.
        console.log('-[PilotFittingsPage.classifyFittings]> fitting count: ' + fittings.length);
        for (let fit of fittings) {
            // Search the ship class
            const shipClassId = fit.getShipClassName();
            let hitShipClass = this.shipClasses.get(shipClassId);
            if (null == hitShipClass) {
                // Create a new ship class entry.
                console.log("-[PilotFittingsPage.accessPilotFittings]> Creating Ship Class: " + fit.getShipGroup());
                hitShipClass = new GroupContainer()
                    .setId(fit.getShipTypeId())
                    .setTitle(fit.getShipClassName())
                    .setGroupIcon(new URLGroupIconReference(fit.getShipTypeId()));
                this.shipClasses.set(shipClassId, hitShipClass);

                // Search for this ship hull category on the list of ships.
                const hullCategory = fit.getShipGroup();
                console.log('-[PilotFittingsPage.classifyFittings]> hullCategory: ' + hullCategory);
                let hitHullCategory = this.hullCategories.get(hullCategory);
                if (null == hitHullCategory) {
                    // Create a new hull category and add the current ship class to it.
                    console.log("-[PilotFittingsPage.accessPilotFittings]> Creating Hull Category: " + hullCategory);
                    hitHullCategory = new GroupContainer()
                        .setId(fit.getShipGroupId())
                        .setTitle(hullCategory)
                        .setGroupIcon(new AssetGroupIconReference(fit.getHullCategory() + "_64"));
                    this.hullCategories.set(hullCategory, hitHullCategory);
                    this.dataModelRoot.push(hitHullCategory);
                }
                hitHullCategory.addContent(hitShipClass);
            }
            hitShipClass.addContent(fit);

            // let groupId = fit.getShipGroupId();
            // Search for this group on the current list or create a new group.
            // let group = fit.getShipGroup();
            // Add the new group to the dta content root.
            // Add the Ship group to the category group.
            // }
            // }
        }
        console.log('<[PilotFittingsPage.classifyFittings]');
    }
}
