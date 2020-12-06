import { Component, Input, OnInit } from '@angular/core';
import { NeoComFeature } from '@domain/ui/NeoComFeature.domain';

@Component({
  selector: 'v1-feature-button',
  templateUrl: './v1-feature-button.component.html',
  styleUrls: ['./v1-feature-button.component.scss']
})
export class V1FeatureButtonComponent  {

  @Input() feature : NeoComFeature
  public getFeatureImage () : string {
      return 'assets/res-planetary/planetary-planet-info-feature.jpeg'
  }
}
