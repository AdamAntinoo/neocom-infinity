package org.dimensinfin.eveonline.neocom.infinity.config.filter;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.UUID;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jetbrains.annotations.NotNull;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import org.dimensinfin.logging.LogWrapper;

@Component
public class LogMDCFilter extends OncePerRequestFilter {
	private static final String mdcRequestIdKey = "MDC.REQUEST-ID";
	private static final String mdcEntryPointKey = "MDC.ENTRY-POINT";
	private static final String mdcApplicationCodeKey = "MDC.APP-IDENTIFIER";

	/**
	 * Analyze the request header list. If there is a Request Id from Heroku then configure that identifier on the logger to identify all log
	 * operations for the same request.
	 * If there is not such request header create a new identifier and the header on the response.
	 */
	@Override
	protected void doFilterInternal( @NotNull final HttpServletRequest request,
	                                 @NotNull final HttpServletResponse response,
	                                 @NotNull final FilterChain filterChain ) throws ServletException, IOException {
		try {
			// Search for the request unique identifier.
			String requestId = request.getHeader( "X-Request-ID" );
			if (requestId != null) { // Found. Use the Heorku identifier.
				LogWrapper.info( MessageFormat.format( "Found Heroku RUID: {0}", requestId ) );
				MDC.put( mdcRequestIdKey, requestId );
			} else { // missing. Create a new unique identifier for this request.
				requestId = UUID.randomUUID().toString().toUpperCase().replace( "-", "" );
				LogWrapper.info( MessageFormat.format( "Created new RUID: {0}", requestId ) );
				MDC.put( mdcRequestIdKey, requestId );
			}
			response.addHeader( "xApp-Request-ID", requestId );

			// Configure the entry point name.
			final String requestPath = request.getRequestURI();
			MDC.put( mdcEntryPointKey, requestPath );

			// Configure the application code identifier.
			final String appCode = request.getHeader( "xApp-Name" );
			if (appCode != null) {
				MDC.put( mdcApplicationCodeKey, appCode );
			}
			filterChain.doFilter( request, response );
		} finally {
			MDC.clear();
		}
	}
}
