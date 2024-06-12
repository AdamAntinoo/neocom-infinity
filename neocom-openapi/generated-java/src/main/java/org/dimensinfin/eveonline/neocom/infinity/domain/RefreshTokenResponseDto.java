/*
 * NeoCom Backend API
 * New NeoCom Infinity backend API. This is the complete API implementation for the backend services exposed, whatever the backend server that will provide the service. Initial implementations used the Java backend server and the ESI Public Data Services as a complementary source. New implementations have a dual backend service, part of it the original Java SpringBoot services and a new NestJS Typescript service that will implement new services and also hide the ESI Public sources into a HyperLink like DTO definitions. <br> Different api endpoints will be isolated with a version prefix so even they are all defined under the same path root the different version will be used by routers and proxies to fire the request to the correct backend implementation. 
 *
 * The version of the OpenAPI document: 0.29.0
 * Contact: adamantinoo.git@gmail.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.dimensinfin.eveonline.neocom.infinity.domain;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.dimensinfin.eveonline.neocom.infinity.domain.CredentialDto;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.constraints.*;
import jakarta.validation.Valid;

import jakarta.annotation.Generated;
import java.io.Serial;

/**
 * RefreshTokenResponseDto
 */
@JsonPropertyOrder({
  RefreshTokenResponseDto.JSON_PROPERTY_CREDENTIAL
})
@JsonTypeName("RefreshTokenResponse")
@lombok.Builder
@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class RefreshTokenResponseDto {

  public static final String JSON_PROPERTY_CREDENTIAL = "credential";

  private CredentialDto credential;
}

