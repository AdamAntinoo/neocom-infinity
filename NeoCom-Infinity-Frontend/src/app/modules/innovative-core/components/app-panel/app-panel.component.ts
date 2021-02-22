// - CORE
import { Component } from '@angular/core'
import { Input } from '@angular/core'
// - INNOVATIVE
import { IsolationService } from '@innovative/services/isolation.service';
import { BackgroundEnabledComponent } from '@innovative/components/background-enabled/background-enabled.component';
// - DOMAIN
// import { NeoCom } from '@domain/NeoCom.domain'
import { PlatformConstants } from '@env/PlatformConstants'
import { IViewer } from '@innovative/domain/interfaces/IViewer.interface';
import { ICollaboration } from '@innovative/domain/interfaces/ICollaboration.interface';
import { ISelectable } from '@innovative/domain/interfaces/ISelectable.interface';
import { SingleSelectionManager } from '@innovative/domain/SingleSelectionManager';

@Component({
    selector: 'app-panel',
    templateUrl: './app-panel.component.html',
    styleUrls: ['./app-panel.component.scss']
})
export class AppPanelComponent extends BackgroundEnabledComponent implements IViewer {
    @Input() self: AppPanelComponent
    @Input() variant: string = PlatformConstants.DEFAULT_VARIANT
    protected downloading: boolean = true
    protected dataModelRoot: ICollaboration[] = []
    private renderNodeList: ICollaboration[] = []
    protected target: ICollaboration
    protected selectionManager: SingleSelectionManager = new SingleSelectionManager()

    constructor() {
        super()
        this.self = this
    }
    public ngOnInit(): void {
        console.log(">[AppPanelComponent.ngOnInit]");
        this.startDownloading();
        this.refresh();
        console.log("<[AppPanelComponent.ngOnInit]");
    }
    public refresh(): void {
        console.log(">[AppPanelComponent.refresh]");
    }
    // - G E T T E R S
    public getVariant(): string {
        return this.variant
    }
    public setVariant(variant: string): void {
        this.variant = variant
    }
    public isDownloading(): boolean {
        return this.downloading
    }
    public getNodes2Render(): ICollaboration[] {
        return this.renderNodeList;
    }
    public startDownloading(): void {
        this.downloading = true;
    }
    public completeDowload(nodes: ICollaboration[]): void {
        console.log('>[AppPanelComponent.completeDowload]')
        this.dataModelRoot = nodes;
        this.notifyDataChanged();
        this.downloading = false;
        console.log('<[AppPanelComponent.completeDowload]> Nodes processed: ' + this.dataModelRoot.length)
    }

    // - I V I E W E R
    public enterSelected(node: ICollaboration): void {
        this.target = node
        this.fireSelectionChanged()
    }
    /**
     * Depending on the selector managet this method will add/remove the node from the selection or completely replace the selection contents. The default Selectormanager is a single selection so a new selected node will replace the previous one and a unselect of the current selected node will empty the selection.
     * @param node The node to update the selection. If the node is unselected then it is removed from the selection. If selected then it is added.
     */
    public updateSelection(node: ISelectable): void {
        this.selectionManager.updateSelection(node)
        this.fireSelectionChanged()
    }
    public getSelection(): ISelectable[] {
        return this.selectionManager.getSelection()
    }
   /**
     * This is an abstract method that should be implemented by panels that require to support selections.
     */
    public fireSelectionChanged(): void { }
    /**
      Reconstructs the list of nodes to be rendered from the current DataRoot and their collaborations to the view.
      */
    public notifyDataChanged(): void {
        console.log(">[AppPanelComponent.notifyDataChanged]");
        let copyList = [];
        // Get the initial list by applying the policies defined at the page to the initial root node contents. Policies may be sorting or filtering actions.
        let initialList = this.applyPolicies(this.dataModelRoot);
        // Generate the contents by collaborating to the view all the nodes.
        for (let node of initialList) {
            let nodes = node.collaborate2View(this.variant);
            if (null != nodes) {
                console.log("-[AppPanelComponent.notifyDataChanged]> Collaborating " + nodes.length + " nodes.");
                // Add the collaborated nodes to the list of nodes to return.
                for (let childNode of nodes) {
                    copyList.push(childNode);
                }
            }
        }
        this.renderNodeList = copyList;
        console.log("<[AppPanelComponent.notifyDataChanged]");
    }
    public applyPolicies(entries: ICollaboration[]): ICollaboration[] {
        return entries;
    }
    public redirectPage(route: any): void { }
}
