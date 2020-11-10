package org.dimensinfin.eveonline.neocom.infinity;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.mediatype.hal.HalConfiguration;
import org.springframework.web.filter.ForwardedHeaderFilter;

import org.dimensinfin.logging.LogWrapper;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAutoConfiguration
public class NeoComInfinityBackendApplication {
	public static final String APPLICATION_ERROR_CODE_PREFIX = "dimensinfin.eveonline.neocom";

	public static void main( String[] args ) {
		LogWrapper.enter();
		SpringApplication.run( NeoComInfinityBackendApplication.class, args );
		new LogoPrinter().print();
		LogWrapper.exit();
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

	private static final class LogoPrinter {
		public void print() {
			this.printVersion( this.readAllBytes() );
		}

		private void printVersion( final String bannerData ) {
			LogWrapper.info( "\n\n" + bannerData + "\n" );
		}

		private String readAllBytes() {
			try {
				File resource = new File( System.getenv( "NEOCOM_BANNER_LOCATION" ) );
				return new String( Files.readAllBytes( resource.toPath() ) );
			} catch (final IOException ioe) {
				LogWrapper.error( ioe );
				return "        ___      ___      ___  \n" +
						"__   __/ _ \\    / _ \\    / _ \\ \n" +
						"\\ \\ / / | | |  | | | |  | | | |\n" +
						" \\ V /| |_| |  | |_| |  | |_| |\n" +
						"  \\_/  \\___(_)  \\___(_)  \\___/ \n" +
						"                               \n";
			}
		}
	}
}
