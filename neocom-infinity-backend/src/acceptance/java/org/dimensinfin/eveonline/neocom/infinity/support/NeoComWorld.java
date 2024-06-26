package org.dimensinfin.eveonline.neocom.infinity.support;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.database.entities.MiningExtractionEntity;
import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.NewNeoComWorld;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.StoreCredentialRequest;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.StoreCredentialResponse;
import org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain.AuthorizationTokenRequest;
import org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain.AuthorizationTokenResponse;
import org.dimensinfin.eveonline.neocom.infinity.support.corporation.rest.v1.CorporationResponse;
import org.dimensinfin.eveonline.neocom.infinity.support.corporation.rest.v1.LocationAssetContainer;
import org.dimensinfin.eveonline.neocom.infinity.support.fitting.rest.v1.FittingResponse;
import org.dimensinfin.eveonline.neocom.infinity.support.neoitem.rest.v1.NeoItemTransport;
import org.dimensinfin.eveonline.neocom.infinity.support.pilot.rest.v1.PilotResponse;
import org.dimensinfin.eveonline.neocom.market.MarketData;

public class NeoComWorld extends NewNeoComWorld {
	private Integer itemIdentifier;
	private Integer regionId;
	private Integer typeId;
	private ResponseEntity<MarketData> marketDataResponseEntity;
	private HttpStatus httpStatusCode;
	private int httpStatusCodeValue;
	private AuthorizationTokenRequest authorizationTokenRequest;
	private AuthorizationTokenResponse authorizationTokenResponse;
	private Optional<Integer> corporationIdentifier = Optional.empty();
	private Optional<Integer> pilotIdentifier = Optional.empty();
	private ResponseEntity<CorporationResponse> corporationResponseEntity;
	private CorporationResponse corporationResponse;
	private ResponseEntity<PilotResponse> pilotResponseEntity;
	private PilotResponse pilotResponse;
	private ResponseEntity<List<FittingResponse>> fittingResponseEntity;
	private List<FittingResponse> fittingResponse;
	private List<LocationAssetContainer> corporationAssetsByLocation;
	private ResponseEntity<List<LocationAssetContainer>> listAssetContainersResponse;
	private Credential credential;
	private StoreCredentialRequest storeCredentialRequest;
	private ResponseEntity<StoreCredentialResponse> storeCredentialResponseResponseEntity;
	private StoreCredentialResponse storeCredentialResponse;
	private ResponseEntity<NeoItemTransport> neoItemBasicResponseEntity;
	private ResponseEntity<List<MiningExtractionEntity>> miningExtractionsResponseEntity;

	// - G E T T E R S   &   S E T T E R S
	public List<LocationAssetContainer> getCorporationAssetsByLocation() {
		return this.corporationAssetsByLocation;
	}

	public NeoComWorld setCorporationAssetsByLocation( final List<LocationAssetContainer> corporationAssetsByLocation ) {
		this.corporationAssetsByLocation = corporationAssetsByLocation;
		return this;
	}

	public Optional<Integer> getCorporationIdentifier() {
		return this.corporationIdentifier;
	}

	public NeoComWorld setCorporationIdentifier( final Integer corporationIdentifier ) {
		if (null != corporationIdentifier) this.corporationIdentifier = Optional.of( corporationIdentifier );
		return this;
	}

	public CorporationResponse getCorporationResponse() {
		return this.corporationResponse;
	}

	public NeoComWorld setCorporationResponse( final CorporationResponse corporationResponse ) {
		this.corporationResponse = corporationResponse;
		return this;
	}

	public ResponseEntity<CorporationResponse> getCorporationResponseEntity() {
		return this.corporationResponseEntity;
	}

	public NeoComWorld setCorporationResponseEntity( final ResponseEntity<CorporationResponse> corporationResponseEntity ) {
		this.corporationResponseEntity = corporationResponseEntity;
		this.httpStatusCodeValue = corporationResponseEntity.getStatusCodeValue();
		return this;
	}

	public Credential getCredential() {
		return this.credential;
	}

	public NeoComWorld setCredential( final Credential credential ) {
		this.credential = credential;
		return this;
	}

	public List<FittingResponse> getFittingResponse() {
		return this.fittingResponse;
	}

	public NeoComWorld setFittingResponse( final List<FittingResponse> fittingResponse ) {
		this.fittingResponse = fittingResponse;
		return this;
	}

	public ResponseEntity<List<FittingResponse>> getFittingResponseEntity() {
		return this.fittingResponseEntity;
	}

	public NeoComWorld setFittingResponseEntity( final ResponseEntity<List<FittingResponse>> fittingResponseEntity ) {
		this.fittingResponseEntity = fittingResponseEntity;
		return this;
	}

	public String getHttpStatusCode() {
		return this.httpStatusCode.name();
	}

	public NeoComWorld setHttpStatusCode( final HttpStatus httpStatusCode ) {
		this.httpStatusCode = httpStatusCode;
		return this;
	}

	public int getHttpStatusCodeValue() {
		return this.httpStatusCodeValue;
	}

	public NeoComWorld setHttpStatusCodeValue( final int httpStatusCodeValue ) {
		this.httpStatusCodeValue = httpStatusCodeValue;
		return this;
	}

	public Integer getItemIdentifier() {
		return this.itemIdentifier;
	}

	public NeoComWorld setItemIdentifier( final Integer itemIdentifier ) {
		this.itemIdentifier = itemIdentifier;
		return this;
	}

	public ResponseEntity<List<LocationAssetContainer>> getListAssetContainersResponse() {
		return this.listAssetContainersResponse;
	}

	public NeoComWorld setListAssetContainersResponse( final ResponseEntity<List<LocationAssetContainer>> listAssetContainersResponse ) {
		this.listAssetContainersResponse = listAssetContainersResponse;
		return this;
	}

	public ResponseEntity<MarketData> getMarketDataResponseEntity() {
		return this.marketDataResponseEntity;
	}

	public NeoComWorld setMarketDataResponseEntity( final ResponseEntity<MarketData> marketDataResponseEntity ) {
		this.marketDataResponseEntity = marketDataResponseEntity;
		return this;
	}

	public ResponseEntity<List<MiningExtractionEntity>> getMiningExtractionsResponseEntity() {
		return this.miningExtractionsResponseEntity;
	}

	public NeoComWorld setMiningExtractionsResponseEntity( final ResponseEntity<List<MiningExtractionEntity>> miningExtractionsResponseEntity ) {
		this.miningExtractionsResponseEntity = miningExtractionsResponseEntity;
		return this;
	}

	public ResponseEntity<NeoItemTransport> getNeoItemBasicResponseEntity() {
		return this.neoItemBasicResponseEntity;
	}

	public NeoComWorld setNeoItemBasicResponseEntity( final ResponseEntity<NeoItemTransport> neoItemBasicResponseEntity ) {
		this.neoItemBasicResponseEntity = neoItemBasicResponseEntity;
		return this;
	}

	public Optional<Integer> getPilotIdentifier() {
		return this.pilotIdentifier;
	}

	public NeoComWorld setPilotIdentifier( final Integer pilotIdentifier ) {
		if (null != pilotIdentifier) this.pilotIdentifier = Optional.of( pilotIdentifier );
		return this;
	}

	public PilotResponse getPilotResponse() {
		return this.pilotResponse;
	}

	public NeoComWorld setPilotResponse( final PilotResponse pilotResponse ) {
		this.pilotResponse = pilotResponse;
		return this;
	}

	public ResponseEntity<PilotResponse> getPilotResponseEntity() {
		return this.pilotResponseEntity;
	}

	public NeoComWorld setPilotResponseEntity( final ResponseEntity<PilotResponse> pilotResponseEntity ) {
		this.pilotResponseEntity = pilotResponseEntity;
		return this;
	}

	public Integer getRegionId() {
		return this.regionId;
	}

	public NeoComWorld setRegionId( final Integer regionId ) {
		this.regionId = regionId;
		return this;
	}

	public StoreCredentialRequest getStoreCredentialRequest() {
		return this.storeCredentialRequest;
	}

	public NeoComWorld setStoreCredentialRequest( final StoreCredentialRequest storeCredentialRequest ) {
		this.storeCredentialRequest = storeCredentialRequest;
		return this;
	}

	public StoreCredentialResponse getStoreCredentialResponse() {
		return this.storeCredentialResponse;
	}

	public NeoComWorld setStoreCredentialResponse( final StoreCredentialResponse storeCredentialResponse ) {
		this.storeCredentialResponse = storeCredentialResponse;
		return this;
	}

	public ResponseEntity<StoreCredentialResponse> getStoreCredentialResponseResponseEntity() {
		return this.storeCredentialResponseResponseEntity;
	}

	public NeoComWorld setStoreCredentialResponseResponseEntity( final ResponseEntity<StoreCredentialResponse> storeCredentialResponseResponseEntity ) {
		this.storeCredentialResponseResponseEntity = storeCredentialResponseResponseEntity;
		return this;
	}

	public Integer getTypeId() {
		return this.typeId;
	}

	public NeoComWorld setTypeId( final Integer typeId ) {
		this.typeId = typeId;
		return this;
	}

	public AuthorizationTokenRequest getValidateAuthorizationTokenRequest() {
		return this.authorizationTokenRequest;
	}

	public NeoComWorld setValidateAuthorizationTokenRequest( final AuthorizationTokenRequest authorizationTokenRequest ) {
		this.authorizationTokenRequest = authorizationTokenRequest;
		return this;
	}

	public AuthorizationTokenResponse getValidateAuthorizationTokenResponse() {
		return this.authorizationTokenResponse;
	}

	public NeoComWorld setValidateAuthorizationTokenResponse( final AuthorizationTokenResponse authorizationTokenResponse ) {
		this.authorizationTokenResponse = authorizationTokenResponse;
		return this;
	}
}
