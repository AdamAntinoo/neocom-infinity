//	PROJECT:      Neocom.Microservices (NEOC-MS)
//	AUTHORS:      Adam Antinoo - adamantinoo.git@gmail.com
//	COPYRIGHT:      (c) 2017 by Dimensinfin Industries, all rights reserved.
//	ENVIRONMENT:	SpringBoot-MS-Java 1.8.
//	DESCRIPTION:	This is the integration project for all the web server pieces. This is the launcher for
//								the SpringBoot+MicroServices+Angular unified web application.
package org.dimensinfin.eveonline.neocom.connector;

import org.dimensinfin.eveonline.neocom.core.CoreCacheConnector;

// - CLASS IMPLEMENTATION ...................................................................................
public class MicroServicesCacheConnector extends CoreCacheConnector implements ICacheConnector {
	// - S T A T I C - S E C T I O N ..........................................................................
	//	private static Logger	logger				= Logger.getLogger("MicroServicesCacheConnector");

	// - F I E L D - S E C T I O N ............................................................................
	private int	topCounter		= 0;
	private int	marketCounter	= 0;

	// - C O N S T R U C T O R - S E C T I O N ................................................................
	public MicroServicesCacheConnector() {
	}

	// - M E T H O D - S E C T I O N ..........................................................................
	@Override
	public int decrementMarketCounter() {
		this.marketCounter--;
		if (marketCounter < 0) {
			marketCounter = 0;
		}
		return marketCounter;
	}

	@Override
	public int decrementTopCounter() {
		topCounter--;
		if (topCounter < 0) {
			topCounter = 0;
		}
		return topCounter;
	}

	@Override
	public int incrementMarketCounter() {
		return this.marketCounter++;
	}

	@Override
	public int incrementTopCounter() {
		return this.topCounter++;
	}

}

// - UNUSED CODE ............................................................................................