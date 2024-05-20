export * from './authorization.service';
import { AuthorizationService } from './authorization.service';
export * from './authorization.serviceInterface'
export * from './character.service';
import { CharacterService } from './character.service';
export * from './character.serviceInterface'
export const APIS = [AuthorizationService, CharacterService];
