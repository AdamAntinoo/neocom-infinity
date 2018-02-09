//  PROJECT:     NeoCom.Microservices (NEOC.MS)
//  AUTHORS:     Adam Antinoo - adamantinoo.git@gmail.com
//  COPYRIGHT:   (c) 2017-2018 by Dimensinfin Industries, all rights reserved.
//  ENVIRONMENT: Java 1.8 / SpringBoot-1.3.5 / Angular 5.0
//  DESCRIPTION: This is the SpringBoot MicroServices module to run the backend services to complete the web
//               application based on Angular+SB. This is the web version for the NeoCom Android native
//               application. Most of the source code is common to both platforms and this module includes
//               the source for the specific functionalityfor the backend services.
package org.dimensinfin.eveonline.neocom.testblock.database;

import java.sql.SQLException;

import org.dimensinfin.eveonline.neocom.database.ISDEDBHelper;
import org.dimensinfin.eveonline.neocom.database.SDESBDBHelper;
import org.dimensinfin.eveonline.neocom.datamngmt.manager.GlobalDataManager;
import org.dimensinfin.eveonline.neocom.model.EveItem;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test unit for openning and data access to the different queries supported by the SDE Eve Online game model database. The
 * test will cover all the different queries adn will also test the cases where the parameters are not expected or the use of
 * caches.
 *
 * @author Adam Antinoo
 */
// - CLASS IMPLEMENTATION ...................................................................................
public class SDEDatabaseTestUnit {
	// - S T A T I C - S E C T I O N ..........................................................................
	private static Logger logger = LoggerFactory.getLogger(SDEDatabaseTestUnit.class);
//	public static final String APPLICATION_NAME = "NeoComMicroServiceApplication";
//	//	private static NeoComMSConnector _connector = null;
//	private static final int pilotid = 92223647;
//	private static final long locationidnumber = 60014089;
//
//	private static ExtendedLocation newloc = null;

//	@BeforeClass
//	public static void setUpBeforeClass () throws Exception {
//// Connect the Configuration manager.
//		GlobalDataManager.connectConfigurationManager(new SpringBootConfigurationProvider("src/test/resources/properties"));
//
//		// Connect the NeoCom database.
//		try {
//			GlobalDataManager.connectNeoComDBConnector(new NeoComSBDBHelper()
//					.setDatabaseHost(GlobalDataManager
//							.getResourceString("R.database.neocom.databasehost", "jdbc:mysql://localhost:3306"))
//					.setDatabaseName("neocom")
//					.setDatabaseUser("NEOCOMTEST")
//					.setDatabasePassword("01.Alpha")
//					.setDatabaseVersion(1)
//					.build()
//			);
//		} catch (SQLException sqle) {
//			sqle.printStackTrace();
//		}
//
//		// Connect the SDE database.
//		try {
//			GlobalDataManager.connectSDEDBConnector(new SDESBDBHelper()
//					.setDatabaseSchema("jdbc:sqlite")
//					.setDatabasePath("src/main/resources/")
//					.setDatabaseName("sde.db")
//					.build()
//			);
//		} catch (SQLException sqle) {
//			sqle.printStackTrace();
//		}
////
////
////
////
////
////		// Connect the code the the runtime librearies.
////
////		_connector = new NeoComMSConnector(new TestSerializationControl());
////		EveLocation location = NeoComMSConnector.getSingleton().getCCPDBConnector().searchLocationbyID(locationidnumber);
////		// Convert the Location to a new Extended Location with the new Contents Manager.
////		newloc = new ExtendedLocation(pilotid, location);
////		newloc.setContentManager(new DefaultAssetsContentManager(newloc));
//	}

	// - F I E L D - S E C T I O N ............................................................................
	private ISDEDBHelper sdeHelper = null;

	// - C O N S T R U C T O R - S E C T I O N ................................................................

	// - M E T H O D - S E C T I O N ..........................................................................
	@BeforeClass
	public void testOpenAndConnectDatabase() throws SQLException {
		// Connect the SDE database.
		logger.info(">> [NeoComMicroServiceApplication.main]> Connecting SDE database...");
		final String schema = GlobalDataManager.getResourceString("R.database.sdedatabase.databaseschema");
		Assert.assertEquals(schema, "jdbc:sqlite");
		final String dbpath = GlobalDataManager.getResourceString("R.database.sdedatabase.databasepath");
		Assert.assertEquals(dbpath, "src/main/resources/");
		final String dbname = GlobalDataManager.getResourceString("R.database.sdedatabase.databasename");
		Assert.assertEquals(dbname, "sdetest.sqlite");
		sdeHelper = new SDESBDBHelper()
				.setDatabaseSchema(schema)
				.setDatabasePath(dbpath)
				.setDatabaseName(dbname)
				.build();

		// Check the connection descriptor.
		Assert.assertEquals(sdeHelper.getConnectionDescriptor(), schema + ":" + dbpath + dbname);
		// Check the database is open and has a valid connection.
		Assert.assertTrue(sdeHelper.databaseIsValid());
	}

	@Test
	public void testGetItem4Id() throws SQLException {
		Assert.assertNotNull(sdeHelper);
		// Check the access to an item the first time. Be sure the cache is empty.
		GlobalDataManager.cleanEveItemCache();
		// Access a know item.
		final EveItem validItem = GlobalDataManager.searchItem4Id(34);
		// Use the specific test for a know item to check the results.
		Assert.assertEquals(validItem.getTypeID(), 34);
	}
}
// - UNUSED CODE ............................................................................................