package org.dimensinfin.eveonline.neocom.infinity.adapter.implementers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.annimon.stream.Stream;

import org.dimensinfin.eveonline.neocom.adapters.AConfigurationProvider;

public class SBConfigurationProvider extends AConfigurationProvider {
	protected void readAllProperties() throws IOException {
		logger.info( ">> [SBConfigurationProvider.readAllProperties]" );
		// Read all .properties files under the predefined path on the /resources folder.
//		logger.info( "-- [SBConfigurationProvider.readAllProperties]> Read property files from: build/resources {}",
//				this.getResourceLocation() );
		final List<String> propertyFiles = this.getResourceFiles( this.getResourceLocation() );
//		final ClassLoader classLoader = getClass().getClassLoader();
		Stream.of( propertyFiles )
				.sorted()
				.forEach( ( fileName ) -> {
					logger.info( "-- [SBConfigurationProvider.readAllProperties]> Processing file: {}", fileName );
					try {
						Properties properties = new Properties();
						// Generate the proper URI to ge tot the resource file.
//						final String propertyFileName = this.getResourceLocation() + "/" + fileName;
//						final URI propertyURI = new URI( classLoader.getResource( propertyFileName ).toString() );
						properties.load( new FileInputStream( fileName ) );
						// Copy properties to globals.
						configurationProperties.putAll( properties );
					} catch (IOException ioe) {
						logger.error( "E [SBConfigurationProvider.readAllProperties]> Exception reading properties file {}. {}",
								fileName, ioe.getMessage() );
						ioe.printStackTrace();
//					} catch (URISyntaxException e) {
//						e.printStackTrace();
					}
				} );
		logger.info( "<< [SBConfigurationProvider.readAllProperties]> Total properties number: {}", this.contentCount() );
	}

	/**
	 * Reads all the files found on the parameter directory path. Because the directory is read as a stream the method to read
	 * the directory does not use the file system isolation.
	 */
	protected List<String> getResourceFiles( final String initialPath ) throws IOException {
		final File rootFolder = new File( System.getProperty( "user.dir" ) + initialPath );
		logger.info( "-- [SBConfigurationProvider.readAllProperties]> Root directory: {}",
				rootFolder );
		return listFilesForFolder( rootFolder );
//		Files.walk API is available from Java 8.

//		try (Stream<Path> paths = Files.walk( Paths.get( "/home/you/Desktop" ) )) {
//			paths
//					.filter( Files::isRegularFile )
//					.forEach( System.out::println );
//		}


//		List<String> filenames = new ArrayList<>();
//		try (InputStream in = this.getResourceAsStream( path );
//		     BufferedReader br = new BufferedReader( new InputStreamReader( in ) )) {
//			String resource;
//			while ((resource = br.readLine()) != null) {
//				filenames.add( resource );
//			}
//		}
//		return filenames;
	}

	public List<String> listFilesForFolder( final File folder ) {
		List<String> filenames = new ArrayList<>();
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) listFilesForFolder( fileEntry );
			else filenames.add( fileEntry.getPath() );
		}
		return filenames;
	}

//	private InputStream getResourceAsStream( final String resource ) {
//		final InputStream in = getContextClassLoader().getResourceAsStream( resource );
//
//		return in == null ? getClass().getResourceAsStream( resource ) : in;
//	}
//
//	private ClassLoader getContextClassLoader() {
//		return Thread.currentThread().getContextClassLoader();
//	}

	// - B U I L D E R
	public static class Builder extends AConfigurationProvider.Builder {
		private SBConfigurationProvider onConstruction;

		@Override
		protected AConfigurationProvider getActual() {
			if (null == this.onConstruction) this.onConstruction = new SBConfigurationProvider();
			return this.onConstruction;
		}

		@Override
		protected AConfigurationProvider.Builder getActualBuilder() {
			return this;
		}

		public SBConfigurationProvider build() throws IOException {
			final SBConfigurationProvider provider = (SBConfigurationProvider) super.build();
			return provider;
		}
	}
}
