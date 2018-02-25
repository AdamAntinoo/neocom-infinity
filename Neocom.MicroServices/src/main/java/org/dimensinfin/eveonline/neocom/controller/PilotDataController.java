//  PROJECT:     NeoCom.Microservices (NEOC.MS)
//  AUTHORS:     Adam Antinoo - adamantinoo.git@gmail.com
//  COPYRIGHT:   (c) 2017-2018 by Dimensinfin Industries, all rights reserved.
//  ENVIRONMENT: Java 1.8 / SpringBoot-1.3.5 / Angular 5.0
//  DESCRIPTION: This is the SpringBoot MicroServices module to run the backend services to complete the web
//               application based on Angular+SB. This is the web version for the NeoCom Android native
//               application. Most of the source code is common to both platforms and this module includes
//               the source for the specific functionality for the backend services.
package org.dimensinfin.eveonline.neocom.controller;

import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.NeoComMicroServiceApplication;
import org.dimensinfin.eveonline.neocom.database.entity.Credential;
import org.dimensinfin.eveonline.neocom.datamngmt.manager.GlobalDataManager;
import org.dimensinfin.eveonline.neocom.exception.JsonExceptionInstance;
import org.dimensinfin.eveonline.neocom.industry.Action;
import org.dimensinfin.eveonline.neocom.industry.FittingProcessor;
import org.dimensinfin.eveonline.neocom.model.Fitting;
import org.dimensinfin.eveonline.neocom.storage.DataManagementModelStore;

// - CLASS IMPLEMENTATION ...................................................................................
@RestController
public class PilotDataController {
	// - S T A T I C - S E C T I O N ..........................................................................
	private static Logger logger = LoggerFactory.getLogger("PilotDataController");
	private static final HashMap<Integer, Fitting> fittingsCache = new HashMap<>();

	// - F I E L D - S E C T I O N ............................................................................

	// - C O N S T R U C T O R - S E C T I O N ................................................................

	// - M E T H O D - S E C T I O N ..........................................................................

	/**
	 * Returns the list of fittings that are accesible to this Pilot identifier. This data will be processed at the Angular side
	 * to generate any UI structures required for a proper presentation.
	 *
	 * @param identifier identifier for the selected Pilot.
	 * @return list of OK class fittings serialized to Json.
	 */
	@CrossOrigin()
	@RequestMapping(value = "/api/v1/pilot/{identifier}/fittingmanager/fittings", method = RequestMethod.GET, produces =
			"application/json")
	public String pilotFittingManagerFittings( @PathVariable final String identifier ) {
		logger.info(">>>>>>>>>>>>>>>>>>>>NEW REQUEST: /api/v1/pilot/{}/fittingmanager/fittings", identifier);
		logger.info(">> [PilotDataController.pilotFittingManagerFittings]");
		try {
			final Integer id = Integer.valueOf(identifier);
			// Activate the list of credentials.
			DataManagementModelStore.activateCredential(id);
			// Get the list of fittings.
			final List<Fitting> fittings = GlobalDataManager.downloadFitting4Credential(id);
			final String contentsSerialized = NeoComMicroServiceApplication.jsonMapper.writeValueAsString(fittings);
//			// Initialize the model data hierarchies.
//			NeoComMSConnector.getSingleton().getModelStore().activateLoginIdentifier(login);
//			NeoComCharacter pilot = NeoComMSConnector.getSingleton().getModelStore().activatePilot(Long.valueOf(identifier));
//			AssetsManager assetsMan = pilot.getAssetsManager().initialize();
			//			// Download the contents for all locations.
			//			Hashtable<Long, ExtendedLocation> locs = assetsMan.getLocations();
			//			for (Long key : locs.keySet()) {
			//				locs.get(key).getContents();
			//			}
			return contentsSerialized;
		} catch (NumberFormatException nfe) {
			logger.error("EX [PilotDataController.pilotFittingManagerFittings]> identifier received cannot be translated to number - " +
					"{}", nfe.getMessage());
			return new JsonExceptionInstance("Identifier received cannot be translated to number - " + nfe.getMessage()
			).toJson();
		} catch (JsonProcessingException jpe) {
			jpe.printStackTrace();
			return new JsonExceptionInstance(jpe.getMessage()).toJson();
		} catch (RuntimeException rtx) {
			rtx.printStackTrace();
			return new JsonExceptionInstance(rtx.getMessage()).toJson();
		} finally {
			logger.info("<< [PilotDataController.pilotFittingManagerFittings]");
		}
	}

	/**
	 * Returns the list of fittings that are accesible to this Pilot identifier. This data will be processed at the Angular side
	 * to generate any UI structures required for a proper presentation.
	 *
	 * @param identifier identifier for the selected Pilot.
	 * @return list of OK class fittings serialized to Json.
	 */
	@CrossOrigin()
	@RequestMapping(value = "/api/v1/pilot/{identifier}/fittingmanager/processfitting/{fittingidentifier}/copies/{copies}"
			, method = RequestMethod.GET
			, produces = "application/json")
	public String pilotFittingManagerProcessFitting( @PathVariable final int identifier
			, @PathVariable final int fittingidentifier
			, @PathVariable final int copies ) {
		logger.info(">>>>>>>>>>>>>>>>>>>>NEW REQUEST: /api/v1/pilot/{}/fittingmanager/processfitting/{}/copies/{}"
				, identifier, fittingidentifier, copies);
		logger.info(">> [PilotDataController.pilotFittingManagerProcessFitting]");
		try {
			// Activate the list of credentials.
			final Credential credential = DataManagementModelStore.activateCredential(identifier);
			// Get the list of fittings.
			final List<Fitting> fittings = GlobalDataManager.downloadFitting4Credential(identifier);
			addFittings2Cache(fittings);
			// Search for the fitting
			final Fitting target = fittingsCache.get(fittingidentifier);
			final FittingProcessor processor = new FittingProcessor();
			final List<Action> actions = processor.processFitting(identifier, target, copies);

			// Searialize the results.
			final String contentsSerialized = NeoComMicroServiceApplication.jsonMapper.writeValueAsString(actions);
			return contentsSerialized;
		} catch (NumberFormatException nfe) {
			logger.error("EX [PilotDataController.pilotFittingManagerFittings]> identifier received cannot be translated to number - " +
					"{}", nfe.getMessage());
			return new JsonExceptionInstance("Identifier received cannot be translated to number - " + nfe.getMessage()
			).toJson();
		} catch (JsonProcessingException jpe) {
			jpe.printStackTrace();
			return new JsonExceptionInstance(jpe.getMessage()).toJson();
		} catch (RuntimeException rtx) {
			rtx.printStackTrace();
			try {
				return NeoComMicroServiceApplication.jsonMapper.writeValueAsString(new JsonExceptionInstance(rtx.getMessage()));
			} catch (JsonProcessingException e) {
				return new JsonExceptionInstance(rtx.getMessage()).toJson();
			}
		} finally {
			logger.info("<< [PilotDataController.pilotFittingManagerProcessFitting]");
		}
	}

	protected void addFittings2Cache( final List<Fitting> newfittings ) {
		logger.info(">> [PilotDataController.addFittings2Cache]");
		for (Fitting fit : newfittings) {
			fittingsCache.put(fit.getFittingId(), fit);
		}
		logger.info("<< [PilotDataController.addFittings2Cache]");
	}
//	protected Fitting searchFitting(final int identifier){
////		logger.info(">> [PilotDataController.searchFitting]");
//		return
////		logger.info("<< [PilotDataController.searchFitting]");
//	}
//[01]
}

// - UNUSED CODE ............................................................................................
//[01]
//	@CrossOrigin()
//	@RequestMapping(value = "/api/v1/login/{login}/pilot/{identifier}", method = RequestMethod.GET, produces = "application/json")
//	public NeoComCharacter pilotDetailed( @PathVariable final String login, @PathVariable final String identifier ) {
//		logger.info(">>>>>>>>>>>>>>>>>>>>NEW REQUEST: " + "/api/v1/login/{" + login + "}/pilot/{" + identifier + "}");
//		logger.info(">> [PilotRoasterController.pilotDetailed]");
//		//	Vector<AbstractManager> managerList = new Vector<AbstractManager>();
//		try {
//			// Initialize the model data hierarchies.
//			NeoComMSConnector.getSingleton().getModelStore().activateLoginIdentifier(login);
//			NeoComCharacter pilot = NeoComMSConnector.getSingleton().getModelStore().activatePilot(Long.valueOf(identifier));
//			return pilot;
//		} catch (RuntimeException rtx) {
//			rtx.printStackTrace();
//			return null;
//		} finally {
//			logger.info("<< [PilotRoasterController.pilotDetailed]");
//		}
//	}
//
//	@CrossOrigin()
//	@RequestMapping(value = "/api/v1/login/{login}/pilot/{identifier}/pilotmanagers", method = RequestMethod.GET, produces = "application/json")
//	public Vector<AbstractManager> pilotManagers( @PathVariable final String login,
//	                                              @PathVariable final String identifier ) {
//		logger.info(">>>>>>>>>>>>>>>>>>>>NEW REQUEST: " + "/api/v1/login/{" + login + "}/pilot/{" + identifier + "}"
//				+ "/pilotmanagers");
//		logger.info(">> [PilotRoasterController.pilotManagers]");
//		Vector<AbstractManager> managerList = new Vector<AbstractManager>();
//		try {
//			// Initialize the model data hierarchies.
//			NeoComMSConnector.getSingleton().getModelStore().activateLoginIdentifier(login);
//			NeoComCharacter pilot = NeoComMSConnector.getSingleton().getModelStore().activatePilot(Long.valueOf(identifier));
//			logger.info("-- [PilotRoasterController.pilotManagers]> Accessing AssetsManager.");
//			managerList.addElement(pilot.getAssetsManager());
//			logger.info("-- [PilotRoasterController.pilotManagers]> Accessing PlanetaryManager.");
//			managerList.addElement(pilot.getPlanetaryManager());
//			return managerList;
//		} catch (RuntimeException rtx) {
//			rtx.printStackTrace();
//			return new Vector<AbstractManager>();
//		} finally {
//			logger.info("<< [PilotRoasterController.pilotManagers]");
//		}
//	}
//
//	@CrossOrigin()
//	@RequestMapping(value = "/api/v1/login/{login}/pilot/{identifier}/planetarymanager", method = RequestMethod.GET, produces = "application/json")
//	public AbstractManager pilotPlanetaryManager( @PathVariable final String login,
//	                                              @PathVariable final String identifier ) {
//		logger.info(">>>>>>>>>>>>>>>>>>>>NEW REQUEST: " + "/api/v1/login/{" + login + "}/pilot/{" + identifier
//				+ "}/planetarymanager");
//		logger.info(">> [PilotRoasterController.pilotPlanetaryManager]");
//		try {
//			// Initialize the model data hierarchies.
//			NeoComMSConnector.getSingleton().getModelStore().activateLoginIdentifier(login);
//			NeoComMSConnector.getSingleton().getModelStore().activatePilot(Long.valueOf(identifier));
//			return NeoComMSConnector.getSingleton().getModelStore().getActiveCharacter().getPlanetaryManager().initialize();
//		} catch (RuntimeException rtx) {
//			rtx.printStackTrace();
//			return null;
//		} finally {
//			logger.info("<< [PilotRoasterController.pilotPlanetaryManager]");
//		}
//	}
