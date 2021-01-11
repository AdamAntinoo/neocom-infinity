package org.dimensinfin.eveonline.neocom.infinity.credential.rest.support;

import java.sql.SQLException;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComSBException;
import org.dimensinfin.eveonline.neocom.infinity.credential.persistence.SupportCredentialRepository;
import org.dimensinfin.eveonline.neocom.infinity.support.client.CredentialCountResponse;
import org.dimensinfin.eveonline.neocom.service.logger.NeoComLogger;

@Service
@Profile("acceptance")
public class CredentialSupportService {
	private final SupportCredentialRepository credentialRepository;

	// - C O N S T R U C T O R S
	@Autowired
	public CredentialSupportService( @NotNull final SupportCredentialRepository supportCredentialRepository ) {
		this.credentialRepository = supportCredentialRepository;
	}

	public CredentialCountResponse countCredentials() {
		return new CredentialCountResponse.Builder()
				.withCredentialCount( this.credentialRepository.accessAllCredentials().size() )
				.build();
	}

	public CredentialCountResponse deleteAllCredentials() {
		try {
			return new CredentialCountResponse.Builder()
					.withCredentialCount( this.credentialRepository.deleteAll() )
					.build();
		} catch (final SQLException sqle) {
			NeoComLogger.error( sqle );
			throw new NeoComSBException( "Sql exception while deleting all credentials." );
		}
	}

	public Credential findCredentialById( final String credentialId ) {
		try {
			return this.credentialRepository.findCredentialById( credentialId );
		} catch (final SQLException sqle) {
			NeoComLogger.error( sqle );
			throw new NeoComSBException( "Sql exception while searching for credential." );
		}
	}
}
