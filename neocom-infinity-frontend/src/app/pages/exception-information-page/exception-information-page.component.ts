// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
// - SERVICES
import { AppStoreService } from '@app/services/appstore.service';
// - DOMAIN
import { environment } from '@env/environment';
import { NeoComException } from '@innovative/domain/NeoComException';

@Component({
    selector: 'exception-information-page',
    templateUrl: './exception-information-page.component.html',
    styleUrls: ['./exception-information-page.component.scss']
})
export class ExceptionInformationPageComponent implements OnInit {
    public exception: NeoComException;

    constructor(protected appStoreService: AppStoreService) { }

    ngOnInit() {
        this.exception = this.appStoreService.getLastInterceptedException();
    }
    public getExceptionType(): string {
        if (null != this.exception) return this.exception.statusText;
        else return 'UNDEFINED-EXCEPTION';
    }
    public getUserMessage(): string {
        if (null != this.exception) return this.exception.getUserMessage();
        else return '-';
    }
    public getLoginLink(): string {
        console.log('loginLinkData->' + environment.loginLinkData)
        return this.composeLoginLink()
    }
    public composeLoginLink(): string {
        let link: string = environment.loginLinkData.esi_host
        // - parameters
        link+='response_type='+environment.loginLinkData.parameters.response_type+'&'
        link+='client_id='+environment.loginLinkData.parameters.client_id+'&'
        link+='state='+environment.loginLinkData.parameters.state+'&'
        // - redirect
        link+='redirect_uri='+     encodeURIComponent  (environment.loginLinkData.callback_url)+'&'
        // - scope
        link+='scope='+     encodeURIComponent  (environment.loginLinkData.scope)

        return link
    }
    public retryable(): boolean {
        return true;
    }
}
