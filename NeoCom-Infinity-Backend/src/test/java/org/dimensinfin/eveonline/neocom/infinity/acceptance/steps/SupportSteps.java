package org.dimensinfin.eveonline.neocom.infinity.acceptance.steps;

import org.apache.commons.lang3.NotImplementedException;

import org.dimensinfin.eveonline.neocom.infinity.support.ConverterContainer;
import org.dimensinfin.eveonline.neocom.infinity.support.CucumberTableToRequestConverter;
import org.dimensinfin.eveonline.neocom.infinity.support.NeoComWorld;
import org.dimensinfin.eveonline.neocom.infinity.support.RequestType;

public class SupportSteps {
	protected ConverterContainer cucumberTableToRequestConverters;
	protected NeoComWorld neocomWorld;

	public SupportSteps( final ConverterContainer cucumberTableToRequestConverters, final NeoComWorld neocomWorld ) {
		this.cucumberTableToRequestConverters = cucumberTableToRequestConverters;
		this.neocomWorld = neocomWorld;
	}

	protected CucumberTableToRequestConverter findConverter( RequestType requestType ) {
		return cucumberTableToRequestConverters.getConverters().stream()
				.filter( cucumberTableToRequestConverter -> cucumberTableToRequestConverter.getType() == requestType )
				.findFirst()
				.orElseThrow( () -> new NotImplementedException( "Request not implemented." ) );
	}
}
