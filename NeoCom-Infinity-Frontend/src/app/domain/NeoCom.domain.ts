// - DOMAIN
import { INode } from './interfaces/INode.interface';
import { IExpandable } from './interfaces/IExpandable.interface';
import { ISelectable } from './interfaces/ISelectable.interface';
import { ESeparator } from './interfaces/EPack.enumerated';
import { ICollaboration } from '@innovative/domain/interfaces/ICollaboration.interface';

export class NeoCom implements INode, ICollaboration, IExpandable, ISelectable {
    public jsonClass: string = 'NeoCom';
    protected expanded: boolean = false;
    protected selected: boolean = false;
    protected themeColor: ESeparator = ESeparator.WHITE;

    constructor(values: Object = {}) {
        Object.assign(this, values)
        this.decode()
        this.jsonClass = 'NeoCom'
    }
    public decode(): void { 
        console.log('[Decode]')
    }

    protected isEmpty(target?: any): boolean {
        if (null == target) return true;
        if (Object.keys(target).length > 0) return false;
        return true;
    }

    // - I N O D E
    public getJsonClass(): string {
        return this.jsonClass;
    }
    public isActive(): boolean {
        return true;
    }
    // -  I C O L L A B O R A T I O N
    public collaborate2View(): ICollaboration[] {
        return [this];
    }

    // - I E X P A N D A B L E
    public isExpandable(): boolean {
        return false;
    }
    public isExpanded(): boolean {
        return this.expanded;
    }
    public collapse(): boolean {
        this.expanded = false;
        return this.isExpanded();
    }
    public expand(): boolean {
        this.expanded = true;
        return this.isExpanded();
    }
    public toggleExpanded() {
        this.expanded = !this.expanded;
        console.log('toggleExpanded: ' + this.expanded)
    }

    // - I S E L E C T A B L E
    public toggleSelected(): boolean {
        this.selected = !this.selected;
        return this.selected;
    }
    public isSelected(): boolean {
        if (this.selected) return true;
        else return false;
    }
    public select(): void {
        this.selected = true;
    }
    public unselect(): void {
        this.selected = false;
    }

    // - I M E N U
    public hasMenu(): boolean {
        return false;
    }

    // - I C O L O R
    public getThemeColor(): ESeparator {
        return this.themeColor;
    }
}
