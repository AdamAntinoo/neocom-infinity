package org.dimensinfin.eveonline.neocom.infinity.adapter.implementers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.dimensinfin.eveonline.neocom.provider.IFileSystem;

public class SBFileSystemAdapterTest {
	private static final String TEST_APPLICATION_DIRECTORY = "-TEST-APPLICATION-DIRECTORY-";
	private SBFileSystemAdapter fileSystemAdapter4Test;

	@BeforeEach
	public void beforeEach() {
		this.fileSystemAdapter4Test = new SBFileSystemAdapter( TEST_APPLICATION_DIRECTORY );
	}

	@Test
	public void constructorContract() {
		final SBFileSystemAdapter fileSystemAdapter = new SBFileSystemAdapter( TEST_APPLICATION_DIRECTORY );
		Assertions.assertNotNull( fileSystemAdapter );
	}

	@Test
	public void openResource4InputSuccess() throws IOException {
		final FileNotFoundException exception = Assertions.assertThrows( FileNotFoundException.class, () -> {
					this.fileSystemAdapter4Test.openResource4Input( "-TEST-FILE-" );
				},
				"Expected JobScheduler.Builder() to throw null verification, but it didn't." );
		Assertions.assertNotNull( exception );
		Assertions.assertTrue( exception.getMessage().contains( "No such file or directory" ) );
	}

	@Test
	public void openResource4Output() throws IOException {
		final IFileSystem fileSystem = new SBFileSystemAdapter( "./src/test" );
		final OutputStream file = fileSystem.openResource4Output( "FILE.test" );
		Assertions.assertNotNull( file );
	}
}
