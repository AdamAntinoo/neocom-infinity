package org.dimensinfin.eveonline.neocom.infinity.acceptance.steps;

import javax.validation.constraints.NotNull;

import org.dimensinfin.eveonline.neocom.infinity.support.NeoComWorld;

public abstract class StepSupport {
	protected NeoComWorld neocomWorld;

	// - C O N S T R U C T O R S
	public StepSupport( final @NotNull NeoComWorld neoComWorld ) {
		this.neocomWorld = neoComWorld;
	}
	public NeoComWorld getWorld(){
		return this.neocomWorld;
	}
}
