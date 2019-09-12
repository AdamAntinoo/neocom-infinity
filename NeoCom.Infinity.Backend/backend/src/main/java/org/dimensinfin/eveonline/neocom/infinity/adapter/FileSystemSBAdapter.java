package org.dimensinfin.eveonline.neocom.infinity.adapter;

import org.dimensinfin.eveonline.neocom.interfaces.IFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Spring boot implementation for the File System isolation interface. We can get access to the application generated data
 * files stored on the private application folder or to the application assets deployed with the compiled code.
 *
 * The Assets api will access the readonly application deployed files while the Resource api will deal with the temporary
 * application storage files like cache or running stored local data.
 * @author Adam Antinoo
 */
// - CLASS IMPLEMENTATION ...................................................................................
public class FileSystemSBAdapter implements IFileSystem {
	// - S T A T I C - S E C T I O N ..........................................................................
	private static Logger logger = LoggerFactory.getLogger("FileSystemSBImplementation");
	private static ClassLoader classLoader = null;

	// - F I E L D - S E C T I O N ............................................................................
	private String applicationFolder = "./NeoCom.Infinity";

	// - C O N S T R U C T O R - S E C T I O N ................................................................
	public FileSystemSBAdapter(final String applicationStoreDirectory ) {
		logger.info(">< [FileSystemSBImplementation.constructor]> applicationStoreDirectory: {}", applicationStoreDirectory);
		if (null != applicationStoreDirectory)
			this.applicationFolder = applicationStoreDirectory;
		logger.info("-- [FileSystemSBImplementation.constructor]> applicationFolder: {}", this.applicationFolder);
	}

	// - M E T H O D - S E C T I O N ..........................................................................
	@Override
	public InputStream openResource4Input( final String filePath ) throws IOException {
		return new FileInputStream(new File(applicationFolder + "/" + filePath));
	}

	@Override
	public OutputStream openResource4Output( final String filePath ) throws IOException {
		return new FileOutputStream(new File(applicationFolder + "/" + filePath));
	}

	@Override
	public InputStream openAsset4Input( final String filePath ) throws IOException {
		URI propertyURI = null;
		try {
			final String executionDirectory = new java.io.File(".").getCanonicalPath() + "/build/resources/main/";
//			final URL resource = getClassLoader().getResource(filePath);
//			if (null == resource) throw new IOException("[FileSystemSBImplementation.openAsset4Input]> Resource file " + filePath + "" +
//					" not found with classloader.");
			propertyURI = new URI(executionDirectory + filePath);
			logger.info("DD [FileSystemSBImplementation.openAsset4Input]> Processing file: {}", propertyURI);
		} catch (URISyntaxException use) {
		}
		return new FileInputStream(propertyURI.getPath());
	}

	@Override
	public String accessAsset4Path( final String filePath ) throws IOException {
		URI propertyURI = null;
		try {
			final String executionDirectory = new java.io.File(".").getCanonicalPath() + "/build/resources/main/";
//			final URL resource = getClassLoader().getResource(filePath);
//			if (null == resource) throw new IOException("[FileSystemSBImplementation.openAsset4Input]> Resource file " + filePath + "" +
//					" not found with classloader.");
			propertyURI = new URI(executionDirectory + filePath);
			logger.info("DD [FileSystemSBImplementation.accessAsset4Path]> Processing file: {}", propertyURI);
		} catch (URISyntaxException e) {
		}
		return propertyURI.getPath();
	}

	@Override
	public String accessResource4Path( final String filePath ) {
		return applicationFolder + "/" + filePath;
	}

	@Override
	public void copyFromAssets(final String sourceFileName, final String destinationDirectory) {
 // TODO - Implement the copy fom one place to another
	}

//	@Override
//	public String accessAppStorage4Path( final String filePath ) {
//		return accessResource4Path(filePath);
//	}
//[01]

	/**
	 * Get a first access application classloader to be used to generate Resource paths.
	 * @return an application classloader to have a reference point from the application run place.
	 */
	protected ClassLoader getClassLoader() {
		if (null == classLoader) classLoader = getClass().getClassLoader();
		return classLoader;
	}

	// --- D E L E G A T E D   M E T H O D S
	@Override
	public String toString() {
		final StringBuffer buffer = new StringBuffer("FileSystemSBImplementation [ ")
				.append("applicationFolder:").append(applicationFolder).append(" ");
		try {
			buffer.append("assetsFolder:").append(accessAsset4Path("")).append(" ");
		} catch (IOException ioe) {
		}
		return buffer.append("]")
//				.append("->").append(super.toString())
				.toString();
	}
}

// - UNUSED CODE ............................................................................................
//[01]
//	@Override
//	public InputStream openAsset4Input( final String filePath ) throws IOException {
//		return new FileInputStream(new File(accessAssetPath() + filePath));
//	}
//
//	@Override
//	public File accessAppStorageFile( final String filePath ) {
//		return new File(applicationFolder + "/" + filePath);
//	}
//
//	@Override
//	public String accessAssetPath() {
//		return "./src/main/";
//	}
