package org.dimensinfin.eveonline.neocom.infinity.acceptance.support.market;

import java.util.Map;

import org.junit.jupiter.api.Assertions;

import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.Validator;
import org.dimensinfin.eveonline.neocom.infinity.backend.market.domain.MarketOrder;

public class MarketOrderValidator implements Validator<MarketOrder> {
	public static final String MARKETORDER_TYPE_ID = "typeId";
	public static final String MARKETORDER_PRICE = "price";
	public static final String MARKETORDER_ORDER_ID = "orderId";
	public static final String MARKETORDER_VOLUME_REMAIN = "volumeRemain";
	public static final String MARKETORDER_VOLUME_TOTAL= "volumeTotal";

	@Override
	public boolean validate( final Map<String, String> rowData, final MarketOrder record ) {
		if (null != rowData.get( MARKETORDER_TYPE_ID ))
			Assertions.assertEquals( Integer.parseInt( rowData.get( MARKETORDER_TYPE_ID ) ),
					record.getTypeId()
			);
		if (null != rowData.get( MARKETORDER_PRICE ))
			Assertions.assertEquals( Double.parseDouble( rowData.get( MARKETORDER_PRICE ) ),
					record.getPrice(),
					0.01
			);
		if (null != rowData.get( MARKETORDER_ORDER_ID ))
			Assertions.assertEquals( Long.parseLong( rowData.get( MARKETORDER_ORDER_ID ) ),
					record.getOrderId()
			);
		if (null != rowData.get( MARKETORDER_VOLUME_REMAIN ))
			Assertions.assertEquals( Integer.parseInt( rowData.get( MARKETORDER_VOLUME_REMAIN ) ),
					record.getVolumeRemain()
			);
		if (null != rowData.get( MARKETORDER_VOLUME_TOTAL ))
			Assertions.assertEquals( Integer.parseInt( rowData.get( MARKETORDER_VOLUME_TOTAL ) ),
					record.getVolumeTotal()
			);
		return true;
	}
}