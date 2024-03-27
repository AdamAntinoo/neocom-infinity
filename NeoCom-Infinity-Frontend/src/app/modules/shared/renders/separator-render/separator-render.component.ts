// - CORE
import { Component } from '@angular/core';
import { Input } from '@angular/core';
import { ESeparator } from '@domain/interfaces/EPack.enumerated';
import { NodeContainerRenderComponent } from '../node-container-render/node-container-render.component';
import { Separator } from '@domain/Separator.model';

/**
The separator will render some kind of artefact to signal boundaries between node elements. There are some variations in color and in shape that are controlled by an input parameter that contains the Enumerated value of the variation to use.
*/

@Component({
  selector: 'separator-render',
  templateUrl: './separator-render.component.html',
  styleUrls: ['./separator-render.component.scss']
})
export class SeparatorComponent extends NodeContainerRenderComponent {
    public getSeparatorColor(): string{
        switch (this.getNode().getThemeColor()) {
            case ESeparator.RED:
                return 'solid-red';
            case ESeparator.WHITE:
                return 'solid-white';
            case ESeparator.ORANGE:
                return 'solid-orange';
            case ESeparator.YELLOW:
                return 'solid-yellow';
            case ESeparator.GREEN:
                return 'solid-green';
            case ESeparator.EMPTY:
                return 'empty-marker';
            case ESeparator.SPINNER:
                return 'empty-marker';
        }
    }
}
