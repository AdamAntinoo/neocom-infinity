// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Input } from '@angular/core';
// - SERVICES
// - DOMAIN
import { Pilot } from '@app/domain/Pilot.domain';
import { RenderComponent } from '../render/render.component';

@Component({
    selector: 'pilot-render',
    templateUrl: './pilot-render.component.html',
    styleUrls: ['./pilot-render.component.scss']
})
export class PilotRenderComponent extends RenderComponent {
    @Input() node: Pilot;
}
