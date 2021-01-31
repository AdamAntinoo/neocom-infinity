package org.dimensinfin.eveonline.neocom.infinity.acceptance.support.universe.rest;

import java.util.Map;

import org.junit.jupiter.api.Assertions;

import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.Validator;
import org.dimensinfin.eveonline.neocom.loyalty.persistence.LoyaltyOfferEntity;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
public class LoyaltyOfferValidator implements Validator<LoyaltyOfferEntity> {
	public static final String LOYALTY_OFFER_OFFER_ID = "offerId";
	public static final String LOYALTY_OFFER_TYPE_ID = "typeId";
	public static final String LOYALTY_OFFER_TYPE_NAME = "typeName";
	public static final String LOYALTY_OFFER_CORPORATION_ID = "corporationId";
	public static final String LOYALTY_OFFER_CORPORATION_NAME = "corporationName";
	public static final String LOYALTY_OFFER_LP_VALUE = "lpValue";
	public static final String LOYALTY_OFFER_ISK_COST = "iskCost";
	public static final String LOYALTY_OFFER_LP_COST = "lpCost";
	public static final String LOYALTY_OFFER_QUANTITY = "quantity";
	public static final String LOYALTY_OFFER_REGION_ID = "regionId";
	public static final String LOYALTY_OFFER_PRICE = "price";

	@Override
	public boolean validate( final Map<String, String> rowData, final LoyaltyOfferEntity record ) {
		if (null != rowData.get( LOYALTY_OFFER_OFFER_ID ))
			Assertions.assertEquals( Integer.parseInt( rowData.get( LOYALTY_OFFER_OFFER_ID ) ), record.getOfferId() );
		if (null != rowData.get( LOYALTY_OFFER_TYPE_ID ))
			Assertions.assertEquals( Integer.parseInt( rowData.get( LOYALTY_OFFER_TYPE_ID ) ), record.getTypeId() );
		if (null != rowData.get( LOYALTY_OFFER_TYPE_NAME ))
			Assertions.assertEquals( rowData.get( LOYALTY_OFFER_TYPE_NAME ), record.getTypeName() );
		if (null != rowData.get( LOYALTY_OFFER_CORPORATION_ID ))
			Assertions.assertEquals( Integer.parseInt( rowData.get( LOYALTY_OFFER_CORPORATION_ID ) ), record.getCorporationId() );
		if (null != rowData.get( LOYALTY_OFFER_CORPORATION_NAME ))
			Assertions.assertEquals( rowData.get( LOYALTY_OFFER_CORPORATION_NAME ), record.getCorporationName() );
		if (null != rowData.get( LOYALTY_OFFER_LP_VALUE ))
			Assertions.assertEquals( Integer.parseInt( rowData.get( LOYALTY_OFFER_LP_VALUE ) ), record.getLpValue() );
		if (null != rowData.get( LOYALTY_OFFER_ISK_COST ))
			Assertions.assertEquals( Long.parseLong( rowData.get( LOYALTY_OFFER_ISK_COST ) ), record.getIskCost() );
		if (null != rowData.get( LOYALTY_OFFER_LP_COST ))
			Assertions.assertEquals( Integer.parseInt( rowData.get( LOYALTY_OFFER_LP_COST ) ), record.getLpCost() );
		if (null != rowData.get( LOYALTY_OFFER_QUANTITY ))
			Assertions.assertEquals( Integer.parseInt( rowData.get( LOYALTY_OFFER_QUANTITY ) ), record.getQuantity() );
		if (null != rowData.get( LOYALTY_OFFER_REGION_ID ))
			Assertions.assertEquals( Integer.parseInt( rowData.get( LOYALTY_OFFER_REGION_ID ) ), record.getMarketRegionId() );
		if (null != rowData.get( LOYALTY_OFFER_PRICE ))
			Assertions.assertEquals( Double.parseDouble( rowData.get( LOYALTY_OFFER_PRICE ) ), record.getPrice() );
		return true;
	}
}