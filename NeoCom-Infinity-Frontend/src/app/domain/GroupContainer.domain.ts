// - SERVICES
import { ICollaboration } from '@app/innovative-core/interfaces/ICollaboration.interface';
import { AppCoreStoreService } from '@app/innovative-core/services/AppCoreStoreService.service';
import { AppStoreService } from '@app/services/appstore.service';
// - DOMAIN
import { ESeparator } from '@domain/interfaces/EPack.enumerated';
import { IIconReference } from './interfaces/IIconReference.interface';
import { AssetGroupIconReference } from './interfaces/IIconReference.interface';
import { NeoComExpandable } from './NeoComExpandable.domain';
import { Separator } from './Separator.model';

export class GroupContainer extends NeoComExpandable {
    private id: number = -1;
    private title: string = '-G-CONTAINER-';
    private groupIcon: IIconReference = new AssetGroupIconReference('rookie_64.png');
    private contents: ICollaboration[] = [];

    constructor(values: Object = {}) {
        super();
        Object.assign(this, values);
        this.jsonClass = 'GroupContainer';
    }

    // - I C O L L A B O R A T I O N
    public collaborate2View(appModelStore?: AppCoreStoreService, variant?: string): ICollaboration[] {
        console.log('>[GroupContainer.collaborate2View]');
        let collab: any = []; // Initialize the list to be output.
        // Check if the Node is expanded or not.
        if (this.isExpanded()) {
            console.log('>[GroupContainer.collaborate2View]> Collaborating: ' + 'Separator.RED');
            collab.push(new Separator().setVariation(ESeparator.RED));
            console.log('>>[GroupContainer.collaborate2View]> Collaborating: ' + 'GroupContainer');
            collab.push(this);
            for (let partialnode of this.collaborateContents(this.contents, appModelStore, variant)) {
                collab.push(partialnode);
            }
            // collab.concat(this.collaborateContents(this.contents, appModelStore, variant));
            console.log(">[GroupContainer.collaborate2View]> Collaborating: " + "Separator.RED");
            collab.push(new Separator().setVariation(ESeparator.RED));
        } else {
            console.log('>>[GroupContainer.collaborate2View]>Collaborating: ' + 'GroupContainer');
            collab.push(this);
        }
        console.log('<[GroupContainer.collaborate2View]');
        return collab;
    }
    private collaborateContents(contentList: ICollaboration[],
        appModelStore: AppCoreStoreService,
        variant: string): ICollaboration[] {
        let collaboration = [];
        // Process each Location for new collaborations.
        for (let node of contentList) {
            let partialcollab = node.collaborate2View(appModelStore, variant);
            for (let partialnode of partialcollab) {
                collaboration.push(partialnode);
            }
        }
        return collaboration;
    }

    // - GETTERS & SETTERS
    public getId(): number {
        return this.id;
    }
    public getGroupTitle(): string {
        return this.title;
    }
    public getGroupIconReference(): string {
        return this.groupIcon.getReference();
    }
    public getContentsCount(): number {
        return this.contents.length;
    }
    public getContents(): ICollaboration[] {
        return this.contents;
    }
    public setId(newid: number): GroupContainer {
        this.id = newid;
        return this;
    }
    public setTitle(newtitle: string): GroupContainer {
        this.title = newtitle;
        return this;
    }
    public setGroupIcon(reference: IIconReference): GroupContainer {
        this.groupIcon = reference;
        return this;
    }
    public addContent(newcontent: ICollaboration): GroupContainer {
        this.contents.push(newcontent);
        return this;
    }
}
