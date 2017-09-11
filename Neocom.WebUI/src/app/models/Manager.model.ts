// PROJECT:     NEOCOM.WEB (NEOC.W)
// AUTHORS:     Adam Antinoo - adamantinoo.git@gmail.com
// COPYRIGHT:   (c) 2017 by Dimensinfin Industries, all rights reserved.
// ENVIRONMENT: Angular - CLASS
// DESCRIPTION: Defines the structure of a EVE Pilot. May depend on other classes to complete the character information hierarchy.

//--- INTERFACES
import { EVariant } from '../classes/EVariant.enumerated';
//--- MODELS
import { NeoComNode } from './NeoComNode.model';
import { PilotAction } from './pilotaction';
import { Region } from './Region.model';

export class Manager extends NeoComNode {
  public jsonClassname: string = "Manager";
  public regions: Region[] = [];
  public locations: Location[] = [];

  constructor(values: Object = {}) {
    super();
    Object.assign(this, values);
    this.class = "Manager";
  }
  public collaborate2View(variant: EVariant): NeoComNode[] {
    let collab = [];
    // Add myself to the list and then if expanded add all my data depending on the Variant.
    collab.push(this);

    if (this.expanded) {
      if (variant == EVariant.PILOTMANAGERS) {
        collab.push(this);
      }
    }
    return collab;
  }
}
