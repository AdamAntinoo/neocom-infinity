// - SERVICES
import { AppStoreService } from '@app/services/appstore.service';
// - DOMAIN
import { NeoCom } from '../NeoCom.domain';
import { EVariant } from './EPack.enumerated';

export interface ICollaboration {
    collaborate2View(appModelStore?: AppStoreService, variant?: string | EVariant): ICollaboration[] | NeoCom[];
}
