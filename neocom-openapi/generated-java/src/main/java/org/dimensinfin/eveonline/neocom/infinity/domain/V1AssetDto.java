/*
 * NeoCom Infinity Backend Public API
 * # Summary ### New and latest NeoCom Api definitions to be used on the reviewed implementations for the new backend services. This API defines all the client REST endpoints public and available to other systems. The API definition follows the OpenApi 3.0 specification and there are provisioning for automatic code generation for this API clients. This specification describes the implemented API for the backend on the application **NeoCom.Infinity**. The end points described are expected to be used from the **Angular Frontend** application. The ouputs are formatted as JSON results and will be consumed by json compliant applications or by clients that will transform them to Java instances. The recommended json deserializer is GSON. For java transformation the JSON code could be used to generate the transformation code at the next link. **http://www.jsonschema2pojo.org/_**. # System Description The backend is a single standalone SpringBoot server but that publishes more than a single service. There are 3 main services at this version: * **neocom** - this is the authenticated set of endpoints that will collect and the data that can only be obtained when a Pilot is authenticated on the ESI services page. Some of the endpoints may also have a counterpart on the other services for public or data that can also be obtained with no authentication. * **public** - Endpoints that export some sets of data like Character or Market or any other section and that do no require authentication. * **universe** - Endpoints that reference universe or game data and not related to any of the previous or so generic and core that can be used from anywhere. There are endpoitns for locations, types and other game data structures. * **spacelocations** - This service will search for detailed data about a space location identifier and get the type, system, statios or structure and any other specific data that is related to the location. Wormholes are still unsupported as many of the out of common space coordinates. * **items** - This is the item database access service. It will add all sorts of market data to each of the available items. The exported data is being improved for HATEOAS support so now some of the endpoints will replace resolved esi data by an equivalent http link. This will help to reduce response times because there is no need to go to the ESI servers to get the data (this is specially severe when there are collections of data). The consumer application is really required that detailed data can follow the link to retrieve the additional information but this is done at the user selection pace and not preloading all possible data records.  This is most effective on long lists like assets or fittings. Now instead of resolving each of the item accesses and also getting each item market information the response will just report a link. Following that link we get the item and from it we can get any market additional data is really required. New NeoCom Infinity backend API. This is the complete API implementation for the backend services exposed, whatever the backend server that will provide the service. Initial implementations used the Java backend server and the ESI Public Data Services as a complementary source. New implementations have a dual backend service, part of it the original Java SpringBoot services and a new NestJS Typescript service that will implement new services and also hide the ESI Public sources into a HyperLink like DTO definitions. <br> Different api endpoints will be isolated with a version prefix so even they are all defined under the same path root the different version will be used by routers and proxies to fire the request to the correct backend implementation. ## CHANGES: * [20201226] *Introduced the new Market Data endpoints both for authenticated and universal access.* * [20240609] *Introduced the new endpoint separation by backed server. There are the neonest and the neospring servers.* ## TODO: * [20201019] *Replace old api endpoints by the new HAL endpoints and also document the corrent exception responses.* * [20191025] *Complete the definition for the NeoComExceptionResponse once I have the correct serialization data.* 
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
import org.dimensinfin.eveonline.neocom.infinity.domain.V1StackDto;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.constraints.*;
import jakarta.validation.Valid;

import jakarta.annotation.Generated;
import java.io.Serial;

/**
 * V1AssetDto
 */
@JsonPropertyOrder({
  V1AssetDto.JSON_PROPERTY_ID,
  V1AssetDto.JSON_PROPERTY_RESOURCE
})
@JsonTypeName("v1.Asset")
@lombok.Builder
@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class V1AssetDto {

  public static final String JSON_PROPERTY_ID = "id";
  public static final String JSON_PROPERTY_RESOURCE = "resource";

  private Integer id;
  private V1StackDto resource;
}

