import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'v1-industry-fitting-build-configuration-page',
  templateUrl: './v1-industry-fitting-build-configuration-page.component.html',
  styleUrls: ['./v1-industry-fitting-build-configuration-page.component.scss']
})
export class V1IndustryFittingBuildConfigurationPageComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  public getIdentityParameter(): number {
      return 60320161;
  }

}
