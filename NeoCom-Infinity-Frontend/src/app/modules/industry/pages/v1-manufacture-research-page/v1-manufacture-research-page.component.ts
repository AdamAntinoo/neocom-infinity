import { Component, OnInit } from '@angular/core';
import { ICollaboration } from '@innovative/domain/interfaces/ICollaboration.interface';
// import a from '../../../../../styles/'
@Component({
  selector: 'v1-manufacture-research-page',
  templateUrl: './v1-manufacture-research-page.component.html',
  styleUrls: ['./v1-manufacture-research-page.component.scss']
})
export class V1ManufactureResearchPageComponent implements OnInit {
    public   hoveringTarget : ICollaboration
public node : any
  constructor() { }

  ngOnInit(): void {
  }

}
