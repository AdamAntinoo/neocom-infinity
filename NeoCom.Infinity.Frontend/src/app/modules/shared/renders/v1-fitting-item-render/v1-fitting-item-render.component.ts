import { Component, OnInit } from '@angular/core';
import { NodeContainerRenderComponent } from '../node-container-render/node-container-render.component';

@Component({
    selector: 'v1-fitting-item',
    templateUrl: './v1-fitting-item-render.component.html',
    styleUrls: ['./v1-fitting-item-render.component.scss']
})
export class V1FittingItemRenderComponent extends NodeContainerRenderComponent {

    constructor() { super(); }

    ngOnInit() {
    }

}
