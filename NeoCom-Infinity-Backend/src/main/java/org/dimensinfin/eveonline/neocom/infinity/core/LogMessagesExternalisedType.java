package org.dimensinfin.eveonline.neocom.infinity.core;

public enum LogMessagesExternalisedType {
	UPDATEMANAGER_JOB_ENTERING_STATE("-- [UpdaterJobManager.call]> Job {} entering state: {}");

	private String message;

	LogMessagesExternalisedType( final String message ) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}
}