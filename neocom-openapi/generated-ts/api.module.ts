import { DynamicModule, Module, Global } from '@nestjs/common';
import { HttpModule, HttpService } from '@nestjs/axios';
import { Configuration } from './configuration';

import { AuthorizationService } from './api/authorization.service';
import { CharacterService } from './api/character.service';

@Global()
@Module({
  imports:      [ HttpModule ],
  exports:      [
    AuthorizationService,
    CharacterService
  ],
  providers: [
    AuthorizationService,
    CharacterService
  ]
})
export class ApiModule {
    public static forRoot(configurationFactory: () => Configuration): DynamicModule {
        return {
            module: ApiModule,
            providers: [ { provide: Configuration, useFactory: configurationFactory } ]
        };
    }

    constructor( httpService: HttpService) { }
}
