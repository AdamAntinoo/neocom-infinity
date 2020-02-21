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
//  private variation: ESeparator=ESeparator.ORANGE;
//   @Input() viewer: IViewer;
//   @Input() node: any;

  public hasMenu(): boolean {
    return false;
  }
  public isExpandable(): boolean {
    return false;
  }
  public getVariation(): ESeparator {
      const separatorInstance = this.node as Separator;
      return separatorInstance.getVariation();
  }
  public isRed(): boolean {
    if (this.getVariation() == ESeparator.RED) return true;
    else return false;
  }
  public isOrange(): boolean {
    if (this.getVariation() == ESeparator.ORANGE) return true;
    else return false;
  }
  public isYellow(): boolean {
    if (this.getVariation() == ESeparator.YELLOW) return true;
    else return false;
  }
  public isGreen(): boolean {
    if (this.getVariation() == ESeparator.GREEN) return true;
    else return false;
  }
  public isBlue(): boolean {
    if (this.getVariation() == ESeparator.BLUE) return true;
    else return false;
  }
  public isWhite(): boolean {
    if (this.getVariation() == ESeparator.WHITE) return true;
    else return false;
  }
  public isEmpty(): boolean {
    if (this.getVariation() == ESeparator.EMPTY) return true;
    else return false;
  }
  public isSpinner(): boolean {
    if (this.getVariation() == ESeparator.SPINNER) return true;
    else return false;
  }
}
