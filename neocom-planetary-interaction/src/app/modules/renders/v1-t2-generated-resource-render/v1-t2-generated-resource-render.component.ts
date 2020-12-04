import { Component, Input, OnInit } from '@angular/core';
import { GeneratedResource } from '@domain/generated-resource';

@Component({
    selector: 'npi-v1-t2-generated-resource',
    templateUrl: './v1-t2-generated-resource-render.component.html',
    styleUrls: ['./v1-t2-generated-resource-render.component.scss']
})
export class V1T2GeneratedResourceRenderComponent {
    @Input() resource: GeneratedResource | undefined
    @Input() variant: string = '-DEFAULT-'

    public getPlanetName(): string {
        if (this.resource) return this.resource.getPlanetName()
        else return '-'
    }
    public isSelected(): boolean {
        if (this.resource) return this.resource.selected
        return false
    }
}
