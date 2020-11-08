package org.dimensinfin.eveonline.neocom.infinity.backend.industry.converter;

import org.dimensinfin.core.interfaces.Converter;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.domain.FittingIndustryJob;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.rest.dao.FittingIndustryJobDao;

public class FittingIndustryJobToFittingIndustryJobDao implements Converter<FittingIndustryJob, FittingIndustryJobDao> {

	@Override
	public FittingIndustryJobDao convert( final FittingIndustryJob input ) {
		return new FittingIndustryJobDao.Builder()
//				.withHull(input.ge
				.build();
	}
}
