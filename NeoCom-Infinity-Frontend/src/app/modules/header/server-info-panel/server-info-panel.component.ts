// - CORE
import { Component } from '@angular/core'
// - DOMAIN
import { ServerStatus } from '@domain/esi/ServerStatus.domain'
import { BackgroundEnabledComponent } from '@innovative/components/background-enabled/background-enabled.component'
import { IRefreshable } from '@innovative/domain/interfaces/IRefreshable.interface'
import { PublicService } from '@app/services/public.service'

@Component({
    selector: 'server-info-panel',
    templateUrl: './server-info-panel.component.html',
    styleUrls: ['./server-info-panel.component.scss']
})
export class ServerInfoPanelComponent extends BackgroundEnabledComponent implements IRefreshable {
    public serverInfo: ServerStatus
    public downloading: boolean = true

    constructor(protected publicService: PublicService) { super() }

    public ngOnInit() {
        this.downloading = true
        this.refresh()
    }

    // - I R E F R E S H A B L E
    public clean(): void { }
    public refresh(): void {
        this.clean()
        this.backendConnections.push(
            this.publicService.apiV1_GetServerStatus()
                .subscribe(info => {
                    console.log('[ServerInfoPanelComponent.refresh]>Server Startus version' + info.backendVersion)
                    this.serverInfo = info
                    this.downloading = false
                })
        )
    }

    // - G E T T E R S
    public getServerName(): string {
        if (null != this.serverInfo) return this.serverInfo.getServerName()
        else return '-'
    }
    public getServerStatus(): string {
        if (null != this.serverInfo) return "ONLINE".toUpperCase()
        else return "OFFLINE".toUpperCase()
    }
    public getServerCapsuleers(): number {
        if (null != this.serverInfo) return this.serverInfo.getPlayersCount()
        else return -1
    }
    public getStartedAgo(): string {
        if (null != this.serverInfo) return this.serverInfo.startAgo
        else return '-'
    }
    public getNextDowntime():string{
        if (null != this.serverInfo) return this.serverInfo.nextDowntime
        else return '-'
    }
}
