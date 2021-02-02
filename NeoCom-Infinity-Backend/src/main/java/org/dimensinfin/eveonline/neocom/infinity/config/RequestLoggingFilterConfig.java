package org.dimensinfin.eveonline.neocom.infinity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
//@Configuration
public class RequestLoggingFilterConfig {

	@Bean
	public CommonsRequestLoggingFilter logFilter() {
		final CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
		filter.setIncludeQueryString( true );
		filter.setIncludePayload( true );
		filter.setMaxPayloadLength( 10000 );
		filter.setIncludeHeaders( false );
		filter.setAfterMessagePrefix( "REQUEST DATA : " );
		return filter;
	}
}