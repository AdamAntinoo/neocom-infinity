package org.dimensinfin.eveonline.neocom.infinity.backend.universe.item.rest.v1;

import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.domain.NeoItem;

@Service
public class NeoItemServiceV1 {
	public NeoItem getItem( final Integer itemId ) {
		return new NeoItem( itemId );
	}
}
