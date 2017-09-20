//	PROJECT:      Neocom.Microservices (NEOC-MS)
//	AUTHORS:      Adam Antinoo - adamantinoo.git@gmail.com
//	COPYRIGHT:    (c) 2017 by Dimensinfin Industries, all rights reserved.
//	ENVIRONMENT:	SpringBoot-MS-Java 1.8.
//	DESCRIPTION:	This is the integration project for all the web server pieces. This is the launcher for
//								the SpringBoot+MicroServices+Angular unified web application.
package org.dimensinfin.eveonline.neocom.connector;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;

import org.dimensinfin.eveonline.neocom.core.NeocomRuntimeException;
import org.dimensinfin.eveonline.neocom.interfaces.INeoComModelStore;
import org.dimensinfin.eveonline.neocom.model.ApiKey;
import org.dimensinfin.eveonline.neocom.model.NeoComApiKey;
import org.dimensinfin.eveonline.neocom.model.NeoComCharacter;

import com.beimin.eveapi.exception.ApiException;

// - CLASS IMPLEMENTATION ...................................................................................
/**
 * This class has as main responsibility the access to the data input by the user and the store of all Eve
 * data downloaded from the CCP servers. The data to be manipulated has some levels, starting on the list of
 * credentials to access the CCP api and ending on processed and generated data from the other levels.<br>
 * The quantity of information makes a priority to keep it updated to the minor cost of processing. After
 * storing the data in serialized form or developing some cache variations I have choose the store into a
 * local database as the fastest way to have ready data while the lengthy download processes update most of
 * that information.<br>
 * <br>
 * The class is a singleton with two main structures, the list of api keys and the list of user defined
 * fittings. Both sets have to contain unique identifiers so any addition of a duplicated one will only
 * replace the older one. The persistence mechanics are delegated to a new PersistenceManager that will have
 * access to a SQLite database to keep track of this data structures, so all input/output should be handled by
 * that Manager.<br>
 * <br>
 * There should be some type of notification mechanism to report the UI about changes on the data contents
 * performed with background tasks. This is integrated on the GEF model hierarchy but has to be reviewed
 * inside Android special UI/non UI threads structure.
 * 
 * @author Adam Antinoo
 */
public class AppModelStore implements INeoComModelStore {

	// - S T A T I C - S E C T I O N ..........................................................................
	private static final long			serialVersionUID	= 8777607802616543118L;
	private static Logger					logger						= Logger.getLogger("AppModelStore");
	private static AppModelStore	singleton					= null;

	/**
	 * Returns the single global instance of the Store to be used as an instance. In case the instance does not
	 * exists then we initiate the initialization process trying to create the most recent instance we have
	 * recorded or recreate it from scratch from the api_list file.
	 * 
	 * @return the single global instance
	 */
	public static AppModelStore getSingleton() {
		if (null == AppModelStore.singleton) {
			// Initiate the recovery.
			AppModelStore.initialize();
		}
		return AppModelStore.singleton;
	}

	/**
	 * Forces the initialization of the Model store from the api list file. Instead reading the data from the
	 * store file it will process the api list and reload all the character information from scratch.
	 */
	public static void initialize() {
		AppModelStore.logger.info(">> [AppModelStore.initialize]");
		// Create a new from scratch.
		AppModelStore.singleton = new AppModelStore();
		AppModelStore.logger.info("<< [AppModelStore.initialize]");
	}

	// - F I E L D - S E C T I O N ............................................................................
	/** This is the unique list for all registered distinc Logins at the database. */
	private Vector<String>						_loginList				= null;
	/**
	 * This is the string identifier assigned to this session and that relates to an specific set of api keys.
	 */
	private String										_loginIdentifier	= "Default";
	/** The list of keys related to a login identifier */
	private List<NeoComApiKey>				_neocomApiKeys		= null;
	private List<NeoComCharacter>			_neocomCharacters	= null;
	private long											_pilotIdentifier	= -1L;
	/** Reference to the current active Character, be it a Pilot or a Corporation */
	private transient NeoComCharacter	_pilot						= null;

	private List<ApiKey>							_apiKeys					= null;
	private Vector<NeoComCharacter>		_pilotRoaster			= null;

	//	/** Reference to the application menu to make it accessible to any level. */
	//	private transient Menu									_appMenu					= null;
	//	/** Reference to the current active Activity. Sometimes this is needed to access application resources. */
	//	private transient Activity							_activity					= null;
	//	protected long													_pilotIdentifier	= -1L;
	//	/** Check to verify if the recovery process is successful. */
	//	private boolean													recovered					= false;
	//
	//	//	/** List of registered DataSources. This data is not stored on switch or termination. */
	//	//	private transient DataSourceManager			dsManager					= null;
	//	private HashMap<Integer, NeoComApiKey>	apiKeys						= new HashMap<Integer, NeoComApiKey>();
	//	/** List of fittings by name. This is the source for the Fittings DataSource. */
	//	private HashMap<String, Fitting>				fittings					= new HashMap<String, Fitting>();
	//	private transient HashMap<Long, EveChar>	charCache					= null;
	//	private final long											lastCCPAccessTime	= 0;

	// - C O N S T R U C T O R - S E C T I O N ................................................................
	private AppModelStore() {
		super();
	}

	// - M E T H O D - S E C T I O N ..........................................................................
	/**
	 * This is the main activation entry point for the Login. If there are no logins already on the ModelStore
	 * we should get them from the backend database and then search for the indicated login. If finally that
	 * login is not found we can fire an Exception.
	 * 
	 * @param login
	 */
	public String activateLoginIdentifier(final String loginTarget) {
		logger.info(">< [AppModelStore.activateLoginIdentifier]loginTarget: " + loginTarget);
		if (null == _loginList) _loginList = AppConnector.getDBConnector().queryAllLogins();
		if (_loginIdentifier.equalsIgnoreCase(loginTarget)) return _loginIdentifier;
		for (String login : _loginList) {
			if (login.equalsIgnoreCase(loginTarget)) {
				_loginIdentifier = loginTarget;
				// Update the list of api keys and the characters related to the login.
				this.readApiKeys();
				return _loginIdentifier;
			}
		}
		// We run through all the login list and did not found the login. Or a problem of an error. Fire exception.
		throw new NeocomRuntimeException(
				"RT[AppModelStore.activateLoginIdentifier]>Login " + loginTarget + " not found on current login list.");
	}

	/**
	 * Searches for the pilot on the character list active after the API key processing and copies it to the
	 * store slot.<br>
	 * On the new implementation change the search to delegate the action to a new data model where the list of
	 * keys comes from a database search and the list of associated keys are stored on the App Model.
	 * 
	 * @param characterid
	 *          id of the character to activate and select for work with.
	 */
	public NeoComCharacter activatePilot(final long characterid) {
		logger.info(">< [AppModelStore.activatePilot]Id: " + characterid);
		// If the current pilot is the one searched simplify the search.
		if ((null != _pilot) && ((_pilot.getCharacterID()) == characterid)) return _pilot;

		// If the list of pilots is empty then we have a problem when activating the Login. Try again.
		if (null == _neocomCharacters) this.readApiKeys();
		// Search for the Character
		_pilot = this.searchCharacter(characterid);
		if (null != _pilot) {
			_pilotIdentifier = _pilot.getCharacterID();
			return _pilot;
		}
		throw new RuntimeException(
				"RT [AppModelStore.activatePilot]>Pilot with id: " + characterid + " not located. Problem of initialization.");
	}

	/**
	 * Return the list of pilots that are currently active as selected by the user. The user may deactivate some
	 * pilots to reduce the view of data or pilots that are no longer active.<br>
	 * The start list is the list of characters associated to the llist of kays assocated to the current login.
	 * 
	 * @return
	 */
	@Deprecated
	public List<NeoComCharacter> getActiveCharacters() {
		// Iterate the list of pilots and accumulate the active ones.
		final ArrayList<NeoComCharacter> activePilots = new ArrayList<NeoComCharacter>();
		if (null == _neocomCharacters) {
			this.readApiKeys();
		}
		for (final NeoComCharacter pilot : _neocomCharacters)
			if (pilot.isActive()) {
				activePilots.add(pilot);
			}
		return activePilots;
	}

	@Deprecated
	public List<NeoComApiKey> getApiKeys() {
		if (null == _neocomApiKeys) {
			this.readApiKeys();
		}
		return _neocomApiKeys;
	}

	/**
	 * Returns the current active pilot or corporation if exists. If the value is not defined then this means
	 * that the model store has been initialized and that we should go back to the pilot roaster page to select
	 * a new one.
	 */
	public NeoComCharacter getCurrentPilot() {
		if (null != _pilot) return _pilot;
		throw new NeocomRuntimeException(
				"RT [AppModelStore.getCurrentPilot]>Selected character is not selected. Go back to home to select one.");
	}

	public String getLoginIdentifier() {
		if (null != _loginIdentifier) return _loginIdentifier;
		throw new NeocomRuntimeException(
				"RT [AppModelStore.getLoginIdentifier]>Login Identifier not defined. Setting to default if possible.");
	}

	@Deprecated
	public NeoComCharacter getPilot() {
		return this.getCurrentPilot();
	}

	/**
	 * The method gets access to the cached list of characters associated with a set of keys. The keys are
	 * identified by a single and unique login identifier that should be already set on the Model Store. If the
	 * lists are empty the method will access the database and the CCP services to get the information.<br>
	 * Also if the information stored is obsolete the procedure will update with fresh copies.
	 * 
	 * @return
	 */
	@Deprecated
	public List<NeoComCharacter> getPilotRoaster() {
		logger.info(">> [AppModelStore.getPilotRoaster]");
		//		// Check if the list is already available.
		//		if (null == _pilotRoaster) {
		//			// Access the database to get the list of keys. From that point on we can retrieve the characters easily.
		//			try {
		//				Dao<ApiKey, String> keyDao = AppConnector.getDBConnector().getApiKeysDao();
		//				QueryBuilder<ApiKey, String> queryBuilder = keyDao.queryBuilder();
		//				Where<ApiKey, String> where = queryBuilder.where();
		//				where.eq("login", getLoginIdentifier());
		//				PreparedQuery<ApiKey> preparedQuery = queryBuilder.prepare();
		//				_apiKeys = keyDao.query(preparedQuery);
		//			} catch (java.sql.SQLException sqle) {
		//				sqle.printStackTrace();
		//			}
		//			// For each key get the list of characters and instantiate them to the resulting list.
		//			_pilotRoaster = new Vector<>();
		//			for (ApiKey apiKey : _apiKeys) {
		//				try {
		//					NeoComApiKey key = NeoComApiKey.build(apiKey.getKeynumber(), apiKey.getValidationcode());
		//					// Update the time stamp for this valid information.
		//					_keysUntil = new DateTime(key.getCachedUntil().getTime());
		//					// Scan for the characters declared into this key.
		//					for (NeoComCharacter pilot : key.getApiCharacters()) {
		//						_pilotRoaster.add(pilot);
		//						logger.info("-- [AppModelStore.getPilotRoaster]> Adding " + pilot.getName() + " to the _pilotRoaster");
		//					}
		//				} catch (ApiException apiex) {
		//					apiex.printStackTrace();
		//				}
		//			}
		//		} else {
		//			// Check if the list is valid or we should reload it because it is stale.
		//			DateTime now = DateTime.now();
		//		}
		//
		logger.info("<< [AppModelStore.getPilotRoaster]");
		return _neocomCharacters;
	}

	/**
	 * Stored the keys on the store fields from the persistence store. This code should reconnect pointers to
	 * fields not stored and marked as transient.
	 * 
	 * @param newkeys
	 */
	@Deprecated
	public void setApiKeys(final List<NeoComApiKey> newkeys) {
		_neocomApiKeys = newkeys;
		// we have to reparent the new data because this is not stored on the serialization.
		// Also reinitialize transient fields that are not saved
		//		final Iterator<ApiKey> eit = apiKeys.values().iterator();
		//		while (eit.hasNext()) {
		//			final ApiKey apiKey = eit.next();
		//			//	apiKey.setParent(this);
		//		}
	}

	@Deprecated
	public void setLoginIdentifier(final String login) {
		// OPTIMIZATION If the login is already set to the same value do not update the keys.
		if (_loginIdentifier.equalsIgnoreCase(login))
			return;
		else {
			_loginIdentifier = login;
			// Update the list of api keys and the characters related to the login.
			this.readApiKeys();
		}
	}

	/**
	 * The method gets access to the cached list of characters associated with a set of keys. The keys are
	 * identified by a single and unique login identifier that should be already set on the Model Store. If the
	 * lists are empty the method will access the database and the CCP services to get the information.<br>
	 * Also if the information stored is obsolete the procedure will update with fresh copies.
	 * 
	 * @return
	 */
	private void readApiKeys() {
		AppModelStore.logger.info(">> [AppModelStore.readApiKeys]");
		// Access the database to get the list of keys. From that point on we can retrieve the characters easily.
		List<ApiKey> apiKeys = AppConnector.getDBConnector().getApiList4Login(this.getLoginIdentifier());
		// For each key get the list of characters and instantiate them to the resulting list.
		_neocomApiKeys = new Vector<NeoComApiKey>();
		_neocomCharacters = new Vector<NeoComCharacter>();
		for (ApiKey apiKey : apiKeys) {
			try {
				NeoComApiKey key = NeoComApiKey.build(apiKey.getKeynumber(), apiKey.getValidationcode());
				_neocomApiKeys.add(key);
				// Scan for the characters declared into this key.
				for (NeoComCharacter pilot : key.getApiCharacters()) {
					// Post the request to update the Character.
					AppConnector.getCacheConnector().addCharacterUpdateRequest(pilot.getCharacterID());
					_neocomCharacters.add(pilot);
					logger.info("-- [AppModelStore.readApiKeys]> Adding " + pilot.getName() + " to the _neocomCharacters");
				}
			} catch (ApiException apiex) {
				apiex.printStackTrace();
			}
		}
		AppModelStore.logger.info("<< [AppModelStore.readApiKeys]");
	}

	/**
	 * Search for the specified character id in the list of associated characters list.
	 * 
	 * @param characterID
	 * @return
	 */
	private NeoComCharacter searchCharacter(final long characterID) {
		for (NeoComCharacter character : _neocomCharacters) {
			if (character.getCharacterID() == characterID) return character;
		}
		throw new NeocomRuntimeException("Character with id=" + characterID + " not found on list");
	}
}

// - UNUSED CODE ............................................................................................
