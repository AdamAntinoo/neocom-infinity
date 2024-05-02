// - CORE
import { Component } from '@angular/core';
import { OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
// - DOMAIN
import { NeoComException } from '@innovative/domain/NeoComException';
import { OAuthEnabledComponent } from '@app/pages/OauthEnabled.component';

@Component({
    selector: 'background-enabled',
    templateUrl: './empty.html'
})
export class BackgroundOauthEnabledComponent extends OAuthEnabledComponent implements OnDestroy {
    public exception: NeoComException
    protected backendConnections: Subscription[] = [];
    /**
     * Unsubscribe from any open subscription made to the backend.
     */
    public ngOnDestroy(): void {
        this.backendConnections.forEach(element => {
            element.unsubscribe();
        });
    }
}
