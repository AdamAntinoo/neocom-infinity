package org.dimensinfin.eveonline.neocom.infinity.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.openapitools.configuration.ClientConfiguration;

@FeignClient(name="${authorization.name:authorization}", url="${authorization.url:http://localhost}", configuration = ClientConfiguration.class)
public interface AuthorizationApiClient extends AuthorizationApi {
}
