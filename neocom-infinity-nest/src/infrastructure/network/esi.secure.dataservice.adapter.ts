import { Injectable, Logger } from '@nestjs/common';
import { HttpService } from '@nestjs/axios';
import { AxiosResponse, AxiosError } from 'axios';
import { Observable, catchError, firstValueFrom } from 'rxjs';
import { HttpSecureServiceInterface as HttpSecureServiceInterface } from './http.secure.service.interface';
/**
 * ESI data provider is a set of swagger endpoint provided by CCP to get access to Eve Online capsuller data from inside the game. Most of those endpoints are secured and require a token oauth authentication prior to get access to the capsuleer data. On feature development this data will come from a mocked ESI service that will mimic the functionality we require for the MiningOperation.

This Infrastructure service relays on the Framework layer to make outbound http calls. This is the single element tied to the NestJs libraries dependencies.
 */
@Injectable()
export class ESISecureDataServiceAdapter implements HttpSecureServiceInterface {
  private readonly logger = new Logger(ESISecureDataServiceAdapter.name);
  constructor(private readonly httpService: HttpService) {}

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
