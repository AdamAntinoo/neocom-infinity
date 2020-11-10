import { ICollaboration } from '@domain/interfaces/ICollaboration.interface';

export class BaseNode implements ICollaboration {
    public jsonClass: string = 'BaseNode'
    // -  I C O L L A B O R A T I O N
    public collaborate2View(): ICollaboration[] {
        return [this];
    }
    public getJsonClass(): string {
        return this.jsonClass
    }
}
