// - CORE
import { Component } from '@angular/core';
import { NodeContainerRenderComponent } from '../node-container-render/node-container-render.component';

@Component({
    selector: 'v1-fitting',
    templateUrl: './v1-fitting-render.component.html',
    styleUrls: ['./v1-fitting-render.component.scss']
})
export class V1FittingRenderComponent extends NodeContainerRenderComponent {

    constructor() { super(); }

    ngOnInit() {
    }

}
