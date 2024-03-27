package org.dimensinfin.eveonline.neocom.infinity.adapter.implementers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.dimensinfin.eveonline.neocom.provider.IFileSystem;
import org.dimensinfin.eveonline.neocom.service.scheduler.JobScheduler;

public class SBFileSystemAdapterTest {
	private SBFileSystemAdapter fileSystemAdapter4Test ;

	@BeforeEach
	public void beforeEach() {
		this.fileSystemAdapter4Test = new SBFileSystemAdapter.Builder()
				.optionalApplicationDirectory( "./open/application.directory/NeoCom.Infinity.unittesting" )
				.build();
	}

	@Test
	public void buildComplete() {
		final SBFileSystemAdapter fileSystemAdapter = new SBFileSystemAdapter.Builder()
				.optionalApplicationDirectory( "APPLICATION-DIRECTORY" )
				.build();
		Assertions.assertNotNull(fileSystemAdapter);
	}

	@Test
	public void openResource4InputSuccess() throws IOException {
		final FileNotFoundException exception = Assertions.assertThrows( FileNotFoundException.class, () -> {
					final InputStream file = this.fileSystemAdapter4Test.openResource4Input( "-TEST-FILE-" );
				},
				"Expected JobScheduler.Builder() to throw null verification, but it didn't." );
		Assertions.assertNotNull( exception );
		Assertions.assertTrue( exception.getMessage().contains( "No such file or directory" ) );
	}

	@Test
	public void openResource4Output() throws IOException {
		final IFileSystem fileSystem = new SBFileSystemAdapter.Builder()
				.optionalApplicationDirectory( "./src/test" )
				.build();
		final OutputStream file = fileSystem.openResource4Output( "FILE.test" );
		Assertions.assertNotNull( file );
	}
}
