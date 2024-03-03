import { Observable } from "rxjs";

export interface HttpSecureServiceInterface{
  wrapHttpGet<T>(request: string): Promise<T>;
}
