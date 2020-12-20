// - CORE
import { Component } from '@angular/core';
// - DOMAIN
import { V2NodeContainerRenderComponent } from '@app/modules/renders/v2-node-container-render/v2-node-container-render.component';
import { V1IndustryNodeContainerRenderComponent } from '../v1-industry-node-container-render/v1-industry-node-container-render.component';

@Component({
  selector: 'v1-bomresource',
  templateUrl: './v1-bomresource-render.component.html',
  styleUrls: ['./v1-bomresource-render.component.scss']
})
export class V1BOMResourceRenderComponent extends V1IndustryNodeContainerRenderComponent {
}
