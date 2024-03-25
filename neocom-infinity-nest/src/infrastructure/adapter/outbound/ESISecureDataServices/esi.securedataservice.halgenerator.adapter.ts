import { Injectable } from "@nestjs/common"

@Injectable()
export class ESISecureDataServiceHALGeneratorAdapter {
    public getSystemLink(systemId: number): string {
        return 'https://esi.evetech.net/latest/universe/systems/'+systemId+'/?datasource=tranquility&language=en'
    }
    public getTypeLink(typeId:number):string{
        return 'https://esi.evetech.net/latest/universe/types/'+typeId+'/?datasource=tranquility&language=en'
    }
}
