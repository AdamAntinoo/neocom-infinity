import { Module } from '@nestjs/common';
import { HttpModule } from '@nestjs/axios'
import { HttpService, } from '@nestjs/axios';
import { ESIAssetsDataAdapter } from './adapter/outbound/esi.assets.adapter';
import { ESISecureDataServiceAdapter } from './network/esi.secure.dataservice.adapter';
import { MockESIHttpSecureService } from './network/mock.esi.httpsecureservice';
import { DomainModule } from '../domain/domain.module';

@Module({
  imports: [
    HttpModule.register({
    timeout: 5000,
    maxRedirects: 5
  }),
DomainModule],
  providers: [ESIAssetsDataAdapter],
})
export class InfrastructureModule { }
