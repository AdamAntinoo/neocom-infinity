// - CORE
import { Component } from '@angular/core';
// - DOMAIN
import { NodeContainerRenderComponent } from '@shared/renders/node-container-render/node-container-render.component';
import { Fitting } from '@domain/Fitting.domain';
import { UIIconReference } from '@domain/interfaces/IIconReference.interface';

@Component({
    selector: 'v1-fitting',
    templateUrl: './v1-fitting-render.component.html',
    styleUrls: ['./v1-fitting-render.component.scss']
})
export class V1FittingRenderComponent extends NodeContainerRenderComponent {
    public getUrl4Item(): string {
        if (null != this.node) {
            let fitting = this.node as Fitting;
            return fitting.getUrl4Item();
        } else return new UIIconReference('defaulticonplaceholder').getReference()
    }
    public getName(): string {
        if (null != this.node) {
            let fitting = this.node as Fitting;
            return fitting.getName();
        } else return '-';
    }
}
