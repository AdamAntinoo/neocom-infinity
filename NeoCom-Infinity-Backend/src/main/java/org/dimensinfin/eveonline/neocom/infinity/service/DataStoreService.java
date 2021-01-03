package org.dimensinfin.eveonline.neocom.infinity.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.domain.ProcessedBlueprint;

@Service
public class DataStoreService {
	private Map<Credential, List<ProcessedBlueprint>> processedBlueprintsStore = new HashMap<>();

	public List<ProcessedBlueprint> accessProcessedBlueprints( final Credential credential ) {
		return this.processedBlueprintsStore.get( credential );
	}

	public void updateProcessedBlueprint( final Credential credential, final List<ProcessedBlueprint> blueprint ) {
		this.processedBlueprintsStore.put( credential, blueprint );
	}
}