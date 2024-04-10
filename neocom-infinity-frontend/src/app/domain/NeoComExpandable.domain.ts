// - DOMAIN
import { NeoCom } from './NeoCom.domain';

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
}
