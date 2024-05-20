export * from './authorization.service';
import { AuthorizationService } from './authorization.service';
export * from './character.service';
import { CharacterService } from './character.service';
export const APIS = [AuthorizationService, CharacterService];
