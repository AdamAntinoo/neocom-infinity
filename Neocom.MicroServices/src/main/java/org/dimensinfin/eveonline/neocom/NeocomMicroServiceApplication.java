//  PROJECT:     Neocom.Microservices (NEOC-MS)
//  AUTHORS:     Adam Antinoo - adamantinoo.git@gmail.com
//  COPYRIGHT:   (c) 2017-2018 by Dimensinfin Industries, all rights reserved.
//  ENVIRONMENT: Java 1.8 / SpringBoot-1.3.5 / Angular 5.0
//  DESCRIPTION: This is the SpringBoot MicroServices module to run the backend services to complete the web
//               application based on Angular+SB. This is the web version for the NeoCom Android native
//               application. Most of the source code is common to both platforms and this module includes
//               the source for the specific functionality for the backend services.
package org.dimensinfin.eveonline.neocom;

import java.io.IOException;
import java.sql.SQLException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import org.dimensinfin.eveonline.neocom.database.NeoComSBDBHelper;
import org.dimensinfin.eveonline.neocom.database.SDESBDBHelper;
import org.dimensinfin.eveonline.neocom.database.entity.Credential;
import org.dimensinfin.eveonline.neocom.datamngmt.manager.ESINetworkManager;
import org.dimensinfin.eveonline.neocom.datamngmt.manager.GlobalDataManager;
import org.dimensinfin.eveonline.neocom.datamngmt.manager.MarketDataServer;
import org.dimensinfin.eveonline.neocom.model.Ship;
import org.dimensinfin.eveonline.neocom.services.TimedUpdater;

// - CLASS IMPLEMENTATION ...................................................................................

/**
 * This is the initial class and loader for the Spring Boot application. It will be used to integrate the
 * different modules and libraries, instantiate the adapters and serve as the base point to integrate the
 * different controllers. Most of the code will be imported and integrated from the depending libraries.
 *
 * @author Adam Antinoo
 */
//@EnableCaching
//@EnableCircuitBreaker
@EnableScheduling
//@EnableAsync
@SpringBootApplication
public class NeoComMicroServiceApplication {
	// - S T A T I C - S E C T I O N ..........................................................................
	private static Logger logger = LoggerFactory.getLogger("NeoComMicroServiceApplication");
	public static final ObjectMapper jsonMapper = new ObjectMapper();
	public static MarketDataServer mdServer = null;
	public static final TimedUpdater timedService = new TimedUpdater();

	static {
		jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
		jsonMapper.registerModule(new JodaModule());
		// Add our own serializers.
		SimpleModule neocomSerializerModule = new SimpleModule();
		neocomSerializerModule.addSerializer(Ship.class, new ShipSerializer());
		neocomSerializerModule.addSerializer(Credential.class, new CredentialSerializer());
		jsonMapper.registerModule(neocomSerializerModule);
	}

	// - M A I N   E N T R Y P O I N T ........................................................................

	/**
	 * Create all the platform specific connectors and then launch it to run.
	 *
	 * @param args
	 */
	public static void main( final String[] args ) {
		logger.info(">> [NeoComMicroServiceApplication.main]");
		// Instance and connect the Adaptors.
		// Connect the Configuration manager.
		// Not required. The default configuration manager already reads the properties folder.
//		logger.info("-- [NeoComMicroServiceApplication.main]> Connecting the Configuration Manager...");
//		GlobalDataManager.connectConfigurationManager(new GlobalConfigurationProvider(null));

		// Initializing the ESI api network controller.
//		ESINetworkManager.initialize();

		// Connect the SDE database.
		logger.info("-- [NeoComMicroServiceApplication.main]> Connecting SDE database...");
		try {
			GlobalDataManager.connectSDEDBConnector(new SDESBDBHelper()
					.setDatabaseSchema(GlobalDataManager.getResourceString("R.database.sdedatabase.databaseschema"))
					.setDatabasePath(GlobalDataManager.getResourceString("R.database.sdedatabase.databasepath"))
					.setDatabaseName(GlobalDataManager.getResourceString("R.database.sdedatabase.databasename"))
					.build()
			);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		// Connect the NeoCom database.
		logger.info("-- [NeoComMicroServiceApplication.main]> Connecting NeoCom private database...");
		try {
			GlobalDataManager.connectNeoComDBConnector(new NeoComSBDBHelper()
					.setDatabaseHost(GlobalDataManager
							.getResourceString("R.database.neocom.databasehost", "jdbc:mysql://localhost:3306"))
					.setDatabaseName("neocom")
					.setDatabaseUser("NEOCOM")
					.setDatabasePassword("01.Alpha")
					.setDatabaseVersion(GlobalDataManager.getResourceInt("R.database.neocom.databaseversion"))
					.build()
			);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		// Connect the MarketData service.
		logger.info("-- [NeoComMicroServiceApplication.main]> Starting Market Data service...");
		mdServer = new MarketDataServer().start();
		GlobalDataManager.setMarketDataManager(mdServer);

//		// Connect the Timed Upgrade scan.
//		logger.info("-- [NeoComMicroServiceApplication.main]> Connecting the background timed download scanner...");
//		timedService = new TimedUpdater();

		logger.info("-- [NeoComMicroServiceApplication.main]> Starting application instance...");
		SpringApplication.run(NeoComMicroServiceApplication.class, args);
		logger.info("<< [NeoComMicroServiceApplication.main]");
	}

	// - F I E L D - S E C T I O N ............................................................................

	// - C O N S T R U C T O R - S E C T I O N ................................................................

	// - M E T H O D - S E C T I O N ..........................................................................
	@Scheduled(initialDelay = 180000, fixedDelay = 120000)
	private void writeMarketData() {
		mdServer.writeMarketDataCacheToStorage();
	}

	@Scheduled(initialDelay = 120000, fixedDelay = 900000)
	private void onTime() {
		// Fire another background update scan.
		// Check if the configuration properties allow to run the updater.
		if (GlobalDataManager.getResourceBoolean("R.updater.allowtimer",false)) {
			timedService.timeTick();
		}
	}

	// - CLASS IMPLEMENTATION ...................................................................................
	public static class ShipSerializer extends JsonSerializer<Ship> {
		// - F I E L D - S E C T I O N ............................................................................

		// - M E T H O D - S E C T I O N ..........................................................................
		@Override
		public void serialize( final Ship value, final JsonGenerator jgen, final SerializerProvider provider )
				throws IOException, JsonProcessingException {
			jgen.writeStartObject();
			jgen.writeStringField("jsonClass", value.getJsonClass());
			jgen.writeNumberField("assetId", value.getAssetId());
			jgen.writeNumberField("typeId", value.getTypeId());
			jgen.writeNumberField("ownerId", value.getOwnerID());
			jgen.writeStringField("name", value.getItemName());
			jgen.writeStringField("category", value.getCategory());
			jgen.writeStringField("groupName", value.getGroupName());
			jgen.writeStringField("tech", value.getTech());
			jgen.writeStringField("userLabel", value.getUserLabel());
			jgen.writeNumberField("price", value.getItem().getPrice());
			jgen.writeNumberField("highesBuyerPrice", value.getItem().getHighestBuyerPrice().getPrice());
			jgen.writeNumberField("lowerSellerPrice", value.getItem().getLowestSellerPrice().getPrice());
			jgen.writeObjectField("item", value.getItem());
			jgen.writeEndObject();
		}
	}
	// ........................................................................................................

	// - CLASS IMPLEMENTATION ...................................................................................
	public static class CredentialSerializer extends JsonSerializer<Credential> {
		// - F I E L D - S E C T I O N ............................................................................

		// - M E T H O D - S E C T I O N ..........................................................................
		@Override
		public void serialize( final Credential value, final JsonGenerator jgen, final SerializerProvider provider )
				throws IOException, JsonProcessingException {
			jgen.writeStartObject();
			jgen.writeStringField("jsonClass", value.getJsonClass());
			jgen.writeNumberField("accountId", value.getAccountId());
			jgen.writeStringField("accountName", value.getAccountName());
			jgen.writeStringField("tokenType", value.getTokenType());
			jgen.writeBooleanField("isActive", value.isActive());
			jgen.writeBooleanField("isXML", value.isXMLCompatible());
			jgen.writeBooleanField("isESI", value.isESICompatible());
			jgen.writeObjectField("pilot", GlobalDataManager.getPilotV1(value.getAccountId()));
			jgen.writeEndObject();
		}
	}
	// ........................................................................................................
	// - CLASS IMPLEMENTATION ...................................................................................
//	public static class LocationSerializer extends JsonSerializer<EveLocation> {
//		// - F I E L D - S E C T I O N ............................................................................
//
//		// - M E T H O D - S E C T I O N ..........................................................................
//		@Override
//		public void serialize( final EveLocation value, final JsonGenerator jgen, final SerializerProvider provider )
//				throws IOException, JsonProcessingException {
//			jgen.writeStartObject();
//			jgen.writeStringField("jsonClass", value.getJsonClass());
//			jgen.writeNumberField("accountId", value.getAccountId());
//			jgen.writeStringField("accountName", value.getAccountName());
//			jgen.writeStringField("tokenType", value.getTokenType());
//			jgen.writeBooleanField("isActive", value.isActive());
//			jgen.writeBooleanField("isXML", value.isXMLCompatible());
//			jgen.writeBooleanField("isESI", value.isESICompatible());
//			jgen.writeObjectField("pilot", GlobalDataManager.getPilotV1(value.getAccountId()));
//			jgen.writeEndObject();
//
//
//
//			@JsonIgnore
//			@DatabaseField(id = true, index = true)
//			protected long id = -2;
//			@DatabaseField
//			protected long stationID = -1;
//			@DatabaseField
//			private String station = "SPACE";
//			@DatabaseField
//			protected long systemID = -1;
//			@DatabaseField
//			private String system = "UNKNOWN";
//			@DatabaseField
//			protected long constellationID = -1;
//			@DatabaseField
//			private String constellation = "Echo Cluster";
//			@DatabaseField
//			protected long regionID = -1;
//			@DatabaseField
//			private String region = "-DEEP SPACE-";
//			@DatabaseField
//			private String security = "0.0";
//			@DatabaseField
//			protected String typeID = ELocationType.UNKNOWN.name();
//			public String urlLocationIcon = null;
//
//
//
//
//
//		}
//	}
	// ........................................................................................................
}
// - UNUSED CODE ............................................................................................
