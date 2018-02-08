//	PROJECT:      Neocom.MarketDataService (NEOC-MKDS)
//	AUTHORS:      Adam Antinoo - adamantinoo.git@gmail.com
//	COPYRIGHT:      (c) 2017 by Dimensinfin Industries, all rights reserved.
//	ENVIRONMENT:	SpringBoot-MS-Java 1.8.
//	DESCRIPTION:	This project contains a MicroService specially dedicated to get and store the Market Data
//								information. I should be exposed on a new port and should be accesible to all the backend MS to consult 
//								Item Market Data.
package org.dimensinfin.eveonline.neocom.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;

import org.dimensinfin.eveonline.neocom.constant.ModelWideConstants;
import org.dimensinfin.eveonline.neocom.core.AccessStatistics;
import org.dimensinfin.eveonline.neocom.enums.ELocationType;
import org.dimensinfin.eveonline.neocom.industry.Resource;
import org.dimensinfin.eveonline.neocom.model.EveItem;
import org.dimensinfin.eveonline.neocom.model.EveLocation;
import org.dimensinfin.eveonline.neocom.planetary.Schematics;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

// - CLASS IMPLEMENTATION ...................................................................................
public class CCPDatabaseConnector implements ICCPDatabaseConnector {
	// - S T A T I C - S E C T I O N ..........................................................................
	private static Logger logger = Logger.getLogger("CCPDatabaseConnector");
	private static final String CCPDATABASE_URL = "jdbc:sqlite:src/main/resources/eve.db";
	private static AccessStatistics locationsCacheStatistics = new AccessStatistics();

	// - F I E L D   I N D E X   D E F I N I T I O N S
	private static int STATIONTYPEID_COLINDEX = 1;
	private static int LOCATIONBYID_SYSTEMID_COLINDEX = 5;
	private static int LOCATIONBYID_SYSTEM_COLINDEX = 6;
	private static int LOCATIONBYID_LOCATIONNAME_COLINDEX = 3;
	private static int LOCATIONBYID_CONSTELLATIONID_COLINDEX = 7;
	private static int LOCATIONBYID_CONSTELLATION_COLINDEX = 8;
	private static int LOCATIONBYID_REGIONID_COLINDEX = 9;
	private static int LOCATIONBYID_REGION_COLINDEX = 10;
	private static int LOCATIONBYID_TYPEID_COLINDEX = 2;
	private static int LOCATIONBYID_LOCATIONID_COLINDEX = 1;
	private static int LOCATIONBYID_SECURITY_COLINDEX = 4;
	private static int MODULE4BLUEPRINT_PRODUCTTYPEID_COLINDEX = 1;
	private static int TECH4BLUEPRINT_METAGROUPID_COLINDEX = 3;
	private static int REFINEORE_MATERIALTYPEID_COLINDEX = 1;
	private static int REFINEORE_QUANTITY_COLINDEX = 2;
	private static int REFINEORE_PORTIONSIZE_COLINDEX = 4;
	private static int BLUEPRINT4MODULE_TYPEID_COLINDEX = 1;

	// - S Q L   C O M M A N D S
	private static final String SELECT_ITEM_BYID = "SELECT it.typeID AS typeID, it.typeName AS typeName"
					+ " , ig.groupName AS groupName" + " , ic.categoryName AS categoryName" + " , it.basePrice AS basePrice"
					+ " , it.volume AS volume" + " , IFNULL(img.metaGroupName, " + '"' + "NOTECH" + '"' + ") AS Tech"
					+ " FROM invTypes it" + " LEFT OUTER JOIN invGroups ig ON ig.groupID = it.groupID"
					+ " LEFT OUTER JOIN invCategories ic ON ic.categoryID = ig.categoryID"
					+ " LEFT OUTER JOIN invMetaTypes imt ON imt.typeID = it.typeID"
					+ " LEFT OUTER JOIN invMetaGroups img ON img.metaGroupID = imt.metaGroupID" + " WHERE it.typeID = ?";
	private static final String SELECT_LOCATIONBYID = "SELECT md.itemID AS locationID, md.typeID AS typeID, md.itemName AS locationName, md.security AS security"
					+ " , IFNULL(md.solarSystemID, -1) AS systemID, ms.solarSystemName AS system"
					+ " , IFNULL(md.constellationID, -1) AS constellationID, mc.constellationName AS constellation"
					+ " , IFNULL(md.regionID, -1) AS regionID, mr.regionName AS region"
					+ " FROM mapDenormalize md"
					+ " LEFT OUTER JOIN mapRegions mr ON mr.regionID = md.regionID"
					+ " LEFT OUTER JOIN mapConstellations mc ON mc.constellationID = md.constellationID"
					+ " LEFT OUTER JOIN mapSolarSystems ms ON ms.solarSystemID = md.solarSystemID" + " WHERE itemID = ?";
	private static final String SELECT_LOCATIONBYSYSTEM = "SELECT solarSystemID FROM mapSolarSystems"
					+ " WHERE solarSystemName = ?";
	private static final String SELECT_STATIONTYPE = "SELECT stationTypeID FROM staStations WHERE stationID = ?";
	private static final String SELECT_MODULE4BLUEPRINT = "SELECT productTypeID FROM industryActivityProducts BT"
					+ " WHERE typeID = ? AND activityID = 1";
	private static final String SELECT_TECH4BLUEPRINT = "SELECT iap.typeID, it.typeName, imt.metaGroupID, img.metaGroupName"
					+ " FROM industryActivityProducts iap, invTypes it, invMetaTypes imt, invMetaGroups img"
					+ " WHERE it.typeID =?"
					+ " AND iap.typeID = it.typeID"
					+ " AND imt.typeID = productTypeID"
					+ " AND img.metaGroupID = imt.metaGroupID" + " AND iap.activityID = 1";
	private static final String SELECT_REFININGASTEROID = "SELECT itm.materialTypeID AS materialTypeID, itm.quantity AS qty"
					+ " , it.typeName AS materialName"
					+ " , ito.portionSize AS portionSize"
					+ " FROM invTypeMaterials itm, invTypes it, invTypes ito"
					+ " WHERE itm.typeID = ?"
					+ " AND it.typeID = itm.materialTypeID" + " AND ito.typeID = itm.typeID" + " ORDER BY itm.materialTypeID";
	private static final String SELECT_BLUEPRINT4MODULE = "SELECT typeID FROM industryActivityProducts BT"
					+ " WHERE productTypeID = ? AND activityID = 1";
	private static final String SELECT_RAW_PRODUCTRESULT = "SELECT pstmo.typeID, pstmo.quantity, pstmo.schematicID"
					+ " FROM   planetSchematicsTypeMap pstmi, planetSchematicsTypeMap pstmo" + " WHERE  pstmi.typeID = ?"
					+ " AND    pstmo.schematicID = pstmi.schematicID" + " AND    pstmo.isInput = 0";
	private static final String SELECT_SCHEMATICS4OUTPUT = "SELECT pstms.typeID, pstms.quantity, pstms.isInput"
					+ " FROM   planetSchematicsTypeMap pstmt, planetSchematicsTypeMap pstms" + " WHERE  pstmt.typeID = ?"
					+ " AND    pstmt.isInput = 0" + " AND    pstms.schematicID = pstmt.schematicID";

	// - F I E L D - S E C T I O N ............................................................................
	private Connection ccpDatabase = null;
	private final Hashtable<Integer, EveItem> itemCache = new Hashtable<Integer, EveItem>();
	private final Hashtable<Long, EveLocation> locationsCache = new Hashtable<Long, EveLocation>(200);

	// - C O N S T R U C T O R - S E C T I O N ................................................................
	public CCPDatabaseConnector () {
	}

	// - M E T H O D - S E C T I O N ..........................................................................
	@Override
	public boolean openCCPDataBase () {
		if (null == ccpDatabase) {
			try {
				Class.forName("org.sqlite.JDBC");
				ccpDatabase = DriverManager.getConnection(CCPDatabaseConnector.CCPDATABASE_URL);
				ccpDatabase.setAutoCommit(false);
			} catch (Exception sqle) {
				CCPDatabaseConnector.logger.warning(sqle.getClass().getName() + ": " + sqle.getMessage());
			}
			CCPDatabaseConnector.logger
							.info("-- [StringDatabaseConnector.openCCPDataBase]> Opened CCP database successfully.");
		}
		return true;
	}

	@Override
	public ArrayList<Resource> refineOre ( final int oreID ) {
		ArrayList<Resource> results = new ArrayList<Resource>();
		PreparedStatement prepStmt = null;
		ResultSet cursor = null;
		try {
			prepStmt = this.getCCPDatabase().prepareStatement(SELECT_REFININGASTEROID);
			prepStmt.setString(1, Integer.valueOf(oreID).toString());
			cursor = prepStmt.executeQuery();
			if (null != cursor) {
				while (cursor.next()) {
					int resourceID = cursor.getInt(REFINEORE_MATERIALTYPEID_COLINDEX);
					int qty = cursor.getInt(REFINEORE_QUANTITY_COLINDEX);
					int portionSize = cursor.getInt(REFINEORE_PORTIONSIZE_COLINDEX);
					Resource resource = new Resource(resourceID, qty);
					resource.setStackSize(portionSize);
					results.add(resource);
				}
				return results;
			}
		} catch (final Exception ex) {
			logger.severe("E> Error refining <" + oreID + "> not found.");
		} finally {
			try {
				if (null != cursor) {
					cursor.close();
				}
				if (null != prepStmt) {
					prepStmt.close();
				}
			} catch (SQLException ex) {
			}
		}
		return results;
	}

	/**
	 * Returns the blueprint id that matched this module from the <code>invBlueprintTypes</code> table.
	 */
	@Override
	public int searchBlueprint4Module ( final int moduleID ) {
		PreparedStatement prepStmt = null;
		ResultSet cursor = null;
		try {
			prepStmt = getCCPDatabase().prepareStatement(SELECT_BLUEPRINT4MODULE);
			prepStmt.setString(1, Integer.valueOf(moduleID).toString());
			cursor = prepStmt.executeQuery();
			if (null != cursor) {
				int productTypeID = -1;
				while (cursor.next()) {
					productTypeID = cursor.getInt(BLUEPRINT4MODULE_TYPEID_COLINDEX);
				}
				return productTypeID;
			}
		} catch (final Exception ex) {
			logger.severe("E> Error locating BPO for module <" + moduleID + ">.");
		} finally {
			try {
				if (null != cursor) {
					cursor.close();
				}
				if (null != prepStmt) {
					prepStmt.close();
				}
			} catch (SQLException ex) {
			}
		}
		return -1;
	}

//	/**
//	 * Search on the eve.db database for the attributes that describe an Item. Items are the lowest data
//	 * structure for EVE resources or modules. Everything on Eve is an Item. We detect blueprints that require a
//	 * different treatment and also we check for the availability of the item at the current cache if
//	 * implemented.
//	 */
//	@Override
//	public synchronized EveItem searchItembyID ( final int typeID ) {
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
//				prepStmt = this.getCCPDatabase().prepareStatement(CCPDatabaseConnector.SELECT_ITEM_BYID);
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
//					CCPDatabaseConnector.logger.warning("W> AndroidDatabaseConnector.searchItembyID -- Item <" + typeID
//									+ "> not found.");
//				}
//			} catch (Exception e) {
//				CCPDatabaseConnector.logger.warning("W> AndroidDatabaseConnector.searchItembyID -- Item <" + typeID
//								+ "> not found.");
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
	public EveLocation searchLocationbyID ( final long locationID ) {
		CCPDatabaseConnector.logger.info(">< [CCPDatabaseConnector.searchLocationbyID]> Searching ID: " + locationID);
		// First check if the location is already on the cache table.
		EveLocation hit = locationsCache.get(locationID);
		if (null != hit) {
			int access = CCPDatabaseConnector.locationsCacheStatistics.accountAccess(true);
			int hits = CCPDatabaseConnector.locationsCacheStatistics.getHits();
			CCPDatabaseConnector.logger.info(">< [CCPDatabaseConnector.searchLocationbyID]> [HIT-" + hits + "/" + access
							+ "] Location " + locationID + " found at cache.");
			return hit;
		} else {
			// Try to get that id from the cache tables
			int access = CCPDatabaseConnector.locationsCacheStatistics.accountAccess(false);
			List<EveLocation> locationList = null;
			try {
				Dao<EveLocation, String> locationDao = ModelAppConnector.getSingleton().getDBConnector().getLocationDAO();
				QueryBuilder<EveLocation, String> queryBuilder = locationDao.queryBuilder();
				Where<EveLocation, String> where = queryBuilder.where();
				where.eq("id", locationID);
				PreparedQuery<EveLocation> preparedQuery = queryBuilder.prepare();
				locationList = locationDao.query(preparedQuery);
			} catch (java.sql.SQLException sqle) {
				sqle.printStackTrace();
				return new EveLocation(locationID);
			}

			// Check list contents. If found we have the location. Else then check if Office
			if (locationList.size() < 1) {
				//				CCPDatabaseConnector.logger.info(
				//						"-- [CCPDatabaseConnector.searchLocationbyID]> Location: " + locationID + " not found on local Database.");
				// Offices
				long fixedLocationID = locationID;
				//				if (fixedLocationID >= 66000000) {
				//					if (fixedLocationID < 66014933) {
				//						fixedLocationID = fixedLocationID - 6000001;
				//					} else {
				//						fixedLocationID = fixedLocationID - 6000000;
				//					}
				//				}
				hit = new EveLocation(fixedLocationID);
				ResultSet cursor = null;
				try {
					PreparedStatement prepStmt = this.getCCPDatabase().prepareStatement(CCPDatabaseConnector.SELECT_LOCATIONBYID);
					prepStmt.setString(1, Long.valueOf(fixedLocationID).toString());
					cursor = prepStmt.executeQuery();
					if (null != cursor) {
						boolean detected = false;
						if (cursor.next()) {
							detected = true;
							//							CCPDatabaseConnector.logger.info(
							//									"-- [CCPDatabaseConnector.searchLocationbyID]> Location: " + locationID + " Obtained from CCP data.");
							// Check returned values when doing the assignments.
							long fragmentID = cursor.getLong(CCPDatabaseConnector.LOCATIONBYID_SYSTEMID_COLINDEX);
							if (fragmentID > 0) {
								hit.setSystemID(fragmentID);
								hit.setSystem(cursor.getString(CCPDatabaseConnector.LOCATIONBYID_SYSTEM_COLINDEX));
							} else {
								hit.setSystem(cursor.getString(CCPDatabaseConnector.LOCATIONBYID_LOCATIONNAME_COLINDEX));
							}
							fragmentID = cursor.getLong(CCPDatabaseConnector.LOCATIONBYID_CONSTELLATIONID_COLINDEX);
							if (fragmentID > 0) {
								hit.setConstellationID(fragmentID);
								hit.setConstellation(cursor.getString(CCPDatabaseConnector.LOCATIONBYID_CONSTELLATION_COLINDEX));
							}
							fragmentID = cursor.getLong(CCPDatabaseConnector.LOCATIONBYID_REGIONID_COLINDEX);
							if (fragmentID > 0) {
								hit.setRegionID(fragmentID);
								hit.setRegion(cursor.getString(CCPDatabaseConnector.LOCATIONBYID_REGION_COLINDEX));
							}
							hit.setTypeID(ELocationType.CCPLOCATION);
							hit.setStation(cursor.getString(CCPDatabaseConnector.LOCATIONBYID_LOCATIONNAME_COLINDEX));
							hit.setLocationID(cursor.getLong(CCPDatabaseConnector.LOCATIONBYID_LOCATIONID_COLINDEX));
							hit.setSecurity(cursor.getString(CCPDatabaseConnector.LOCATIONBYID_SECURITY_COLINDEX));
							// Update the final ID
							hit.getID();

							// Location found on CCP database.
							int hits = CCPDatabaseConnector.locationsCacheStatistics.getHits();
							CCPDatabaseConnector.logger.info(">< [CCPDatabaseConnector.searchLocationbyID]> [HIT-" + hits + "/"
											+ access + "] Location " + locationID + " found at CCP Database.");
							locationsCache.put(hit.getID(), hit);
						}
						if (!detected) {
							CCPDatabaseConnector.logger.info("-- [searchLocationbyID]> Location: " + locationID
											+ " not found on any Database - UNKNOWN-.");
							hit.setSystem("ID>" + Long.valueOf(locationID).toString());
						}
					}
				} catch (final Exception ex) {
					CCPDatabaseConnector.logger.warning("W- [AndroidDatabaseConnector.searchLocationbyID]> Location <"
									+ fixedLocationID + "> not found.");
				} finally {
					try {
						cursor.close();
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
				// If the location is not cached nor in the CCP database. Return default location
				return hit;
			} else {
				// Location found on the Application database.
				int hits = CCPDatabaseConnector.locationsCacheStatistics.getHits();
				CCPDatabaseConnector.logger.info(">< [CCPDatabaseConnector.searchLocationbyID]> [HIT-" + hits + "/" + access
								+ "] Location " + locationID + " found at Application Database.");
				EveLocation foundLoc = locationList.get(0);
				locationsCache.put(foundLoc.getID(), foundLoc);
				return locationList.get(0);
			}
		}
	}

	@Override
	public EveLocation searchLocationBySystem ( final String name ) {
		EveLocation hit = new EveLocation();
		PreparedStatement prepStmt = null;
		ResultSet cursor = null;
		try {
			prepStmt = this.getCCPDatabase().prepareStatement(CCPDatabaseConnector.SELECT_LOCATIONBYSYSTEM);
			prepStmt.setString(1, name);
			cursor = prepStmt.executeQuery();
			boolean detected = false;
			while (cursor.next()) {
				detected = true;
				// Check returned values when doing the assignments.
				long fragmentID = cursor.getInt(1);
				if (fragmentID > 0) {
					hit.setSystemID(fragmentID);
				}
				detected = true;
			}
		} catch (final Exception ex) {
			CCPDatabaseConnector.logger.warning("Location <" + name + "> not found.");
		} finally {
			try {
				if (null != cursor) {
					cursor.close();
				}
				if (null != prepStmt) {
					prepStmt.close();
				}
			} catch (SQLException ex) {
			}
		}
		return this.searchLocationbyID(hit.getSystemID());
	}

	@Override
	public int searchModule4Blueprint ( final int bpitemID ) {
		PreparedStatement prepStmt = null;
		ResultSet cursor = null;
		try {
			prepStmt = this.getCCPDatabase().prepareStatement(CCPDatabaseConnector.SELECT_MODULE4BLUEPRINT);
			prepStmt.setString(1, Integer.valueOf(bpitemID).toString());
			cursor = prepStmt.executeQuery();
			if (null != cursor) {
				int productTypeID = -1;
				while (cursor.next()) {
					productTypeID = cursor.getInt(CCPDatabaseConnector.MODULE4BLUEPRINT_PRODUCTTYPEID_COLINDEX);
				}
				return productTypeID;
			}
		} catch (final Exception ex) {
			CCPDatabaseConnector.logger.severe("E> Error locating module for BPO <" + bpitemID + ">.");
		} finally {
			try {
				if (null != cursor) {
					cursor.close();
				}
				if (null != prepStmt) {
					prepStmt.close();
				}
			} catch (SQLException ex) {
			}
		}
		return -1;
	}

	@Override
	public int searchRawPlanetaryOutput ( final int typeID ) {
		int outputResourceId = typeID;
		PreparedStatement prepStmt = null;
		ResultSet cursor = null;
		try {
			prepStmt = getCCPDatabase().prepareStatement(SELECT_RAW_PRODUCTRESULT);
			prepStmt.setString(1, Integer.valueOf(typeID).toString());
			cursor = prepStmt.executeQuery();
			while (cursor.next()) {
				outputResourceId = cursor.getInt(1);
			}
		} catch (Exception ex) {
			logger.warning("W- [SpingDatabaseConnector.searchRawPlanetaryOutput]> Database exception: " + ex.getMessage());
		} finally {
			try {
				if (cursor != null) cursor.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			try {
				if (prepStmt != null) prepStmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return outputResourceId;
	}

	@Override
	public Vector<Schematics> searchSchematics4Output ( final int targetId ) {
		Vector<Schematics> scheList = new Vector<Schematics>();
		PreparedStatement prepStmt = null;
		ResultSet cursor = null;
		try {
			prepStmt = getCCPDatabase().prepareStatement(SELECT_SCHEMATICS4OUTPUT);
			prepStmt.setString(1, Integer.valueOf(targetId).toString());
			cursor = prepStmt.executeQuery();
			while (cursor.next()) {
				scheList.add(new Schematics().addData(cursor.getInt(1), cursor.getInt(2), cursor.getBoolean(3)));
			}
		} catch (Exception ex) {
			logger.warning("W- [SpingDatabaseConnector.searchRawPlanetaryOutput]> Database exception: " + ex.getMessage());
		} finally {
			try {
				if (cursor != null) cursor.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			try {
				if (prepStmt != null) prepStmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return scheList;
	}

	/**
	 * Returns the resource identifier of the station class to locate icons or other type related resources.
	 * 
	 * @param stationID
	 * @return
	 */
	@Override
	public int searchStationType ( final long stationID ) {
		int stationTypeID = 1529;
		ModelAppConnector.getSingleton().startChrono();
		PreparedStatement prepStmt = null;
		ResultSet cursor = null;
		try {
			prepStmt = this.getCCPDatabase().prepareStatement(CCPDatabaseConnector.SELECT_STATIONTYPE);
			prepStmt.setString(1, Long.valueOf(stationID).toString());
			cursor = prepStmt.executeQuery();
			while (cursor.next()) {
				stationTypeID = cursor.getInt(CCPDatabaseConnector.STATIONTYPEID_COLINDEX);
			}
		} catch (Exception ex) {
			CCPDatabaseConnector.logger.warning("W- [SpingDatabaseConnector.searchStationType]> Database exception: "
							+ ex.getMessage());
		} finally {
			try {
				if (cursor != null) {
					cursor.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			try {
				if (prepStmt != null) {
					prepStmt.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		//	logger.info("~~ Time lapse for [SELECT STATIONTYPEID " + stationID + "] " + AppConnector.timeLapse());
		return stationTypeID;
	}

	@Override
	public String searchTech4Blueprint ( final int blueprintID ) {
		PreparedStatement prepStmt = null;
		ResultSet cursor = null;
		try {
			prepStmt = getCCPDatabase().prepareStatement(SELECT_TECH4BLUEPRINT);
			prepStmt.setString(1, Integer.valueOf(blueprintID).toString());
			cursor = prepStmt.executeQuery();
			if (null != cursor) {
				String productTypeID = ModelWideConstants.eveglobal.TechI;
				while (cursor.next()) {
					productTypeID = cursor.getString(TECH4BLUEPRINT_METAGROUPID_COLINDEX);
				}
				return productTypeID;
			}
		} catch (final Exception ex) {
			logger.severe("E> Error locating BPO for module <" + blueprintID + ">.");
		} finally {
			try {
				if (null != cursor) {
					cursor.close();
				}
				if (null != prepStmt) {
					prepStmt.close();
				}
			} catch (SQLException ex) {
			}
		}
		return ModelWideConstants.eveglobal.TechI;
	}

	private Connection getCCPDatabase () {
		if (null == ccpDatabase) {
			this.openCCPDataBase();
		}
		return ccpDatabase;
	}

}
// - UNUSED CODE ............................................................................................
