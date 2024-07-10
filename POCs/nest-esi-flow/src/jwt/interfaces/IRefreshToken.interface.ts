import { ITokenBase } from './ITokenBase.interface';

export interface IRefreshPayload {
  tokenId: string;
}

export interface IRefreshToken extends IRefreshPayload, ITokenBase {}
