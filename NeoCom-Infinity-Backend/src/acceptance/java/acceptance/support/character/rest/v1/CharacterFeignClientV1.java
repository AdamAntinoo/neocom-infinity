package acceptance.support.character.rest.v1;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import acceptance.support.AcceptanceTargetConfig;
import org.dimensinfin.eveonline.neocom.infinity.support.core.CommonFeignClient;

@Component
public class CharacterFeignClientV1   extends CommonFeignClient {
	// - C O N S T R U C T O R S
	public CharacterFeignClientV1( final @NotNull AcceptanceTargetConfig acceptanceTargetConfig ) {
		super( acceptanceTargetConfig );
	}
}
