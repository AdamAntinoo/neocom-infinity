package org.dimensinfin.eveonline.neocom.infinity.config.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.HandlerInterceptor;

public class HeaderResponseInterceptor implements HandlerInterceptor {
	@Override
	public void afterCompletion( @NotNull final HttpServletRequest request,
	                             final HttpServletResponse response,
	                             @NotNull final Object handler,
	                             final Exception ex ) {
		response.addHeader( "Access-Control-Allow-Origin", "*" );
		response.addHeader( "Access-Control-Allow-Headers", "Accept, Origin, X-Requested-With, Content-Type, Authorization, " +
				"xapp-name, xapp-platform, xapp-version" );
		response.addHeader( "Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS,HEAD" );
	}
}
