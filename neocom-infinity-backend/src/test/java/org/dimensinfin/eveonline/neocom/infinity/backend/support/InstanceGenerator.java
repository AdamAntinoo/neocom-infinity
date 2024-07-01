package org.dimensinfin.eveonline.neocom.infinity.backend.support;

import java.util.Random;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.domain.EsiType;
import org.dimensinfin.eveonline.neocom.domain.IndustryGroup;
import org.dimensinfin.eveonline.neocom.domain.space.SpaceLocation;
import org.dimensinfin.eveonline.neocom.domain.space.SpaceSystemImplementation;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdBlueprints200Ok;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseCategoriesCategoryIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseConstellationsConstellationIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseGroupsGroupIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseRegionsRegionIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseSystemsSystemIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseTypesTypeIdOk;
import org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain.AuthorizationTokenResponse;

public class InstanceGenerator {
	public static final Integer TEST_ESITYPE_ID = 11535;
	public static final String TEST_ESITYPE_NAME = "Magnetometric Sensor Cluster";
	public static final String TEST_ESITYPE_GROUP_NAME = "Construction Components";
	public static final String TEST_ESITYPE_CATEGORY_NAME = "Commodity";
	public static final String TEST_ESITYPE_TYPEICON_URL = "https://image.eveonline.com/Type/11535_64.png";
	public static final IndustryGroup TEST_ESITYPE_INDUSTRYGROUP_NAME = IndustryGroup.COMMODITY;
	public static final String TEST_ESITYPE_HULLGROUP_NAME = "not-applies";
	public static final String TEST_ESITYPE_TECH = "Tech I";
	public static final Float TEST_ESITYPE_VOLUME = 0.1F;
	public static final Integer TEST_GROUP_ID = 54;
	public static final Integer TEST_CATEGORY_ID = 12;
	public static final Long TEST_STATION_LOCATION_ID = 60006907L;

	public SpaceLocation getSpaceLocation() {
		return new SpaceSystemImplementation.Builder()
				.withRegion( new GetUniverseRegionsRegionIdOk().regionId( 10000006 ).name( "REGION" ) )
				.withConstellation(
						new GetUniverseConstellationsConstellationIdOk().regionId( 10000006 ).constellationId( 20000006 ).name( "CONSTELLATION" ) )
				.withSolarSystem( new GetUniverseSystemsSystemIdOk().constellationId( 20000006 ).systemId( 2000004 ).name( "SYSTEM" ) )
				.build();
	}

	public AuthorizationTokenResponse generateAuthorizationTokenResponse() {
		return new AuthorizationTokenResponse.Builder()
			.withCredential( this.generateCredential() )
			.withJwtToken( "FFJJWWTT-TTKKEENNFF" )
			.withEsiToken( "-ESI-TOKEN-" )
			.build();
	}

	public Credential generateCredential() {
		return new Credential.Builder( TestDataConstants.CredentialConstants.TEST_ACCOUNT_ID )
			.withDataSource( TestDataConstants.CredentialConstants.TEST_DATASOURCE )
			.withAccessToken( TestDataConstants.CredentialConstants.TEST_ACCESSTOKEN )
			.withAccountName( TestDataConstants.CredentialConstants.TEST_ACCOUNT_NAME )
			.withCorporationId( 34 )
			.withRefreshToken( "ref" )
			.withScope( "scope" )
			.withTokenType( "-TYPE-" )
			.build();
	}
	public GetUniverseCategoriesCategoryIdOk getGetUniverseCategoriesCategoryIdOk() {
		return new GetUniverseCategoriesCategoryIdOk().categoryId( TEST_CATEGORY_ID ).name( TEST_ESITYPE_CATEGORY_NAME );
	}

	public GetUniverseGroupsGroupIdOk getGetUniverseGroupsGroupIdOk() {
		return new GetUniverseGroupsGroupIdOk().groupId( TEST_GROUP_ID ).name( TEST_ESITYPE_GROUP_NAME ).categoryId( TEST_CATEGORY_ID );
	}

	public GetUniverseTypesTypeIdOk getGetUniverseTypesTypeIdOk() {
		return new GetUniverseTypesTypeIdOk().typeId( TEST_ESITYPE_ID ).name( TEST_ESITYPE_NAME ).volume( TEST_ESITYPE_VOLUME );
	}
	public EsiType getEsiType() {
		return new EsiType.Builder()
				.withTypeId( 17464 )
				.withItemType( this.getGetUniverseTypesTypeIdOk() )
				.withCategory( this.getGetUniverseCategoriesCategoryIdOk() )
				.withGroup( this.getGetUniverseGroupsGroupIdOk() )
				.build();
	}
	public GetCharactersCharacterIdBlueprints200Ok getGetCharactersCharacterIdBlueprints200Ok(){
		return new GetCharactersCharacterIdBlueprints200Ok()
				.itemId(new Random().nextLong()  )
				.typeId( TEST_ESITYPE_ID )
				.locationId( TEST_STATION_LOCATION_ID )
				.materialEfficiency( 10 )
				.timeEfficiency( 10 )
				.runs( 20 );
	}

}
