package acceptance.steps;

import java.io.IOException;
import javax.validation.constraints.NotNull;

import org.junit.Assert;

import org.dimensinfin.eveonline.neocom.infinity.support.NeoComWorld;
import org.dimensinfin.eveonline.neocom.infinity.support.rest.NeoComSupportFeignClient;

import io.cucumber.java.en.Given;

public class GivenACleanCredentialsRepository extends StepSupport {
	private final NeoComSupportFeignClient neoComSupportFeignClient;

	// - C O N S T R U C T O R S
	public GivenACleanCredentialsRepository( final @NotNull NeoComWorld neoComWorld,
	                                         final @NotNull NeoComSupportFeignClient neoComSupportFeignClient ) {
		super( neoComWorld );
		this.neoComSupportFeignClient = neoComSupportFeignClient;
	}

	@Given("a clean Credentials repository")
	public void a_clean_Coils_repository() throws IOException {
		// Send the support message to clean the list of credentials at the repository.
		this.neoComSupportFeignClient.deleteAllCredentials();
		final Integer obtained = this.neoComSupportFeignClient.countCredentials();
		Assert.assertEquals( 0, obtained.intValue() );
	}
}
