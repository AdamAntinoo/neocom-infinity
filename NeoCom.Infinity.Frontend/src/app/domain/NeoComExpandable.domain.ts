// - DOMAIN
import { NeoCom } from './NeoCom.domain';
import { IExpandable } from '@domain/interfaces/IExpandable.interface';
import { ICollaboration } from './interfaces/ICollaboration.interface';
import { AppStoreService } from '@app/services/appstore.service';
import { EVariant } from './interfaces/EPack.enumerated';

export class NeoComExpandable extends NeoCom {
    // - I E X P A N D A B L E
    public isExpandable(): boolean {
        return true;
    }
    public isExpanded(): boolean {
        return this.expanded;
    }
    public collapse(): boolean {
        this.expanded = false;
        return this.expanded;
    }
    public expand(): boolean {
        this.expanded = true;
        return this.expanded;
    }
    public toggleExpanded() {
        this.expanded = !this.expanded;
        return this.expanded;
    }

    // -  I C O L L A B O R A T I O N
    // collaborate2View(appModelStore?: AppStoreService, variant?: string | EVariant): ICollaboration[] | NeoCom[]{
    //     return [];
    // }
}
