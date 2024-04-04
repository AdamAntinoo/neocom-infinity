// - DOMAIN
import { ISelectable } from '@innovative/domain/interfaces/ISelectable.interface';
import { ICollaboration } from './ICollaboration.interface'

export interface IViewer {
    enterSelected(node: ICollaboration): void
    notifyDataChanged(): void
    redirectPage(route: any): void
    updateSelection(node: ISelectable): void
    fireSelectionChanged(): void
    getSelection(): ISelectable[]
}
