import { Injectable, Logger } from "@nestjs/common";
import { HttpService, } from '@nestjs/axios';
import { AxiosResponse, AxiosError } from 'axios';
@Injectable()
export class ESIHttpService{
  get<T>(request: string) :any{
    throw new Error("Method not implemented.");
  }
 

}
