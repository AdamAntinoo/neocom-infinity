package org.openapitools.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class ClientConfiguration {

  @Value("${neocombackend.security.neocomEsiAuth.key:}")
  private String neocomEsiAuthKey;

  @Bean
  @ConditionalOnProperty(name = "neocombackend.security.neocomEsiAuth.key")
  public ApiKeyRequestInterceptor neocomEsiAuthRequestInterceptor() {
    return new ApiKeyRequestInterceptor("query", "neocom_esi_auth", this.neocomEsiAuthKey);
  }

}
