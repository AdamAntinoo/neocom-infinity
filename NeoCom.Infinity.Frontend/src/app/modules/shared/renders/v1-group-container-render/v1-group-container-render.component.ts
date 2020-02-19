import { Component, OnInit } from '@angular/core';
import { NodeContainerRenderComponent } from '@renders/node-container-render/node-container-render.component';

@Component({
    selector: 'v1-group-container',
    templateUrl: './v1-group-container-render.component.html',
    styleUrls: ['./v1-group-container-render.component.scss']
})
export class V1GroupContainerRenderComponent extends NodeContainerRenderComponent {

    constructor() { super(); }

    ngOnInit() {
    }

}
