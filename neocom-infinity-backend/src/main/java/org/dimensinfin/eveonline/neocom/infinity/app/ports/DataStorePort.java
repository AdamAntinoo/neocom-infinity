package org.dimensinfin.eveonline.neocom.infinity.app.ports;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.dimensinfin.eveonline.neocom.domain.EsiType;
import org.dimensinfin.eveonline.neocom.infinity.infrastructure.adapters.impedance.IGenerator;
import org.dimensinfin.eveonline.neocom.service.IDataStore;

public interface DataStorePort extends IDataStore {

	Optional<EsiType> accessType4Id( int typeId, final  @NotNull IGenerator<EsiType> generatorEsiType );
}
