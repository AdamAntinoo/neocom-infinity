import { Component, Input, OnInit } from '@angular/core';
import { PlanetaryResource } from '@domain/planetary-resource';

@Component({
  selector: 'npi-v1-resource',
  templateUrl: './v1-resource-render.component.html',
  styleUrls: ['./v1-resource-render.component.scss']
})
export class V1ResourceRenderComponent implements OnInit {
  @Input() resource: PlanetaryResource | undefined
  constructor() { }

  ngOnInit(): void {
  }

}
