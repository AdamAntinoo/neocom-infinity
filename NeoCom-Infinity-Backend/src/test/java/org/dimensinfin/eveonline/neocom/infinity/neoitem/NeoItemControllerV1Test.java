package org.dimensinfin.eveonline.neocom.infinity.neoitem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.dimensinfin.eveonline.neocom.domain.NeoItem;
import org.dimensinfin.eveonline.neocom.infinity.backend.universe.item.rest.v1.NeoItemControllerV1;
import org.dimensinfin.eveonline.neocom.infinity.backend.universe.item.rest.v1.NeoItemServiceV1;

public class NeoItemControllerV1Test {
	@Test
	public void createContract() {
		final NeoItemServiceV1 neoItemServiceV1 = Mockito.mock( NeoItemServiceV1.class );
		final NeoItemControllerV1 neoItemControllerV1 = new NeoItemControllerV1( neoItemServiceV1 );
		Assertions.assertNotNull( neoItemControllerV1 );
	}

	@Test
	public void getItemBasic() {
		final NeoItemServiceV1 neoItemServiceV1 = Mockito.mock( NeoItemServiceV1.class );
		final NeoItemControllerV1 neoItemControllerV1 = new NeoItemControllerV1( neoItemServiceV1 );
		Assertions.assertNotNull( neoItemControllerV1 );
		final NeoItem item = new NeoItem( 123 );
		final ResponseEntity<NeoItem> expected = new ResponseEntity<>( item, HttpStatus.OK );
		// When
		Mockito.when( neoItemServiceV1.getItem( Mockito.anyInt() ) ).thenReturn( item );
		final ResponseEntity<NeoItem> obtained = neoItemControllerV1.getItem( 123 );
		Assertions.assertEquals( expected, obtained );
	}
}
