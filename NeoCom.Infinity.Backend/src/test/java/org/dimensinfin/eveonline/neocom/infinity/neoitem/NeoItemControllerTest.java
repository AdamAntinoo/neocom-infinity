package org.dimensinfin.eveonline.neocom.infinity.neoitem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.dimensinfin.eveonline.neocom.domain.NeoItem;

public class NeoItemControllerTest {
	@Test
	public void createContract() {
		final NeoItemService neoItemService = Mockito.mock( NeoItemService.class );
		final NeoItemController neoItemController = new NeoItemController( neoItemService );
		Assertions.assertNotNull( neoItemController );
	}

	@Test
	public void getItemBasic() {
		final NeoItemService neoItemService = Mockito.mock( NeoItemService.class );
		final NeoItemController neoItemController = new NeoItemController( neoItemService );
		Assertions.assertNotNull( neoItemController );
		final NeoItem item = new NeoItem( 123 );
		final ResponseEntity<NeoItem> expected = new ResponseEntity<>( item, HttpStatus.OK );
		// When
		Mockito.when( neoItemService.getItemBasic( Mockito.anyInt() ) ).thenReturn( item );
		final ResponseEntity<NeoItem> obtained = neoItemController.getItemBasic( 123 );
		Assertions.assertEquals( expected, obtained );
	}
}