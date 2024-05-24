package org.dimensinfin.eveonline.neocom.infinity.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.openapitools.configuration.ClientConfiguration;

@FeignClient(name="${character.name:character}", url="${character.url:http://localhost}", configuration = ClientConfiguration.class)
public interface CharacterApiClient extends CharacterApi {
}
