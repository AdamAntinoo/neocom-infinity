import { Observable } from "rxjs";

export interface HttpServiceInterface{
  wrapHttpGet<T>(request: string): Promise<T>;
}
