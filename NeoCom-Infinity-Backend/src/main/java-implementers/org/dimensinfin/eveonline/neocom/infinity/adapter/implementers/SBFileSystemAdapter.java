package org.dimensinfin.eveonline.neocom.infinity.adapter.implementers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.text.MessageFormat;
import javax.validation.constraints.NotNull;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import org.dimensinfin.eveonline.neocom.provider.IFileSystem;
import org.dimensinfin.logging.LogWrapper;

/**
 * Spring boot implementation for the File System isolation interface. We can get access to the application generated data
 * files stored on the private application folder or to the application assets deployed with the compiled code.
 *
 * The Assets api will access the readonly application deployed files while the Resource api will deal with the temporary
 * application storage files like cache or running stored local data.
 *
 * @author Adam Antinoo
 */
public class SBFileSystemAdapter implements IFileSystem {
	private static final String DIRECTORY_SEPARATOR = "/";
	private static final String PRODUCTION_RESOURCES_PATH = DIRECTORY_SEPARATOR + "build" +
			DIRECTORY_SEPARATOR + "resources" +
			DIRECTORY_SEPARATOR + "main" + DIRECTORY_SEPARATOR;
	protected String applicationDirectory;

	// - C O N S T R U C T O R S
	@Inject
	public SBFileSystemAdapter( final @NotNull @Named("ApplicationDirectory") String applicationDirectory ) {
		this.applicationDirectory = applicationDirectory;
	}

	@Override
	public boolean checkWritable( final String filePath ) {
		return Files.isWritable( FileSystems.getDefault().getPath( filePath ) );
	}

	@Override
	public InputStream openResource4Input( final String filePath ) throws IOException {
		return new FileInputStream( this.applicationDirectory + DIRECTORY_SEPARATOR + filePath );
	}

	@Override
	public OutputStream openResource4Output( final String filePath ) throws IOException {
		return new FileOutputStream( this.applicationDirectory + DIRECTORY_SEPARATOR + filePath );
	}

	@Override
	public InputStream openAsset4Input( final String filePath ) throws IOException {
		try {
			final String executionDirectory = new File( "." ).getCanonicalPath() + PRODUCTION_RESOURCES_PATH;
			final URI propertyURI = new URI( executionDirectory + filePath );
			LogWrapper.info( MessageFormat.format( "Processing file: {0}", propertyURI ) );
			return new FileInputStream( propertyURI.getPath() );
		} catch (final URISyntaxException use) {
			LogWrapper.error( use );
			throw new IOException( use.getMessage() );
		}
	}

	@Override
	public String accessAsset4Path( final String filePath ) throws IOException {
		try {
			final String executionDirectory = new java.io.File( "." ).getCanonicalPath() + PRODUCTION_RESOURCES_PATH;
			final URI propertyURI = new URI( executionDirectory + filePath );
			LogWrapper.info( MessageFormat.format( "Processing file: {0}", propertyURI ) );
			return propertyURI.getPath();
		} catch (final URISyntaxException use) {
			LogWrapper.error( use );
			throw new IOException( use.getMessage() );
		}
	}

	@Override
	public String accessResource4Path( final String filePath ) throws IOException {
		if (null == filePath) throw new IOException( "The resource path name is empty." );
		return this.applicationDirectory + "/" + filePath;
	}

	@Override
	public String accessPublicResource4Path( final String filePath ) throws IOException {
		return this.accessResource4Path( filePath );
	}

	@Override
	public void copyFromAssets( final String sourceFileName, final String destinationDirectory ) {
		// This method does not require implementation on this environment.
	}

	// - C O R E
	@Override
	public String toString() {
		return new ToStringBuilder( this, ToStringStyle.JSON_STYLE )
				.append( "applicationDirectory", this.applicationDirectory )
				.toString();
	}
}
