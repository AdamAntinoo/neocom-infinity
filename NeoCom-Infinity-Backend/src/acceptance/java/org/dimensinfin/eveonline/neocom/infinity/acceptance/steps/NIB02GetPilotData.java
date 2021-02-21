package org.dimensinfin.eveonline.neocom.infinity.acceptance.steps;

import org.springframework.beans.factory.annotation.Autowired;

import org.dimensinfin.eveonline.neocom.infinity.support.ConverterContainer;
import org.dimensinfin.eveonline.neocom.infinity.support.NeoComWorld;

@Deprecated
public class NIB02GetPilotData extends SupportSteps {
	private static final String PILOT_ID = "pilotId";
	private static final String CORPORATION_ID = "corporationId";
	private static final String NAME = "name";

// - C O N S T R U C T O R S
	@Autowired
	public NIB02GetPilotData( final ConverterContainer cucumberTableToRequestConverters,
	                          final NeoComWorld neocomWorld ) {
		super( cucumberTableToRequestConverters, neocomWorld );
	}

	//	@Then("the response has a pilot block with the next data")
	//	public void the_response_has_a_pilot_block_with_the_next_data( final List<Map<String, String>> dataTable ) {
	//		final Map<String, String> row = dataTable.get( 0 );
	//		final PilotResponse pilot = this.neocomWorld.getPilotResponseEntity().getBody();
	//		Assert.assertEquals( Integer.valueOf( row.get( PILOT_ID ) ).intValue(), pilot.getPilotId().intValue() );
	//		Assert.assertEquals( Integer.valueOf( row.get( CORPORATION_ID ) ).intValue(), pilot.getCorporationId().intValue() );
	//		Assert.assertEquals( row.get( NAME ), pilot.getName() );
	//	}
}
