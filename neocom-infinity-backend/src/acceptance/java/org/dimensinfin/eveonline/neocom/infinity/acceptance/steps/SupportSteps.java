package org.dimensinfin.eveonline.neocom.infinity.acceptance.steps;

import org.apache.commons.lang3.NotImplementedException;

import org.dimensinfin.eveonline.neocom.infinity.support.ConverterContainer;
import org.dimensinfin.eveonline.neocom.infinity.support.CucumberTableToRequestConverter;
import org.dimensinfin.eveonline.neocom.infinity.support.NeoComWorld;
import org.dimensinfin.eveonline.neocom.infinity.support.RequestType;

@Deprecated
public class SupportSteps {
	protected ConverterContainer cucumberTableToRequestConverters;
	protected NeoComWorld neocomWorld;

// - C O N S T R U C T O R S
	public SupportSteps( final ConverterContainer cucumberTableToRequestConverters, final NeoComWorld neocomWorld ) {
		this.cucumberTableToRequestConverters = cucumberTableToRequestConverters;
		this.neocomWorld = neocomWorld;
	}

	protected CucumberTableToRequestConverter findConverter( final RequestType requestType ) {
		return this.cucumberTableToRequestConverters.getConverters().stream()
				.filter( cucumberTableToRequestConverter -> cucumberTableToRequestConverter.getType() == requestType )
				.findFirst()
				.orElseThrow( () -> new NotImplementedException( "Request not implemented." ) );
	}
}
