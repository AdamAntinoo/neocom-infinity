import { Component, OnInit } from '@angular/core';
import { PlanetaryDataRecord } from 'src/app/domain/planetary-data-record';
import { DataService } from 'src/app/services/data-service.service';
import { BackgroundEnabledComponent } from '@bit/innovative.innovative.innovative-core'

@Component({
  selector: 'npi-v1-t2-classification-panel',
  templateUrl: './v1-t2-classification-panel.component.html',
  styleUrls: ['./v1-t2-classification-panel.component.scss'],
})
/**
 * This panels shows the list of planets and for each one the list of possible T2 resources that can be generated along with the index calculation
 * 
 */
export class V1T2ClassificationPanelComponent extends BackgroundEnabledComponent implements OnInit {
  public planetList: PlanetaryDataRecord[] =[]
  constructor(protected dataService: DataService) {
    super();
  }

  public ngOnInit(): void {
    this.refresh();
  }
  private clear(): void { }
  private refresh(): void {
    this.backendConnections.push(
      this.dataService.apiGetPlanetPIInformation().subscribe((dataList) => {
        for (let index = 0; index < dataList.length; index++) {
          const element = dataList[index];
          const planetData: PlanetaryDataRecord = new PlanetaryDataRecord(element)
          this.planetList.push(planetData)
        }
      })
    )
  }
}
