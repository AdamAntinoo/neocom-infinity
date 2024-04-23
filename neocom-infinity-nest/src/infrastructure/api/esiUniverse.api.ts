import { V1EsiTypeDto, V1MarketDataDto, V1SpaceLocationDto } from "neocom-domain";

export interface EsiUniverseApi {
    esiGetTypeInformation(typeId: number): Promise<V1EsiTypeDto>
    esiGetMarketData( params: any): Promise<V1MarketDataDto> 
    esiGetLocation( params: any): Promise<V1SpaceLocationDto> 
}
