package org.dimensinfin.eveonline.neocom.infinity.config.interceptor;

import java.text.MessageFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.HandlerInterceptor;

import org.dimensinfin.logging.LogWrapper;

public class LogResponseInterceptor implements HandlerInterceptor {
	private final ObjectMapper mapper = new ObjectMapper();

	@Override
	public void afterCompletion( @NotNull final HttpServletRequest request,
	                             @NotNull final HttpServletResponse response,
	                             @NotNull final Object handler,
	                             final Exception ex ) {
		try {
			LogWrapper.info( MessageFormat.format( "[REQUEST]> {0}", mapper.writeValueAsString( response ) ) );
		} catch (final JsonProcessingException jsone) {
			LogWrapper.error( jsone );
		}
	}
}
