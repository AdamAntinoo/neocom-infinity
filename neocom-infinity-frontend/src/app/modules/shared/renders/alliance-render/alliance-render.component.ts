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
import { PlatformConstants } from '@env/PlatformConstants';
import { NeoComConstants } from '@app/platform/NeocomConstants.platform';

@Component({
    selector: 'alliance-render',
    templateUrl: './alliance-render.component.html',
    styleUrls: ['./alliance-render.component.scss']
})
export class AllianceRenderComponent extends RenderComponent {
    @Input() node: AllianceV1;

    public getName(): string {
        if (null != this.node) return this.node.name;
        else return '-';
    }
    public getAllianceIcon(): string {
        if (null != this.node) return this.node.getAllianceIcon();
        else return NeoComConstants.DEFAULT_AVATAR_PLACEHOLDER;
    }
}
