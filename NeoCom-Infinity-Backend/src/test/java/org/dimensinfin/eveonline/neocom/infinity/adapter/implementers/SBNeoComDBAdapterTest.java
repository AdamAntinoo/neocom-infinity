package org.dimensinfin.eveonline.neocom.infinity.adapter.implementers;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRuntimeBackendException;

public class SBNeoComDBAdapterTest {
	protected static void setEnv( final Map<String, String> newenv ) throws Exception {
		try {
			final Class<?> processEnvironmentClass = Class.forName( "java.lang.ProcessEnvironment" );
			final Field theEnvironmentField = processEnvironmentClass.getDeclaredField( "theEnvironment" );
			theEnvironmentField.setAccessible( true );
			final Map<String, String> env = (Map<String, String>) theEnvironmentField.get( null );
			env.putAll( newenv );
			final Field theCaseInsensitiveEnvironmentField = processEnvironmentClass.getDeclaredField( "theCaseInsensitiveEnvironment" );
			theCaseInsensitiveEnvironmentField.setAccessible( true );
			final Map<String, String> cienv = (Map<String, String>) theCaseInsensitiveEnvironmentField.get( null );
			cienv.putAll( newenv );
		} catch (final NoSuchFieldException e) {
			final Class[] classes = Collections.class.getDeclaredClasses();
			final Map<String, String> env = System.getenv();
			for (final Class cl : classes) {
				if ("java.util.Collections$UnmodifiableMap".equals( cl.getName() )) {
					final Field field = cl.getDeclaredField( "m" );
					field.setAccessible( true );
					final Object obj = field.get( env );
					final Map<String, String> map = (Map<String, String>) obj;
					map.clear();
					map.putAll( newenv );
				}
			}
		}
	}

	@Test
	public void constructorContract() throws Exception {
		// Given
		final Map<String, String> env = new HashMap();
		env.put( "NEOCOM_DATABASE_URL", "-DATABASE-URL-" );
		setEnv( env );
		// Test
		final SBNeoComDBAdapter dbAdapter = new SBNeoComDBAdapter();
		// Assertions
		Assertions.assertNotNull( dbAdapter );
	}

	@Test
	public void constructorFailure() {
		final NeoComRuntimeBackendException exception = Assertions.assertThrows( NeoComRuntimeBackendException.class, SBNeoComDBAdapter::new,
				"Expected SBNeoComDBAdapter.<init> to throw Runtime exception, but it didn't." );
		Assertions.assertNotNull( exception );
		Assertions.assertTrue(
				exception.getMessage().contains( "Initialization exception: Required environment variable 'NEOCOM_DATABASE_URL' is not declared." ) );
	}
}
