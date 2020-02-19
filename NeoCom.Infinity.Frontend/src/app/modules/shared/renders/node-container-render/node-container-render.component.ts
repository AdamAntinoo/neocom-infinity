// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Input } from '@angular/core';
// - DOMAIN
import { ICollaboration } from '@app/domain/interfaces/ICollaboration.interface';
import { EVariant } from '@app/domain/interfaces/EPack.enumerated';
import { IViewer } from '@app/domain/interfaces/IViewer.interface';

@Component({
    selector: 'node-container-render',
    templateUrl: './node-container-render.component.html',
    styleUrls: ['./node-container-render.component.scss']
})
export class NodeContainerRenderComponent implements OnInit {
    @Input() container: IViewer;
    @Input() node: ICollaboration;
    @Input() variant: EVariant = EVariant.DEFAULT;
    // constructor() { }

    ngOnInit() {
    }
    public mouseEnter(target: ICollaboration) {
        this.container.enterSelected(target);
    }
}
