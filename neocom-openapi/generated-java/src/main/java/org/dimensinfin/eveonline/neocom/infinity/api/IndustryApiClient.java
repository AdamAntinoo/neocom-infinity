package org.dimensinfin.eveonline.neocom.infinity.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.openapitools.configuration.ClientConfiguration;

@FeignClient(name="${industry.name:industry}", url="${industry.url:http://localhost}", configuration = ClientConfiguration.class)
public interface IndustryApiClient extends IndustryApi {
}
