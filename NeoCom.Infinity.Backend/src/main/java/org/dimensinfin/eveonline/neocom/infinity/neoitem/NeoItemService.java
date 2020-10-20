package org.dimensinfin.eveonline.neocom.infinity.neoitem;

import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.domain.NeoItem;

@Service
public class NeoItemService {
	public NeoItem getItemBasic( final Integer itemId ) {
		return new NeoItem( itemId );
	}
}
