// - CORE
import { Component } from '@angular/core';
import { Input } from '@angular/core';
import { Output } from '@angular/core';
import { EventEmitter } from '@angular/core';
// - DOMAIN
import { EVariant } from '@app/domain/interfaces/EPack.enumerated';
import { ICollaboration } from '@domain/interfaces/ICollaboration.interface';
import { IViewer } from '@domain/interfaces/IViewer.interface';

@Component({
    selector: 'app-panel',
    templateUrl: './app-panel.component.html',
    styleUrls: ['./app-panel.component.scss']
})
export class AppPanelComponent implements IViewer {
    @Input() self: AppPanelComponent;
    @Input() variant: string | EVariant = '-DEFAULT-';
    @Output() notifyChange = new EventEmitter();
    protected downloading: boolean = false;
    protected dataModelRoot: ICollaboration[] = [];
    private renderNodeList: ICollaboration[] = [];
    public completed: boolean = false;

    constructor() {
        this.self = this;
    }

    public isDownloading(): boolean {
        return this.downloading;
    }
    public completeDowload(): void {
        this.notifyDataChanged();
        this.downloading = false;
        this.completed = !this.completed;
    }
    public getNodes2Render(): ICollaboration[] {
        console.log(">[AppPanelComponent.getNodes2Render]> node count: " + this.renderNodeList.length);
        return this.renderNodeList;
    }

    public setVariant(variant: string | EVariant): void {
        this.variant = variant;
    }
    // - I V I E W E R
    public enterSelected(node: ICollaboration): void { }
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
            let nodes = node.collaborate2View(null, this.variant);
            console.log("-[AppPanelComponent.notifyDataChanged]> Collaborating " + nodes.length + " nodes.");
            // Add the collaborated nodes to the list of nodes to return.
            for (let childNode of nodes) {
                copyList.push(childNode);
            }
        }
        this.renderNodeList = copyList;
        // this.notifyChange.emit(this.renderNodeList.length);
        console.log("<[AppPanelComponent.notifyDataChanged]");
    }
    public applyPolicies(entries: ICollaboration[]): ICollaboration[] {
        return entries;
    }
    public redirectPage(route: any): void { }
}
