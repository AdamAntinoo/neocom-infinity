// - DOMAIN
import { ICollaboration } from './interfaces/ICollaboration.interface';
import { IExpandable } from './interfaces/IExpandable.interface';
import { INode } from './interfaces/INode.interface';

export class NeoCom implements INode, ICollaboration, IExpandable {
    protected jsonClass: string = 'NeoCom';
    protected expanded: boolean = false;

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

    // -  I C O L L A B O R A T I O N
    collaborate2View(): ICollaboration[] {
        return [];
    }
}
