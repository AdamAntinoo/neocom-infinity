package org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildActionPreferencesRepository extends JpaRepository<ActionPreferenceEntity, String> {
}
