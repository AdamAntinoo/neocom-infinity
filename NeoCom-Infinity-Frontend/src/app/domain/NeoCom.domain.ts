// - DOMAIN
import { INode } from './interfaces/INode.interface';
import { ICollaboration } from './interfaces/ICollaboration.interface';
import { IExpandable } from './interfaces/IExpandable.interface';
import { ISelectable } from './interfaces/ISelectable.interface';
import { IColorTheme } from './interfaces/IColorTheme.interface';
import { ESeparator } from './interfaces/EPack.enumerated';

export class NeoCom implements INode, ICollaboration, IExpandable, ISelectable/*, IColorTheme*/ {
    public jsonClass: string = 'NeoCom';
    protected expanded: boolean = false;
    protected selected: boolean = false;
    protected themeColor: ESeparator = ESeparator.WHITE;

    constructor(values: Object = {}) {
        Object.assign(this, values);
        this.jsonClass = 'NeoCom';
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

    // -  I C O L L A B O R A T I O N
    collaborate2View(): ICollaboration[] {
        return [this];
    }

    // - I E X P A N D A B L E
    public isExpandable(): boolean {
        return false;
    }
    public isExpanded(): boolean {
        return false;
    }
    public collapse(): boolean {
        return this.isExpanded();
    }
    public expand(): boolean {
        return this.isExpanded();
    }
    public toggleExpanded() {
        return this.isExpanded();
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
    public getThemeColor(): ESeparator{
        return this.themeColor;
    }
}