/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.dimensinfin.eveonline.neocom.infinity.api;

import org.dimensinfin.eveonline.neocom.infinity.domain.BackendErrorDto;
import org.dimensinfin.eveonline.neocom.infinity.domain.V1MiningOperationDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
@Validated
@Tag(name = "Industry", description = "NeoCom Industry endpoints. Covers Character and Corporation if available.")
public interface IndustryApi {

    /**
     * GET /api/v3/neocom/character/miningoperations : Get the minings operations for current target character.
     * Gets the list of Esi Mining Operations that are generated automatically during mining. The target to be used is the capsuleer identifier or corporation identifier that is found on the access token. &lt;br&gt; The list  of operations is transformed to a hyperlink suitable frontend interpretation and operation items are given a unique key for easy identification of changes. &lt;br&gt; There is no persistence for this kind of data. 
     *
     * @param NEOCOM_TOKEN The access token to be used for authorization. This token will contain information to locate the credential to be used for data location. (required)
     * @return Success retrieving the list of Mining Operations. (status code 200)
     *         or Unauthorized. The current active access token is not present. The character identified thus cannot be accessed and the credential cannot be found. (status code 401)
     *         or Forbidden. The credential information is not valid to create a new access token and the authorization request is forbidden. (status code 403)
     */
    @Operation(
        operationId = "getMiningOperations",
        summary = "Get the minings operations for current target character.",
        description = "Gets the list of Esi Mining Operations that are generated automatically during mining. The target to be used is the capsuleer identifier or corporation identifier that is found on the access token. <br> The list  of operations is transformed to a hyperlink suitable frontend interpretation and operation items are given a unique key for easy identification of changes. <br> There is no persistence for this kind of data. ",
        tags = { "Industry" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Success retrieving the list of Mining Operations.", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = V1MiningOperationDto.class)))
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorized. The current active access token is not present. The character identified thus cannot be accessed and the credential cannot be found.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = BackendErrorDto.class))
            }),
            @ApiResponse(responseCode = "403", description = "Forbidden. The credential information is not valid to create a new access token and the authorization request is forbidden.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = BackendErrorDto.class))
            })
        },
        security = {
            @SecurityRequirement(name = "neocom_esi_auth")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/v3/neocom/character/miningoperations",
        produces = "application/json"
    )
    ResponseEntity<List<V1MiningOperationDto>> getMiningOperations(
        @NotNull @Parameter(name = "NEOCOM_TOKEN", description = "The access token to be used for authorization. This token will contain information to locate the credential to be used for data location.", required = true, in = ParameterIn.COOKIE) @CookieValue(name = "NEOCOM_TOKEN") String NEOCOM_TOKEN
    );

}