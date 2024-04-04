package org.dimensinfin.eveonline.neocom.infinity.acceptance.support;

public class CommonValidator {
	protected String cleanKey( final String key ) {
		return key.replace( '>', ' ' ).trim();
	}

	protected String cucumberDataMap( final CommonWorld world, final String cucumberData ) {
		if (cucumberData.contains( "world" )) {
			final String key = this.cleanKey( cucumberData.split( ":" )[1] );
			switch (key) {
				case "id":
					return world.getId().toString();
			}
		}
		return cucumberData;
	}
}
