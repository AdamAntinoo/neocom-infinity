// - DOMAIN
import { ICollaboration } from './ICollaboration.interface';

export interface IViewer {
    getViewer(): IViewer;
    enterSelected(node: ICollaboration);
    notifyDataChanged(): void;
    redirectPage(route: any): void;
}
