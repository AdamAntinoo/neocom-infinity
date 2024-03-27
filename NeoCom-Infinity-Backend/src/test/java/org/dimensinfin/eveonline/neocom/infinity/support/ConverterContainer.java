package org.dimensinfin.eveonline.neocom.infinity.support;

import org.dimensinfin.eveonline.neocom.infinity.support.authorization.converter.CucumberTableToValidateAuthorizationTokenRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConverterContainer {
	private final List<CucumberTableToRequestConverter> converters = new ArrayList<>();

	@Autowired
	public ConverterContainer( final CucumberTableToValidateAuthorizationTokenRequest c1 ) {
		this.registerConverter(c1);
	}

	public List<CucumberTableToRequestConverter> getConverters() {
		return this.converters;
	}

	private void registerConverter( final CucumberTableToRequestConverter converter ) {
		this.converters.add(converter);
	}
}
