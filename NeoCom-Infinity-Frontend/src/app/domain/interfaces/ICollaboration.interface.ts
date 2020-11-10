// - SERVICES
import { AppStoreService } from '@app/services/appstore.service';
// - DOMAIN
import { NeoCom } from '../NeoCom.domain';

export interface ICollaboration {
    collaborate2View(appModelStore?: AppStoreService, variant?: string): ICollaboration[] | NeoCom[];
}
