// - CORE
import { Component } from '@angular/core'
import { Input } from '@angular/core'
// - INNOVATIVE
import { IsolationService } from '@innovative/services/isolation.service';
import { BackgroundEnabledComponent } from '@innovative/components/background-enabled/background-enabled.component';
// - DOMAIN
// import { NeoCom } from '@domain/NeoCom.domain'
import { platformConstants } from '@env/platform-constants'
import { IViewer } from '@innovative/domain/interfaces/IViewer.interface';
import { ICollaboration } from '@innovative/domain/interfaces/ICollaboration.interface';
import { ISelectable } from '@domain/interfaces/ISelectable.interface';

@Component({
    selector: 'app-panel',
    templateUrl: './app-panel.component.html',
    styleUrls: ['./app-panel.component.scss']
})
export class AppPanelComponent extends BackgroundEnabledComponent implements IViewer {
    @Input() self: AppPanelComponent
    @Input() variant: string = platformConstants.DEFAULT_VARIANT
    protected downloading: boolean = true
    protected dataModelRoot: ICollaboration[] = []
    private renderNodeList: ICollaboration[] = []
    private target: ICollaboration
    // protected selection: SingleSelection = new SingleSelection();

    constructor() {
        super()
        this.self = this
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
    }
    // public addSelection(node: ISelectable): void {
    //     this.selection.addSelection(node)
    //     this.fireSelectionChanged();
    // }
    // public subtractSelection(node: ISelectable): void {
    //     if (this.selection.subtractSelection(node))
    //         this.fireSelectionChanged()
    // }
    /**
     * This is an abstract methods that should be implemented by panels that require to support selections.
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
