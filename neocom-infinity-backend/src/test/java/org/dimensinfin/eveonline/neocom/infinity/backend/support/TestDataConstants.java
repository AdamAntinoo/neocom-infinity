package org.dimensinfin.eveonline.neocom.infinity.backend.support;

import org.joda.time.DateTime;

public class TestDataConstants {
	public static class PilotConstants {
		public static final Integer TEST_PILOT_ID = 93813310;
		public static final String TEST_PILOT_NAME = "-TEST_PILOT_NAME-";
		public static final String TEST_PILOT_DESCRIPTION = "-DESCRIPTION-";
		public static final Integer TEST_PILOT_CORPORATION_ID = 98384726;
		public static final Integer TEST_CORPORATION_CEO_ID = 91734031;
		public static final Integer TEST_PILOT_FACTION_ID = 32;
		public static final Integer TEST_PILOT_RACE_ID = 4;
		public static final Integer TEST_PILOT_ANCESTRY_ID = 5;
		public static final Integer TEST_PILOT_BLOODLINE_ID = 6;
		public static final DateTime TEST_PILOT_BIRTHDATE = DateTime.parse( "2012-07-05T21:53:15.000Z" );
		public static final Integer TEST_PILOT_LOCATION_SYSTEM_ID = 54323456;
		public static final Float TEST_PILOT_SECURITY_STATUS = 0.1F;
		public static final String TEST_PILOT_TITLE = "-TITLE-";
		public static final Long TEST_PILOT_SKILL_POINTS = 1436765L;
		public static final Double TEST_PILOT_WALLET_BALANCE = 432567.0;
	}

	public static class FittingConstants {
		public static final Integer TEST_FITTING_ID = 60320161;
		public static final Integer TEST_FITTING_SHIP_TYPE_ID = 30668;
		public static final String TEST_FITTING_NAME = "-TEST_FITTING_NAME-";
		public static final String TEST_FITTING_DESCRIPTION = "-TEST_FITTING_DESCRIPTION-";
	}

	public static class FittingItemConstants {
		public static final Integer TEST_FITTING_ITEM_TYPE_ID = 246;
		public static final Integer TEST_FITTING_ITEM_QUANTITY = 3;
	}

	public static class EsiItemModelConstants {
		public static final Integer TEST_ESI_ITEM_MODEL_TYPE_ID = 246;
		public static final String TEST_ESI_ITEM_GROUP_NAME = "Interceptor";
		public static final String TEST_ESI_ITEM_CATEGORY_NAME = "Ship";
		public static final String TEST_ESI_ITEM_HULLGROUP_NAME = "frigate";
	}

	public static final class MarketDataConstants {
		public static final Double TEST_MARKET_DATA_BEST_SELL_PRICE = 345.98;
		public static final Double TEST_MARKET_DATA_BEST_BUY_PRICE = 164.09;
	}

	public static final class MarketOrderConstants {
		public static final Long TEST_MARKET_ORDER_ID = 4275798L;
		public static final Integer TEST_MARKET_ORDER_TYPE_ID = 16636;
		public static final Integer TEST_MARKET_ORDER_SYSTEM_ID = 60003760;
		public static final Double TEST_MARKET_ORDER_PRICE = 345.98;
		public static final Integer TEST_MARKET_ORDER_VOLUME_REMAIN = 100;
		public static final Integer TEST_MARKET_ORDER_VOLUME_TOTAL = 200;
	}

	public static class ProcessedBlueprintConstants {
		public static final Integer TEST_PROCESSED_BLUEPRINT_ID = 31717;
	}

	public static class ProcessedBlueprintSummaryConstants {
		public static final String TEST_PROCESSED_BLUEPRINT_UID = "BCI:93813310:31359";
		public static final Double TEST_PROCESSED_BLUEPRINT_MANUFACTURE_COST = 538910.0;
		public static final Double TEST_PROCESSED_BLUEPRINT_COST_INDEX = 0.959937651927038;
		public static final String TEST_PROCESSED_BLUEPRINT_BLUEPRINT_TYPE_ICON_URL = "-TEST_PROCESSED_BLUEPRINT_BLUEPRINT_TYPE_ICON_URL-";
		public static final Integer TEST_PROCESSED_BLUEPRINT_BLUEPRINT_TYPE_ID = 31359;
		public static final String TEST_PROCESSED_BLUEPRINT_BLUEPRINT_TYPE_NAME = "Small Ancillary Current Router I Blueprint";
		public static final Double TEST_PROCESSED_BLUEPRINT_OUTPUT_PRICE = 574800.0;
		public static final String TEST_PROCESSED_BLUEPRINT_OUTPUT_TYPE_ICON_URL = "-TEST_PROCESSED_OUTPUT_BLUEPRINT_TYPE_ICON_URL-";
		public static final Integer TEST_PROCESSED_BLUEPRINT_OUTPUT_TYPE_ID = 31358;
		public static final String TEST_PROCESSED_BLUEPRINT_OUTPUT_TYPE_NAME = "Small Ancillary Current Router I";
	}
}
