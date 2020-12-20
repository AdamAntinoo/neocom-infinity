// - CORE
import { Component } from '@angular/core';
// - DOMAIN
import { V2NodeContainerRenderComponent } from '@app/modules/renders/v2-node-container-render/v2-node-container-render.component';
import { V2FeatureRenderComponent } from '../v2-feature-render/v2-feature-render.component';


@Component({
  selector: 'v1-blueprint',
  templateUrl: './v1-blueprint-render.component.html',
  styleUrls: ['./v1-blueprint-render.component.scss']
})
export class V1BlueprintRenderComponent   extends V2NodeContainerRenderComponent {

}
