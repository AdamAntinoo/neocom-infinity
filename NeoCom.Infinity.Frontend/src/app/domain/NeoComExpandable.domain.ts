// - DOMAIN
import { NeoCom } from './NeoCom.domain';
import { IExpandable } from '@domain/interfaces/IExpandable.interface';

export class NeoComExpandable extends NeoCom implements IExpandable {
    public expanded: boolean = false;

    // - I E X P A N D A B L E
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
