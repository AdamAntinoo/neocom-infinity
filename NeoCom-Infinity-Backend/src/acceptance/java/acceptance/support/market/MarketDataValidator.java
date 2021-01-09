package acceptance.support.market;

import java.util.Map;

import org.junit.jupiter.api.Assertions;

import acceptance.support.Validator;
import org.dimensinfin.eveonline.neocom.infinity.backend.market.domain.MarketData;

public class MarketDataValidator implements Validator<MarketData> {
	public static final String MARKETDATA_TYPE_ID = "typeId";
	public static final String MARKETDATA_SELL_DEEP = "sellDeep";
	public static final String MARKETDATA_SELL_AVERAGE = "sellAverage";
	public static final String MARKETDATA_MARKET_WIDTH = "marketWidth";

	@Override
	public boolean validate( final Map<String, String> rowData, final MarketData record ) {
		if (null != rowData.get( MARKETDATA_TYPE_ID ))
			Assertions.assertEquals( Integer.parseInt( rowData.get( MARKETDATA_TYPE_ID ) ),
					record.getTypeId()
			);
		if (null != rowData.get( MARKETDATA_SELL_DEEP ))
			Assertions.assertEquals( Integer.parseInt( rowData.get( MARKETDATA_SELL_DEEP ) ),
					record.getSellDeep()
			);
		if (null != rowData.get( MARKETDATA_SELL_AVERAGE ))
			Assertions.assertEquals( Double.parseDouble( rowData.get( MARKETDATA_SELL_AVERAGE ) ),
					record.getSellAverage(),
					0.01
			);
		if (null != rowData.get( MARKETDATA_MARKET_WIDTH ))
			Assertions.assertEquals( Double.parseDouble( rowData.get( MARKETDATA_MARKET_WIDTH ) ),
					record.getMarketWidth(),
					0.01
			);
		return true;
	}
}