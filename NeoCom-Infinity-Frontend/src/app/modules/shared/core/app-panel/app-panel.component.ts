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

    constructor() {
        super()
        this.self = this
    }

    // - GETTERS & SETTERS
    public getVariant(): string {
        return this.variant
    }
    public isDownloading(): boolean {
        return this.downloading
    }
    public completeDowload(): void {
        this.notifyDataChanged()
        this.downloading = false
    }
    public getNodes2Render(): ICollaboration[] {
        console.log('>[AppPanelComponent.getNodes2Render]> Node count: ' + this.renderNodeList.length)
        return this.renderNodeList
    }
    public setVariant(variant: string): void {
        this.variant = variant
    }

    // - I V I E W E R
    public enterSelected(node: ICollaboration): void {
        this.target = node
    }
    /**
      Reconstructs the list of nodes to be rendered from the current DataRoot and their collaborations to the view.
      */
    public notifyDataChanged(): void {
        console.log(">[AppPanelComponent.notifyDataChanged]")
        let copyList = []
        // Get the initial list by applying the policies defined at the page to the initial root node contents. Policies may be sorting or filtering actions.
        let initialList = this.applyPolicies(this.dataModelRoot)
        // Generate the contents by collaborating to the view all the nodes.
        for (let node of initialList) {
            let nodes = node.collaborate2View(null, this.variant)
            console.log("-[AppPanelComponent.notifyDataChanged]> Collaborating " + nodes.length + " nodes.")
            // Add the collaborated nodes to the list of nodes to return.
            for (let childNode of nodes) {
                copyList.push(childNode)
            }
        }
        this.renderNodeList = copyList
        console.log("<[AppPanelComponent.notifyDataChanged]")
    }
    public applyPolicies(entries: ICollaboration[]): ICollaboration[] {
        return entries
    }
    public redirectPage(route: any): void { }
}
