import { Module } from '@nestjs/common';
import { HttpModule } from '@nestjs/axios'
import { HttpService, } from '@nestjs/axios';
import { ESIAssetsDataAdapter } from './adapter/outbound/esi.assets.adapter';
import { ESIHttpService } from './network/esi.httpservice';
import { MockESIHttpService } from './network/mock.esi.httpservice';

@Module({
  imports: [HttpModule.register({
    timeout: 5000,
    maxRedirects: 5
  })],
  providers: [ESIAssetsDataAdapter, ESIHttpService,HttpService,MockESIHttpService],
})
export class InfrastructureModule { }
