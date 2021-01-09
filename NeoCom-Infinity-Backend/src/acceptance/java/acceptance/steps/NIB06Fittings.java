package acceptance.steps;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import org.dimensinfin.eveonline.neocom.infinity.support.ConverterContainer;
import org.dimensinfin.eveonline.neocom.infinity.support.NeoComWorld;
import org.dimensinfin.eveonline.neocom.infinity.support.fitting.rest.v1.FittingResponse;

import io.cucumber.java.en.Then;

public class NIB06Fittings extends SupportSteps {
	private static final String FITTING_ID = "fittingId";
	private static final String FITTING_NAME = "name";
	private static final String HULL_CLASS = "hullClass";
	private static final ObjectMapper objectMapper = new ObjectMapper();
	private static final String SUPPORT_FILE_PATH = "/home/adam/Development/NeoCom/neocom-infinity/NeoCom.Infinity.Backend/backend" +
			".AcceptanceTest/src/test/resources/features/Fittings/";

	@Autowired
	public NIB06Fittings(
			final ConverterContainer cucumberTableToRequestConverters,
			final NeoComWorld neocomWorld ) {
		super( cucumberTableToRequestConverters, neocomWorld );
	}

	@Then("the first fitting has the next data")
	public void the_first_fitting_has_the_next_data( final List<Map<String, String>> dataTable ) {
		final Map<String, String> row = dataTable.get( 0 );
		final List<FittingResponse> response = this.neocomWorld.getFittingResponse();
		final FittingResponse fitting = response.get( 0 );
		Assert.assertNotNull( fitting );
		Assert.assertEquals( Integer.valueOf( row.get( FITTING_ID ) ).intValue(), fitting.getFittingId().intValue() );
		Assert.assertEquals( row.get( FITTING_NAME ), fitting.getName() );
		Assert.assertEquals( row.get( HULL_CLASS ), fitting.getHullClass() );
	}

	@Then("the response has {int} fittings")
	public void the_response_has_fittings( final Integer fittingNumber ) {
		final List<FittingResponse> response = this.neocomWorld.getFittingResponse();
		Assert.assertEquals( fittingNumber.intValue(), response.size() );
	}

//	@Then("the response has a fitting list response matching the next file {string}")
//	public void the_response_has_a_fitting_list_response_matching_the_next_file( final String testFilePath ) throws IOException {
//		final List<FittingResponse> response = this.neocomWorld.getFittingResponse();
//		final String responseJsonized = objectMapper.writeValueAsString( response );
//		final Path filePath = Paths.get( SUPPORT_FILE_PATH, testFilePath );
//		final String targetResponse = Files.readString( filePath );
//		Assert.assertTrue( targetResponse.equals( responseJsonized ) );
//	}
//	@Then("the exception is of type {string}")
//	public void the_exception_is_of_type(String string) {
//		// Write code here that turns the phrase above into concrete actions
//		throw new cucumber.api.PendingException();
//	}
}
