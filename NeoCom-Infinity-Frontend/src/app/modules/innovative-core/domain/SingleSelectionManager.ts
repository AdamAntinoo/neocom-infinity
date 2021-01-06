// - DOMAIN
import { ISelectable } from '@innovative/domain/interfaces/ISelectable.interface';

export class SingleSelectionManager {
    private selection: ISelectable
    /**
     * If the new target node is a new selection then it replaces the current selection.
     * If the new target is unselected then we should test if the selection reference (that should point to the same node) is also unselected. Is so the we clear the selection. If the selection does not change we then do not change the selection contents.
     * @param node the new target node
     */
    public updateSelection(node: ISelectable): ISelectable {
        if (node.isSelected()) {
            this.selection.unselect()
            this.selection = node
        } else
            if (!this.selection.isSelected()) this.selection = undefined
        return this.selection
    }
    public getSelection(): ISelectable[] {
        return [this.selection]
    }
    public hasSelection(): boolean {
        if (this.selection) return true
        else return false
    }
}
