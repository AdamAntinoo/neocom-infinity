package org.dimensinfin.eveonline.neocom.infinity.infrastructure.config;

import java.text.MessageFormat;

public enum LogMessageCatalog {
	BLUEPRINT_LOCATION_NOT_PRESENT("Blueprint {0,number,#} location is not valid or unreachable. Target location is {1,number,#}."),
	CACHE_DESCRIPTOR_CONFIGURED("Cache Descriptor for key {0} completed. {1}.")
	;

	private final String messageTemplate;

	LogMessageCatalog(final String message){
		this.messageTemplate = message;
	}
	public String getResolvedMessage( final Object... arguments ) {
		return MessageFormat.format( this.messageTemplate, arguments );
	}
}