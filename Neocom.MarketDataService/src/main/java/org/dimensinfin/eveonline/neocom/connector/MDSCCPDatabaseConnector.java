//	PROJECT:      Neocom.MarketDataService (NEOC-MKDS)
//	AUTHORS:      Adam Antinoo - adamantinoo.git@gmail.com
//	COPYRIGHT:      (c) 2017 by Dimensinfin Industries, all rights reserved.
//	ENVIRONMENT:	SpringBoot-MS-Java 1.8.
//	DESCRIPTION:	This project contains a MicroService specially dedicated to get and store the Market Data
//								information. I should be exposed on a new port and should be accesible to all the backend MS to consult 
//								Item Market Data.
package org.dimensinfin.eveonline.neocom.connector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.dimensinfin.eveonline.neocom.enums.ELocationType;
import org.dimensinfin.eveonline.neocom.model.EveLocation;

// - CLASS IMPLEMENTATION ...................................................................................
public class MDSCCPDatabaseConnector extends CCPDatabaseConnector implements ICCPDatabaseConnector {
	// - S T A T I C - S E C T I O N ..........................................................................
	private static Logger				logger																= Logger.getLogger("MDSCCPDatabaseConnector");

	// - F I E L D   I N D E X   D E F I N I T I O N S
	//	private static int													STATIONTYPEID_COLINDEX								= 1;
	private static int					LOCATIONBYID_SYSTEMID_CONINDEX				= 5;
	private static int					LOCATIONBYID_SYSTEM_CONINDEX					= 6;
	private static int					LOCATIONBYID_LOCATIONNAME_CONINDEX		= 3;
	private static int					LOCATIONBYID_CONSTELLATIONID_CONINDEX	= 7;
	private static int					LOCATIONBYID_CONSTELLATION_CONINDEX		= 8;
	private static int					LOCATIONBYID_REGIONID_CONINDEX				= 9;
	private static int					LOCATIONBYID_REGION_CONINDEX					= 10;
	private static int					LOCATIONBYID_TYPEID_CONINDEX					= 2;
	private static int					LOCATIONBYID_LOCATIONID_CONINDEX			= 1;
	private static int					LOCATIONBYID_SECURITY_CONINDEX				= 4;

	// - S Q L   C O M M A N D S
	//	private static final String									SELECT_ITEM_BYID											= "SELECT it.typeID AS typeID, it.typeName AS typeName"
	//			+ " , ig.groupName AS groupName" + " , ic.categoryName AS categoryName" + " , it.basePrice AS basePrice"
	//			+ " , it.volume AS volume" + " , IFNULL(img.metaGroupName, " + '"' + "NOTECH" + '"' + ") AS Tech"
	//			+ " FROM invTypes it" + " LEFT OUTER JOIN invGroups ig ON ig.groupID = it.groupID"
	//			+ " LEFT OUTER JOIN invCategories ic ON ic.categoryID = ig.categoryID"
	//			+ " LEFT OUTER JOIN invMetaTypes imt ON imt.typeID = it.typeID"
	//			+ " LEFT OUTER JOIN invMetaGroups img ON img.metaGroupID = imt.metaGroupID" + " WHERE it.typeID = ?";
	private static final String	SELECT_LOCATIONBYID										= "SELECT md.itemID AS locationID, md.typeID AS typeID, md.itemName AS locationName, md.security AS security"
			+ " , IFNULL(md.solarSystemID, -1) AS systemID, ms.solarSystemName AS system"
			+ " , IFNULL(md.constellationID, -1) AS constellationID, mc.constellationName AS constellation"
			+ " , IFNULL(md.regionID, -1) AS regionID, mr.regionName AS region" + " FROM mapDenormalize md"
			+ " LEFT OUTER JOIN mapRegions mr ON mr.regionID = md.regionID"
			+ " LEFT OUTER JOIN mapConstellations mc ON mc.constellationID = md.constellationID"
			+ " LEFT OUTER JOIN mapSolarSystems ms ON ms.solarSystemID = md.solarSystemID" + " WHERE itemID = ?";
	//	private static final String									SELECT_LOCATIONBYSYSTEM								= "SELECT solarSystemID from mapSolarSystems WHERE solarSystemName = ?";
	//	private static final String									STATIONTYPE														= "SELECT stationTypeID FROM staStations WHERE stationID = ?";

	// - F I E L D - S E C T I O N ............................................................................
	//	private Connection													ccpDatabase														= null;
	//	private final Hashtable<Integer, EveItem>		itemCache															= new Hashtable<Integer, EveItem>();
	//	private final Hashtable<Long, EveLocation>	locationsCache												= new Hashtable<Long, EveLocation>(
	//			200);

	// - C O N S T R U C T O R - S E C T I O N ................................................................
	//	public MDSCCPDatabaseConnector() {
	//	}

	// - M E T H O D - S E C T I O N ..........................................................................
	//	@Override
	//	public boolean openCCPDataBase() {
	//		if (null == ccpDatabase) {
	//			try {
	//				Class.forName("org.sqlite.JDBC");
	//				ccpDatabase = DriverManager.getConnection(CCPDATABASE_URL);
	//				ccpDatabase.setAutoCommit(false);
	//			} catch (Exception sqle) {
	//				logger.warning(sqle.getClass().getName() + ": " + sqle.getMessage());
	//			}
	//			logger.info("-- [StringDatabaseConnector.openCCPDataBase]> Opened CCP database successfully.");
	//		}
	//		return true;
	//	}
	//
	//	/**
	//	 * Search on the eve.db database for the attributes that describe an Item. Items are the lowest data
	//	 * structure for EVE resources or modules. Everything on Eve is an Item. We detect blueprints that require a
	//	 * different treatment and also we check for the availability of the item at the current cache if
	//	 * implemented.
	//	 */
	//	@Override
	//	public synchronized EveItem searchItembyID(final int typeID) {
	//		// Search the item on the cache.
	//		EveItem hit = itemCache.get(typeID);
	//		if (null == hit) {
	//			PreparedStatement prepStmt = null;
	//			ResultSet cursor = null;
	//			try {
	//				hit = new EveItem();
	//				//			final Cursor cursor = getCCPDatabase().rawQuery(SELECT_ITEM_BYID,
	//				//					new String[] { Integer.valueOf(typeID).toString() });
	//				//	      Statement stmt = getCCPDatabase().createStatement();
	//				prepStmt = this.getCCPDatabase().prepareStatement(SELECT_ITEM_BYID);
	//				prepStmt.setString(1, Integer.valueOf(typeID).toString());
	//				cursor = prepStmt.executeQuery();
	//				// The query can be run but now there are ids that do not return data.
	//				boolean found = false;
	//				while (cursor.next()) {
	//					found = true;
	//					hit.setTypeID(cursor.getInt(1));
	//					hit.setName(cursor.getString(2));
	//					hit.setGroupname(cursor.getString(3));
	//					hit.setCategory(cursor.getString(4));
	//					hit.setBasePrice(cursor.getDouble(5));
	//					hit.setVolume(cursor.getDouble(6));
	//					// Process the Tech field. The query marks blueprints
	//					String tech = cursor.getString(7);
	//					if (tech.equalsIgnoreCase("NOTECH")) {
	//						// Double check it is a Blueprint
	//						hit.setTech(ModelWideConstants.eveglobal.TechI);
	//						if (hit.getName().contains(" II Blueprint")) {
	//							hit.setBlueprint(true);
	//							if (hit.getName().contains(" II Blueprint")) {
	//								hit.setTech(ModelWideConstants.eveglobal.TechII);
	//							}
	//							if (hit.getName().contains(" III Blueprint")) {
	//								hit.setTech(ModelWideConstants.eveglobal.TechIII);
	//							}
	//						}
	//					} else {
	//						hit.setTech(tech);
	//					}
	//				}
	//				if (!found) {
	//					logger.warning("W> AndroidDatabaseConnector.searchItembyID -- Item <" + typeID + "> not found.");
	//				}
	//			} catch (Exception e) {
	//				logger.warning("W> AndroidDatabaseConnector.searchItembyID -- Item <" + typeID + "> not found.");
	//				return new EveItem();
	//			} finally {
	//				try {
	//					if (cursor != null) {
	//						cursor.close();
	//					}
	//				} catch (SQLException e) {
	//					e.printStackTrace();
	//				}
	//				try {
	//					if (prepStmt != null) {
	//						prepStmt.close();
	//					}
	//				} catch (SQLException e) {
	//					e.printStackTrace();
	//				}
	//			}
	//			itemCache.put(new Integer(typeID), hit);
	//		}
	//		return hit;
	//	}

	/**
	 * Search on the CCP Database or on the Application database for a new location ID that is not already on
	 * the cache. Locations are extended objects on the NeoCom model because to the standard and game defined
	 * locations we should add an external resource with the compilation of user deployed structures (Citadels,
	 * Refineries, etc) that can also store assets and that are becoming the real place where to have the items.
	 * We also have to include another external source that are the Outposts than come fom CCP sources but that
	 * are being slowly phased out.<br>
	 * The process starts searching for locations depending on range, first at the CCP database and then at the
	 * Locations table at the application database.
	 */
	@Override
	public EveLocation searchLocationbyID(final long locationID) {
		logger.info(">< [MDSCCPDatabaseConnector.searchLocationbyID]> Searching ID: " + locationID);
		// First check if the location is already on the cache table.
		EveLocation hit = locationsCache.get(locationID);
		if (null != hit) {
			int access = locationsCacheStatistics.accountAccess(true);
			int hits = locationsCacheStatistics.getHits();
			logger.info(">< [MDSCCPDatabaseConnector.searchLocationbyID]> [HIT-" + hits + "/" + access + "] Location "
					+ locationID + " found at cache.");
			return hit;
		} else {
			// Try to get that id from the cache tables
			int access = locationsCacheStatistics.accountAccess(false);
			List<EveLocation> locationList = new ArrayList();
			//			try {
			//				Dao<EveLocation, String> locationDao = ModelAppConnector.getSingleton().getDBConnector().getLocationDAO();
			//				QueryBuilder<EveLocation, String> queryBuilder = locationDao.queryBuilder();
			//				Where<EveLocation, String> where = queryBuilder.where();
			//				where.eq("id", locationID);
			//				PreparedQuery<EveLocation> preparedQuery = queryBuilder.prepare();
			//				locationList = locationDao.query(preparedQuery);
			//			} catch (java.sql.SQLException sqle) {
			//				sqle.printStackTrace();
			//				return new EveLocation(locationID);
			//			}

			// Check list contents. If found we have the location. Else then check if Office
			if (locationList.size() < 1) {
				//				 logger.info(
				//						"-- [ searchLocationbyID]> Location: " + locationID + " not found on local Database.");
				// Offices
				long fixedLocationID = locationID;
				if (fixedLocationID >= 66000000) {
					if (fixedLocationID < 66014933) {
						fixedLocationID = fixedLocationID - 6000001;
					} else {
						fixedLocationID = fixedLocationID - 6000000;
					}
				}
				hit = new EveLocation(fixedLocationID);
				ResultSet cursor = null;
				try {
					PreparedStatement prepStmt = this.getCCPDatabase().prepareStatement(SELECT_LOCATIONBYID);
					prepStmt.setString(1, Long.valueOf(fixedLocationID).toString());
					cursor = prepStmt.executeQuery();
					if (null != cursor) {
						boolean detected = false;
						if (cursor.next()) {
							detected = true;
							//							 logger.info(
							//									"-- [ searchLocationbyID]> Location: " + locationID + " Obtained from CCP data.");
							// Check returned values when doing the assignments.
							long fragmentID = cursor.getLong(LOCATIONBYID_SYSTEMID_CONINDEX);
							if (fragmentID > 0) {
								hit.setSystemID(fragmentID);
								hit.setSystem(cursor.getString(LOCATIONBYID_SYSTEM_CONINDEX));
							} else {
								hit.setSystem(cursor.getString(LOCATIONBYID_LOCATIONNAME_CONINDEX));
							}
							fragmentID = cursor.getLong(LOCATIONBYID_CONSTELLATIONID_CONINDEX);
							if (fragmentID > 0) {
								hit.setConstellationID(fragmentID);
								hit.setConstellation(cursor.getString(LOCATIONBYID_CONSTELLATION_CONINDEX));
							}
							fragmentID = cursor.getLong(LOCATIONBYID_REGIONID_CONINDEX);
							if (fragmentID > 0) {
								hit.setRegionID(fragmentID);
								hit.setRegion(cursor.getString(LOCATIONBYID_REGION_CONINDEX));
							}
							hit.setTypeID(ELocationType.CCPLOCATION);
							hit.setStation(cursor.getString(LOCATIONBYID_LOCATIONNAME_CONINDEX));
							hit.setLocationID(cursor.getLong(LOCATIONBYID_LOCATIONID_CONINDEX));
							hit.setSecurity(cursor.getString(LOCATIONBYID_SECURITY_CONINDEX));
							// Update the final ID
							hit.getID();

							// Location found on CCP database.
							int hits = locationsCacheStatistics.getHits();
							logger.info(">< [MDSCCPDatabaseConnector.searchLocationbyID]> [HIT-" + hits + "/" + access + "] Location "
									+ locationID + " found at CCP Database.");
							locationsCache.put(hit.getID(), hit);
						}
						if (!detected) {
							logger.info("-- [MDSCCPDatabaseConnector.searchLocationbyID]> Location: " + locationID
									+ " not found on any Database - UNKNOWN-.");
							hit.setSystem("ID>" + Long.valueOf(locationID).toString());
						}
					}
				} catch (final Exception ex) {
					logger.warning(
							"W- [MDSCCPDatabaseConnector.searchLocationbyID]> Location <" + fixedLocationID + "> not found.");
				} finally {
					try {
						cursor.close();
					} catch (SQLException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}
				}
				// If the location is not cached nor in the CCP database. Return default location
				return hit;
			} else {
				// Location found on the Application database.
				int hits = locationsCacheStatistics.getHits();
				logger.info(">< [MDSCCPDatabaseConnector.searchLocationbyID]> [HIT-" + hits + "/" + access + "] Location "
						+ locationID + " found at Application Database.");
				EveLocation foundLoc = locationList.get(0);
				locationsCache.put(foundLoc.getID(), foundLoc);
				return locationList.get(0);
			}
		}
	}
	//
	//	@Override
	//	public EveLocation searchLocationBySystem(final String name) {
	//		EveLocation hit = new EveLocation();
	//		PreparedStatement prepStmt = null;
	//		ResultSet cursor = null;
	//		try {
	//			prepStmt = this.getCCPDatabase().prepareStatement(SELECT_LOCATIONBYSYSTEM);
	//			prepStmt.setString(1, name);
	//			cursor = prepStmt.executeQuery();
	//			boolean detected = false;
	//			while (cursor.next()) {
	//				//				if (cursor.moveToFirst()) {
	//				detected = true;
	//				// Check returned values when doing the assignments.
	//				long fragmentID = cursor.getInt(1);
	//				if (fragmentID > 0) {
	//					hit.setSystemID(fragmentID);
	//					//					hit.setSystem(cursor.getString(6));
	//					//				} else {
	//					//					hit.setSystem(cursor.getString(3));
	//				}
	//				//				fragmentID = cursor.getLong(7);
	//				//				if (fragmentID > 0) {
	//				//					hit.setConstellationID(fragmentID);
	//				//					hit.setConstellation(cursor.getString(8));
	//				//				}
	//				//				fragmentID = cursor.getLong(9);
	//				//				if (fragmentID > 0) {
	//				//					hit.setRegionID(fragmentID);
	//				//					hit.setRegion(cursor.getString(10));
	//				//				}
	//				//				hit.setTypeID(cursor.getInt(2));
	//				//				hit.setStation(cursor.getString(3));
	//				//				hit.setLocationID(cursor.getLong(1));
	//				//				hit.setSecurity(cursor.getString(4));
	//				//				// Update the final ID
	//				//				hit.getID();
	//				detected = true;
	//			}
	//			//			if (!detected) // Search the location on the list of outposts.
	//			//				hit = searchOutpostbyID(locationID);
	//			//	}
	//		} catch (final Exception ex) {
	//			logger.warning("Location <" + name + "> not found.");
	//		}
	//		return this.searchLocationbyID(hit.getSystemID());
	//	}
	//
	//	@Override
	//	public int searchModule4Blueprint(final int bpitemID) {
	//		// TODO Auto-generated method stub
	//		return 0;
	//	}
	//
	//	/**
	//	 * Returns the resource identifier of the station class to locate icons or other type related resources.
	//	 * 
	//	 * @param stationID
	//	 * @return
	//	 */
	//	@Override
	//	public int searchStationType(final long stationID) {
	//		int stationTypeID = 1529;
	//		ModelAppConnector.getSingleton().startChrono();
	//		PreparedStatement prepStmt = null;
	//		ResultSet cursor = null;
	//		try {
	//			prepStmt = this.getCCPDatabase().prepareStatement(STATIONTYPE);
	//			prepStmt.setString(1, Long.valueOf(stationID).toString());
	//			cursor = prepStmt.executeQuery();
	//			while (cursor.next()) {
	//				stationTypeID = cursor.getInt(STATIONTYPEID_COLINDEX);
	//			}
	//		} catch (Exception ex) {
	//			logger.warning("W- [SpingDatabaseConnector.searchStationType]> Database exception: " + ex.getMessage());
	//		} finally {
	//			try {
	//				if (cursor != null) {
	//					cursor.close();
	//				}
	//			} catch (SQLException ex) {
	//				ex.printStackTrace();
	//			}
	//			try {
	//				if (prepStmt != null) {
	//					prepStmt.close();
	//				}
	//			} catch (SQLException ex) {
	//				ex.printStackTrace();
	//			}
	//		}
	//		//	logger.info("~~ Time lapse for [SELECT STATIONTYPEID " + stationID + "] " + AppConnector.timeLapse());
	//		return stationTypeID;
	//	}
	//
	//	private Connection getCCPDatabase() {
	//		if (null == ccpDatabase) {
	//			this.openCCPDataBase();
	//		}
	//		return ccpDatabase;
	//	}

}
// - UNUSED CODE ............................................................................................
