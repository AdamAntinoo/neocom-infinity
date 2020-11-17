import { Component, OnInit } from '@angular/core';
import { BackgroundEnabledComponent } from '@bit/innovative.innovative.innovative-core/dist/innovative-core/components/background-enabled/background-enabled.component';
import { DataService } from 'src/app/services/data-service.service';

@Component({
  selector: 'npi-v1-t2-classification-panel',
  templateUrl: './v1-t2-classification-panel.component.html',
  styleUrls: ['./v1-t2-classification-panel.component.scss']
})
export class V1T2ClassificationPanelComponent extends BackgroundEnabledComponent implements OnInit {

  constructor(protected dataService : DataService) {super() }

 public  ngOnInit(): void {
   this.refresh()
  }
private clear () : void {
}
private refresh() : void {
this.backendConnections.push ( this.dataService.apiGetPlanetPIInformation()
.subscribe ( dataList => {
for (let index = 0; index < dataList.length; index++) {
  const element = dataList[index];
const planetData : PlanetaryDataRecord = new  PlanetaryDataRecord ( element)
}
})
}
}
