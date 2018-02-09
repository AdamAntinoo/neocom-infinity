//	PROJECT:      Neocom.Microservices (NEOC-MS)
//	AUTHORS:      Adam Antinoo - adamantinoo.git@gmail.com
//	COPYRIGHT:      (c) 2017 by Dimensinfin Industries, all rights reserved.
//	ENVIRONMENT:	SpringBoot-MS-Java 1.8.
//	DESCRIPTION:	This is the integration project for all the web server pieces. This is the launcher for
//								the SpringBoot+MicroServices+Angular unified web application.
package org.dimensinfin.eveonline.neocom.services;

import java.util.Hashtable;
import java.util.logging.Logger;

import org.dimensinfin.eveonline.neocom.connector.NeoComMSConnector;
import org.dimensinfin.eveonline.neocom.enums.EDataBlock;
import org.dimensinfin.eveonline.neocom.model.Login;
import org.dimensinfin.eveonline.neocom.model.NeoComCharacter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

// - CLASS IMPLEMENTATION ...................................................................................
@Component
public class TimedCharacterUpdaterLauncher {
	// - S T A T I C - S E C T I O N ..........................................................................
	private static Logger logger = Logger.getLogger("TimedCharacterUpdaterLauncher");

	// - F I E L D - S E C T I O N ............................................................................

	// - C O N S T R U C T O R - S E C T I O N ................................................................
	public TimedCharacterUpdaterLauncher() {
	}

	// - M E T H O D - S E C T I O N ..........................................................................
	@Scheduled(initialDelay = 60000, fixedDelay = 15000)
	public void onTime() {
		logger.info(">> [TimedCharacterUpdaterLauncher.onTime]");
		Hashtable<String, Login> logins = NeoComMSConnector.getSingleton().getModelStore().accessLoginList();
		for (String key : logins.keySet()) {
			for (NeoComCharacter eveChar : logins.get(key).getCharacters()) {
				EDataBlock updateCode = eveChar.needsUpdate();
				if (updateCode != EDataBlock.READY) {
					logger.info("-- [TimeTickReceiver.onTime] EDataBlock to update: " + eveChar.getName() + " - " + updateCode);
					CharacterUpdaterService.processCharacter(eveChar);
				}
			}
		}
		logger.info("<< [TimedCharacterUpdaterLauncher.onTime]");
	}
}
// - UNUSED CODE ............................................................................................