// - CORE
import { Component } from '@angular/core';
// - DOMAIN
import { V2NodeContainerRenderComponent } from '@app/modules/renders/v2-node-container-render/v2-node-container-render.component';

@Component({
  selector: 'v1-industry-node-container-render',
  templateUrl: './v1-industry-node-container-render.component.html',
  styleUrls: ['./v1-industry-node-container-render.component.scss']
})
export class V1IndustryNodeContainerRenderComponent extends V2NodeContainerRenderComponent {
}
