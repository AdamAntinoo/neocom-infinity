import { Component, OnInit } from '@angular/core';
//--- SERVICES
import { AppModelStoreService } from '../../services/app-model-store.service';
import { PilotListDataSourceService } from '../../services/pilot-list-data-source.service';
//--- INTERFACES
import { PageComponent } from '../../classes/PageComponent';
import { EVariant } from '../../classes/EVariant.enumerated';
//--- CLASSES
//import { PilotListDataSource } from '../../classes/PilotListDataSource';
import { DataSourceLocator } from '../../classes/DataSourceLocator';
//--- MODELS
import { Render } from '../../models/Render.model';
import { NeoComNode } from '../../models/NeoComNode.model';
import { Pilot } from '../../models/Pilot.model';
import { Region } from '../../models/Region.model';

@Component({
  selector: 'neocom-pilot-roaster-page',
  templateUrl: './pilot-roaster-page.component.html',
  styleUrls: ['./pilot-roaster-page.component.css']
})
export class PilotRoasterPageComponent extends PageComponent implements OnInit {
  //  public datasourceData: Pilot[] = [];
  public adapterViewList: Render[] = [];
  public downloading: boolean = true;

  constructor(private appModelStore: AppModelStoreService, private pilotListService: PilotListDataSourceService) {
    super();
    this.setVariant(EVariant.PILOTROASTER)
  }

  ngOnInit() {
    console.log(">>[PilotRoasterPageComponent.ngOnInit]");
    // Create the DataSource and initialize it with the parameters.
    let locator = new DataSourceLocator()
      .addIdentifier("PilotListDataSource")
      .addIdentifier(this.getVariantName());
    this.pilotListService.setLocator(locator);
    this.pilotListService.setVariant(this.getVariant());
    // Set the AppModel datasource to this datasource.
    this.appModelStore.setActiveDataSource(this.pilotListService);
    // Show the spinner
    this.downloading = true;
    //    this.appModelStore.registerDataSource(ds);
    this.pilotListService.collaborate2Model()
      .subscribe(result => {
        console.log("--[PilotRoasterPageComponent.ngOnInit.collaborate2Model]> pilot list: " + JSON.stringify(result));
        // The the list of planatary resource lists to the data returned.
        this.adapterViewList = this.pilotListService.collaborate2View();
        console.log("--[PilotRoasterPageComponent.ngOnInit.collaborate2View]> Renders: " + JSON.stringify(this.adapterViewList));
        this.downloading = false;
      });
    console.log("<<[PilotRoasterPageComponent.ngOnInit]");
  }
  // /**
  // Calls the same method of the associated DataSource to get the list of nodes that currently are collaborating
  // to the view presentation.
  // */
  // public collaborate2View() {
  //   let ds = this.appModelStore.accessDataSource();
  //   if (ds != null) {
  //     let viewList = ds.collaborate2View();
  //     return viewList;
  //   }
  // }
}
