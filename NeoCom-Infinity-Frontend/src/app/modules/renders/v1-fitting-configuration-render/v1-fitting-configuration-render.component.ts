// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Input } from '@angular/core';
import { FittingBuildConfiguration } from '@domain/industry/FittingBuildConfiguration.domain';

@Component({
    selector: 'v1-fitting-configuration',
    templateUrl: './v1-fitting-configuration-render.component.html',
    styleUrls: ['./v1-fitting-configuration-render.component.scss']
})
export class V1FittingConfigurationRenderComponent {
    @Input() node: FittingBuildConfiguration

    public getHullName(): string {
        return 'HULL'
    }
}
