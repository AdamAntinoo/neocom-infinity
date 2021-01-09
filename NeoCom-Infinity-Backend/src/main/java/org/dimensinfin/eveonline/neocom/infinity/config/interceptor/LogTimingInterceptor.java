package org.dimensinfin.eveonline.neocom.infinity.config.interceptor;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.HandlerInterceptor;

import org.dimensinfin.logging.LogWrapper;

public class LogTimingInterceptor implements HandlerInterceptor {
	private Instant timer;

	@Override
	public boolean preHandle( @NotNull final HttpServletRequest request,
	                          @NotNull final HttpServletResponse response,
	                          @NotNull final Object handler ) {
		this.timer = Instant.now();
		return true;
	}

	@Override
	public void afterCompletion( @NotNull final HttpServletRequest request,
	                             @NotNull final HttpServletResponse response,
	                             @NotNull final Object handler,
	                             final Exception ex ) {
		LogWrapper.info( MessageFormat.format( "[TIMING]> {0}", Duration.between( this.timer, Instant.now() ).toMillis() + "ms" ) );
	}
}
