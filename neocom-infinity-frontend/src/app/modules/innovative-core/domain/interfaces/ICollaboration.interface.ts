// - SERVICES
import { AppCoreStoreService } from '@innovative/services/AppCoreStoreService.service';

export interface ICollaboration {
    collaborate2View(appModelStore?: AppCoreStoreService, variant?: string): ICollaboration[]
}
