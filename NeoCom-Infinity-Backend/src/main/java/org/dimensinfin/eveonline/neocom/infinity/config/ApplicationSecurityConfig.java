package org.dimensinfin.eveonline.neocom.infinity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import org.dimensinfin.eveonline.neocom.infinity.config.filter.JWTAuthorizationFilter;
import org.dimensinfin.eveonline.neocom.infinity.config.security.CredentialDetailsService;
import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;

import static org.dimensinfin.eveonline.neocom.infinity.config.security.SecurityConstants.ACTUATORS_URL;
import static org.dimensinfin.eveonline.neocom.infinity.config.security.SecurityConstants.CREDENTIAL_SUPPORT_URL;
import static org.dimensinfin.eveonline.neocom.infinity.config.security.SecurityConstants.GET_ITEM;
import static org.dimensinfin.eveonline.neocom.infinity.config.security.SecurityConstants.LOGIN_SESSION_VERIFICATION_URL;
import static org.dimensinfin.eveonline.neocom.infinity.config.security.SecurityConstants.LOGIN_VERIFICATION_URL;
import static org.dimensinfin.eveonline.neocom.infinity.config.security.SecurityConstants.PUBLIC_URL;
import static org.dimensinfin.eveonline.neocom.infinity.config.security.SecurityConstants.SERVER_STATUS_URL;
import static org.dimensinfin.eveonline.neocom.infinity.config.security.SecurityConstants.STORE_CREDENTIAL_URL;
import static org.dimensinfin.eveonline.neocom.infinity.config.security.SecurityConstants.UNIVERSEV1_URL;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
	private final NeoComAuthenticationProvider neoComAuthenticationProvider;
	private final CredentialDetailsService credentialDetailsService;

	// - C O N S T R U C T O R S
	@Autowired
	public ApplicationSecurityConfig( final CredentialDetailsService userDetailsService,
	                                  final NeoComAuthenticationProvider neoComAuthenticationProvider ) {
		this.neoComAuthenticationProvider = neoComAuthenticationProvider;
		this.credentialDetailsService = userDetailsService;
	}

	@Override
	public void configure( final AuthenticationManagerBuilder auth ) throws Exception {
		auth.authenticationProvider( this.neoComAuthenticationProvider );
	}

	@Override
	protected void configure( final HttpSecurity http ) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests()
				.antMatchers(
						ACTUATORS_URL,
						LOGIN_VERIFICATION_URL, LOGIN_SESSION_VERIFICATION_URL, STORE_CREDENTIAL_URL,
						UNIVERSEV1_URL, PUBLIC_URL,
						SERVER_STATUS_URL, CREDENTIAL_SUPPORT_URL,
						GET_ITEM ).permitAll() // List of URL that do not require authentication JWT token.
				.anyRequest().authenticated() // The rest of endpoint are authenticated.
				.and()
				.addFilter( new JWTAuthorizationFilter( this.authenticationManager() ) )
				// This disables session creation on Spring Security
				.sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS );
		// Create the session when it is required.
		//		http.sessionManagement()
		//				.sessionCreationPolicy( SessionCreationPolicy.IF_REQUIRED );
		// Allow multiple session for the same pilot concurrently.
		//		http.sessionManagement().maximumSessions( 5 );
		// Configure the CookieCsrfTokenRepository so JavaScript can read the cookie.
		//		http.csrf()
		//				.csrfTokenRepository( CookieCsrfTokenRepository.withHttpOnlyFalse() );
		//		http.headers()
		//				.contentSecurityPolicy(
		//						"script-src 'self' https://trustedscripts.example.com; object-src https://trustedplugins.example.com; report-uri /csp-report-endpoint/"
		//				);
		//		http.sessionManagement().maximumSessions( 2 );
		//		http.sessionManagement()
		//				.expiredUrl( "/sessionExpired.html" )
		//				.invalidSessionUrl( "/invalidSession.html" );
		//		servletContext.setSessionTrackingModes( EnumSet.of( Session.SessionTrackingMode.COOKIE ) );
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration( "/**", new CorsConfiguration().applyPermitDefaultValues() );
		return source;
	}

	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}
}

