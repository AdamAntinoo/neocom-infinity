/*
 * NeoCom Backend API
 * New NeoCom Infinity backend API. This is the complete API implementation for the backend services exposed, whatever the backend server that will provide the service. Initial implementations used the Java backend server and the ESI Public Data Services as a complementary source. New implementations have a dual backend service, part of it the original Java SpringBoot services and a new NestJS Typescript service that will implement new services and also hide the ESI Public sources into a HyperLink like DTO definitions. <br> Different api endpoints will be isolated with a version prefix so even they are all defined under the same path root the different version will be used by routers and proxies to fire the request to the correct backend implementation. 
 *
 * The version of the OpenAPI document: 0.27.0
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
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.constraints.*;
import jakarta.validation.Valid;

import jakarta.annotation.Generated;
import java.io.Serial;

/**
 * CredentialDto
 */
@JsonPropertyOrder({
  CredentialDto.JSON_PROPERTY_UNIQUE_CREDENTIAL,
  CredentialDto.JSON_PROPERTY_ACCOUNT_ID,
  CredentialDto.JSON_PROPERTY_ACCOUNT_NAME,
  CredentialDto.JSON_PROPERTY_CORPORATION_ID,
  CredentialDto.JSON_PROPERTY_DATA_SOURCE,
  CredentialDto.JSON_PROPERTY_ACCESS_TOKEN,
  CredentialDto.JSON_PROPERTY_TOKEN_TYPE,
  CredentialDto.JSON_PROPERTY_SCOPE,
  CredentialDto.JSON_PROPERTY_REFRESH_TOKEN,
  CredentialDto.JSON_PROPERTY_ASSETS_COUNT,
  CredentialDto.JSON_PROPERTY_WALLET_BALANCE,
  CredentialDto.JSON_PROPERTY_MINING_RESOURCES_ESTIMATED_VALUE,
  CredentialDto.JSON_PROPERTY_RACE_NAME,
  CredentialDto.JSON_PROPERTY_JWTOKEN
})
@JsonTypeName("Credential")
@lombok.Builder
@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class CredentialDto {

  public static final String JSON_PROPERTY_UNIQUE_CREDENTIAL = "uniqueCredential";
  public static final String JSON_PROPERTY_ACCOUNT_ID = "accountId";
  public static final String JSON_PROPERTY_ACCOUNT_NAME = "accountName";
  public static final String JSON_PROPERTY_CORPORATION_ID = "corporationId";
  public static final String JSON_PROPERTY_DATA_SOURCE = "dataSource";
  public static final String JSON_PROPERTY_ACCESS_TOKEN = "accessToken";
  public static final String JSON_PROPERTY_TOKEN_TYPE = "tokenType";
  public static final String JSON_PROPERTY_SCOPE = "scope";
  public static final String JSON_PROPERTY_REFRESH_TOKEN = "refreshToken";
  public static final String JSON_PROPERTY_ASSETS_COUNT = "assetsCount";
  public static final String JSON_PROPERTY_WALLET_BALANCE = "walletBalance";
  public static final String JSON_PROPERTY_MINING_RESOURCES_ESTIMATED_VALUE = "miningResourcesEstimatedValue";
  public static final String JSON_PROPERTY_RACE_NAME = "raceName";
  public static final String JSON_PROPERTY_JWTOKEN = "jwtoken";

  private UUID uniqueCredential;
  private Integer accountId;
  private String accountName;
  private Integer corporationId;
  /**
   * The Eve Online data source for the ESI requests. It can differentiate between Production (tranquility server) and development and testing (singularity).
   */
  public enum DataSourceEnum {
    TRANQUILITY("tranquility"),
    
    SINGULARITY("singularity");

    private String value;

    DataSourceEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static DataSourceEnum fromValue(String value) {
      for (DataSourceEnum b : DataSourceEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private DataSourceEnum dataSource;
  private String accessToken;
  private String tokenType;
  private String scope;
  private String refreshToken;
  private Integer assetsCount;
  private Double walletBalance = null;
  private Double miningResourcesEstimatedValue = null;
  private String raceName;
  private String jwtoken;
}

