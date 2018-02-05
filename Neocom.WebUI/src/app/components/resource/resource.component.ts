import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import 'rxjs/add/operator/switchMap';
import { Input } from '@angular/core';

//--- SERVICES
import { AppModelStoreService } from '../../services/app-model-store.service';
//--- COMPONENTS
import { PageComponent } from '../../classes/PageComponent';
import { PlanetaryManagerPageComponent } from '../../pages/planetary-manager-page/planetary-manager-page.component';
//--- MODELS
import { Resource } from '../../models/Resource.model';

@Component({
  selector: 'neocom-resource',
  templateUrl: './resource.component.html',
  styleUrls: ['./resource.component.css']
})
export class ResourceComponent implements OnInit {
  //  @Input() viewer: PlanetaryManagerPageComponent;
  @Input() node: Resource;

  public quantity: number = 0;
  public item = null;

  constructor(private appModelStore: AppModelStoreService) { }

  ngOnInit() {
  }
  public getMarketValue(): number {
    return this.node.quantity * this.node.item.baseprice;
  }
}
