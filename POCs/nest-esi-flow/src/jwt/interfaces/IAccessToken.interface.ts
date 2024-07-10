import { ITokenBase } from './ITokenBase.interface';

export interface IAccessPayload {
  id: number;
}

export interface IAccessToken extends IAccessPayload, ITokenBase {}
