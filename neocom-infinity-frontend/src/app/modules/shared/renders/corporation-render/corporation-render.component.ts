// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Input } from '@angular/core';
import { environment } from '@env/environment';
// - SERVICES
// - DOMAIN
import { Corporation } from '@app/domain/Corporation.domain';
import { AllianceV1 } from '@domain/corporation/AllianceV1.domain';
import { RenderComponent } from '../render/render.component';

@Component({
    selector: 'corporation-render',
    templateUrl: './corporation-render.component.html',
    styleUrls: ['./corporation-render.component.scss']
})
export class CorporationRenderComponent extends RenderComponent {
    @Input() node: Corporation;

    public getCorporationIcon(): string {
        return this.node.getIconUrl()
    }
}
