// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Input } from '@angular/core';
import { BuildActionDto } from '@domain/industry/dto/BuildActionDto.dto';
import { FittingBuildConfigurationDto } from '@domain/industry/dto/FittingBuildConfigurationDto.dto';
import { FittingGroup } from '@domain/industry/FittingGroup.domain';
import { NodeContainerRenderComponent } from '@shared/renders/node-container-render/node-container-render.component';
import { V2NodeContainerRenderComponent } from '../v2-node-container-render/v2-node-container-render.component';

@Component({
  selector: 'v1-build-action',
  templateUrl: './v1-build-action-render.component.html',
  styleUrls: ['./v1-build-action-render.component.scss']
})
export class V1BuildActionRenderComponent extends V2NodeContainerRenderComponent{
}
