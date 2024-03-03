import { Injectable, Logger } from "@nestjs/common";
import { HttpService, } from '@nestjs/axios';
import { AxiosResponse, AxiosError } from 'axios';
import { Observable, catchError, firstValueFrom } from "rxjs";
import { HttpServiceInterface } from "./http.service.interface";

@Injectable()
export class ESIHttpService implements HttpServiceInterface{
  private readonly logger = new Logger(ESIHttpService.name);
  constructor(private readonly httpService: HttpService) { }

  public async wrapHttpGet<T>(request: string): Promise<T> {
    const { data } = await firstValueFrom(
      this.httpService.get<T>(request).pipe(
        catchError((error: AxiosError) => {
          this.logger.error(error.response.data);
          throw 'An error happened!';
        }),
      ),
    );
    return data;
  }
}
