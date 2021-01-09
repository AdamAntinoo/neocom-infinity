package acceptance.steps;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import org.dimensinfin.eveonline.neocom.infinity.support.ConverterContainer;
import org.dimensinfin.eveonline.neocom.infinity.support.NeoComWorld;
import org.dimensinfin.eveonline.neocom.infinity.support.corporation.rest.v1.LocationAssetContainer;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class NIB02Corporation extends SupportSteps {
	@Autowired
	public NIB02Corporation( final ConverterContainer cucumberTableToRequestConverters,
	                         final NeoComWorld neocomWorld ) {
		super( cucumberTableToRequestConverters , neocomWorld);
	}
	@Given("{string} authorization token")
	public void authorization_token( String jwtToken ) {
		// Process special values
		if (jwtToken.equalsIgnoreCase( "<null>" )) {
			this.neocomWorld.setJwtAuthorizationToken( null );
			return;
		}
		this.neocomWorld.setJwtAuthorizationToken( jwtToken );
	}

	@Then("the resulting list has the next data")
	public void the_resulting_list_has_the_next_data( final List<Map<String, String>> dataTable ) {
		final String JSON_CLASS = "jsonClass";
		final String CONTAINER_TYPE = "containerType";
		final String CONTENTS_COUNT = "contentsCount";
		final String REGION = "region";
		final String CONSTELLATION = "constellation";
		final String SYSTEM = "system";
		final String STATION = "station";
		final List<LocationAssetContainer> assetLocations = this.neocomWorld.getCorporationAssetsByLocation();
		for (int i = 0; i < dataTable.size(); i++) {
			final Map<String, String> row = dataTable.get( i );
			final LocationAssetContainer location = assetLocations.get( i );
			Assert.assertEquals( row.get( JSON_CLASS ), location.getJsonClass() );
			Assert.assertEquals( row.get( CONTAINER_TYPE ), location.getContainerType() );
			Assert.assertEquals( Integer.valueOf( row.get( CONTENTS_COUNT ) ).intValue(), location.getContents().size() );
			Assert.assertEquals( row.get( REGION ), location.getSpaceLocationJson().getRegionName() );
			Assert.assertEquals( row.get( CONSTELLATION ), location.getSpaceLocationJson().getConstellationName() );
			Assert.assertEquals( row.get( SYSTEM ), location.getSpaceLocationJson().getSystemName() );
			Assert.assertEquals( row.get( STATION ), location.getSpaceLocationJson().getStationName() );
		}
	}

	@Then("the resulting list of locations has {string} elements")
	public void the_resulting_list_of_locations_has_elements( String elementCount ) {
		Assert.assertEquals( Integer.parseInt( elementCount ), this.neocomWorld.getCorporationAssetsByLocation().size() );
	}
}
