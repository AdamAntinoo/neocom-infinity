// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
// - SERVICES
import { AppPanelComponent } from '@app/modules/shared/panels/app-panel/app-panel.component';
import { AppStoreService } from '@app/services/appstore.service';
import { ResponseTransformer } from '@app/services/support/ResponseTransformer';
// - DOMAIN
import { Fitting } from '../../../../domain/Fitting.domain';
import { GroupContainer } from '@domain/GroupContainer.domain';
import { EVariant } from '@app/domain/interfaces/EPack.enumerated';
import { URLGroupIconReference, AssetGroupIconReference } from '@domain/interfaces/IIconReference.interface';

@Component({
    selector: 'pilot-fittings-page',
    templateUrl: './pilot-fittings-page.component.html',
    styleUrls: ['./pilot-fittings-page.component.scss']
})
export class PilotFittingsPageComponent extends AppPanelComponent implements OnInit {
    private groupList: Map<number, GroupContainer> = new Map<number, GroupContainer>();
    private shipList: Map<number, GroupContainer> = new Map<number, GroupContainer>();

    constructor(protected appStoreService: AppStoreService) { super(); }

    ngOnInit() {
        console.log(">[PilotFittingsPageComponent.ngOnInit]");
        this.setVariant(EVariant.FITTING_LIST);
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

    private classifyFittings(fittings: Fitting[]): void {
        console.log('>[PilotFittingsPage.classifyFittings]');
        let hitShip = new GroupContainer()
        // Process the fittings and classify them into Ship categories, then ship type end then fitting.
        console.log('-[PilotFittingsPage.classifyFittings]> fitting count: ' + fittings.length);
        for (let fit of fittings) {
            // Search for this ship type on the list of ships.
            console.log('-[PilotFittingsPage.classifyFittings]> fitting name: ' + JSON.stringify(fit));
            try {
                console.log('-[PilotFittingsPage.classifyFittings]> ship type: ' + fit.getShipTypeId());
            } catch (error) {
                console.log('-[PilotFittingsPage.classifyFittings]> error: ' + JSON.stringify(error));
           }
            let hitShip = this.shipList.get(fit.getShipTypeId());
            if (null == hitShip) {
                // Create a new ship class entry and also check the group.
                console.log("-- [PilotFittingsPage.accessPilotFittings]> Creating Ship Group: " + fit.getShipGroup());
                hitShip = new GroupContainer()
                    .setId(fit.getShipTypeId())
                    .setTitle(fit.getShipGroup())
                    .setGroupIcon(new URLGroupIconReference(fit.getShipTypeId()));
                this.shipList.set(fit.getShipTypeId(), hitShip);
                let groupId = fit.getShipGroupId();
                // Search for this group on the current list or create a new group.
                let hitGroup = this.groupList.get(groupId);
                if (null == hitGroup) {
                    // Create a new group and add the current ship class to it.
                    let group = fit.getShipGroup();
                    console.log("-- [PilotFittingsPage.accessPilotFittings]> Creating Group: " + group);
                    hitGroup = new GroupContainer()
                        .setId(groupId)
                        .setTitle(group)
                        .setGroupIcon(new AssetGroupIconReference(fit.getHullCategory() + "_64"));
                    this.groupList.set(groupId, hitGroup);
                    // Add the new group to the dta content root.
                    this.dataModelRoot.push(hitGroup);
                    // Add the Ship group to the category group.
                    hitGroup.addContent(hitShip);
                }
            }
        }
        console.log('<[PilotFittingsPage.classifyFittings]');
    }
}
