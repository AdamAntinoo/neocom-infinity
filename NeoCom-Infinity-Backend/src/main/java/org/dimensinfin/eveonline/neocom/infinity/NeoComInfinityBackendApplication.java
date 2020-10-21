package org.dimensinfin.eveonline.neocom.infinity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.mediatype.hal.HalConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.filter.ForwardedHeaderFilter;

import org.dimensinfin.eveonline.neocom.service.logger.NeoComLogger;

@EnableScheduling
@SpringBootApplication
public class NeoComInfinityBackendApplication {
	private static final Logger logger = LoggerFactory.getLogger( NeoComInfinityBackendApplication.class );
	private static boolean printedVersion = false;

	public static void main( String[] args ) {
		NeoComLogger.enter();
		printVersion();
		SpringApplication.run( NeoComInfinityBackendApplication.class, args );
		NeoComLogger.exit();
	}

	private static void printVersion() {
		if (!printedVersion) {
			logger.info( "\n\n  ___   _  ___  _  _   \n" +
					" / _ \\ / |/ _ \\| || |  \n" +
					"| | | || | (_) | || |_ \n" +
					"| |_| || |\\__, |__   _|\n" +
					" \\___(_)_|  /_(_) |_|  \n" +
					"                       \n" );
			printedVersion = true;
		}
	}

	@Bean
	public ForwardedHeaderFilter forwardedHeaderFilter() {
		return new ForwardedHeaderFilter();
	}

	@Bean
	public HalConfiguration globalPolicy() {
		return new HalConfiguration()
				.withRenderSingleLinks( HalConfiguration.RenderSingleLinks.AS_ARRAY )
				.withRenderSingleLinksFor(
						LinkRelation.of( "prev" ), HalConfiguration.RenderSingleLinks.AS_SINGLE )
				.withRenderSingleLinksFor(
						LinkRelation.of( "next" ), HalConfiguration.RenderSingleLinks.AS_SINGLE );
	}
}
