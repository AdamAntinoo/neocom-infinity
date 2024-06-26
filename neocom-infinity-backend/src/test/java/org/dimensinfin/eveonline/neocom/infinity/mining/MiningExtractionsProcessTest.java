package org.dimensinfin.eveonline.neocom.infinity.mining;

public class MiningExtractionsProcessTest {

	//	private Credential credential;
	//	private ESIDataProvider esiDataProvider;
	//	private MiningRepository miningRepository;
	//	private LocationCatalogService locationCatalogService;
	//
	//	@BeforeEach
	//	public void beforeEach() {
	//		this.credential = Mockito.mock( Credential.class );
	//		this.esiDataProvider = Mockito.mock( ESIDataProvider.class );
	//		this.miningRepository = Mockito.mock( MiningRepository.class );
	//		this.locationCatalogService = Mockito.mock( LocationCatalogService.class );
	//	}
	//
	//	@Test
	//	public void buildComplete() {
	//		final MiningExtractionsProcess miningExtractionsProcess = new MiningExtractionsProcess.Builder()
	//				.withCredential( this.credential )
	//				.withEsiDataProvider( this.esiDataProvider )
	//				.withMiningRepository( this.miningRepository )
	//				.withLocationCatalogService( this.locationCatalogService )
	//				.addCronSchedule( "* - *" )
	//				.build();
	//		Assertions.assertNotNull( miningExtractionsProcess );
	//	}
	//
	//	@Test
	//	public void buildFailure() {
	//		Assertions.assertThrows( NullPointerException.class, () -> {
	//			final MiningExtractionsProcess miningExtractionsProcess = new MiningExtractionsProcess.Builder()
	//					.withCredential( null )
	//					.withEsiDataProvider( this.esiDataProvider )
	//					.withMiningRepository( this.miningRepository )
	//					.withLocationCatalogService( this.locationCatalogService )
	//					.build();
	//		} );
	//		Assertions.assertThrows( NullPointerException.class, () -> {
	//			final MiningExtractionsProcess miningExtractionsProcess = new MiningExtractionsProcess.Builder()
	//					.withEsiDataProvider( this.esiDataProvider )
	//					.withMiningRepository( this.miningRepository )
	//					.withLocationCatalogService( this.locationCatalogService )
	//					.build();
	//		} );
	//		Assertions.assertThrows( NullPointerException.class, () -> {
	//			final MiningExtractionsProcess miningExtractionsProcess = new MiningExtractionsProcess.Builder()
	//					.withCredential( this.credential )
	//					.withMiningRepository( this.miningRepository )
	//					.withLocationCatalogService( this.locationCatalogService )
	//					.build();
	//		} );
	//		Assertions.assertThrows( NullPointerException.class, () -> {
	//			final MiningExtractionsProcess miningExtractionsProcess = new MiningExtractionsProcess.Builder()
	//					.withCredential( this.credential )
	//					.withEsiDataProvider( this.esiDataProvider )
	//					.withLocationCatalogService( this.locationCatalogService )
	//					.build();
	//		} );
	//		Assertions.assertThrows( NullPointerException.class, () -> {
	//			final MiningExtractionsProcess miningExtractionsProcess = new MiningExtractionsProcess.Builder()
	//					.withCredential( this.credential )
	//					.withEsiDataProvider( this.esiDataProvider )
	//					.withMiningRepository( this.miningRepository )
	//					.build();
	//		} );
	//	}
	//
	//	@Test
	//	public void call() {
	//		// Given
	//		final List<GetCharactersCharacterIdMining200Ok> characterMiningExtractions = new ArrayList<>();
	//		// When
	//		Mockito.when( this.esiDataProvider.getCharactersCharacterIdMining( Mockito.any( Credential.class ) ) )
	//				.thenReturn( characterMiningExtractions );
	//		// Test
	//		final MiningExtractionsProcess miningExtractionsProcess = new MiningExtractionsProcess.Builder()
	//				.withCredential( this.credential )
	//				.withEsiDataProvider( this.esiDataProvider )
	//				.withMiningRepository( this.miningRepository )
	//				.withLocationCatalogService( this.locationCatalogService )
	//				.build();
	//		final Boolean obtained = miningExtractionsProcess.call();
	//		// Assertions
	//		Assertions.assertTrue( obtained );
	//	}
	//
	//	@Test
	//	public void getUniqueIdentifier() {
	//		final MiningExtractionsProcess miningExtractionsProcess = new MiningExtractionsProcess.Builder()
	//				.withCredential( this.credential )
	//				.withEsiDataProvider( this.esiDataProvider )
	//				.withMiningRepository( this.miningRepository )
	//				.withLocationCatalogService( this.locationCatalogService )
	//				.build();
	//		Assertions.assertEquals( -1215078148, miningExtractionsProcess.getUniqueIdentifier() );
	//	}
}
