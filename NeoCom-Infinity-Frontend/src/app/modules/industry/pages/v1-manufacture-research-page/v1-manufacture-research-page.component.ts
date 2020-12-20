import { Component, OnInit } from '@angular/core';
import { ICollaboration } from '@innovative/domain/interfaces/ICollaboration.interface';
@Component({
    selector: 'v1-manufacture-research-page',
    templateUrl: './v1-manufacture-research-page.component.html',
    styleUrls: ['./v1-manufacture-research-page.component.scss']
})
export class V1ManufactureResearchPageComponent  {
    public self: V1ManufactureResearchPageComponent
    public hoveringTarget: ICollaboration

    constructor() {
        this.self = this
    }
    // - A P I

}
