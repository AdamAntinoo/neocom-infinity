// - CORE
import { Component } from '@angular/core'
import { OnInit } from '@angular/core'
// - SERVICES
import { AppStoreService } from '@app/services/appstore.service'
// - DOMAIN
import { NeoComException } from '@innovative/domain/NeoComException'
import { OAuthEnabledComponent } from '../OauthEnabled.component'

@Component({
	selector: 'exception-information-page',
	templateUrl: './exception-information-page.component.html',
	styleUrls: ['./exception-information-page.component.scss'],
})
export class ExceptionInformationPageComponent extends OAuthEnabledComponent implements OnInit {
	public exception: NeoComException

	constructor(protected appStoreService: AppStoreService) {
		super()
	}

	ngOnInit() {
		this.exception = this.appStoreService.getLastInterceptedException()
	}
	public getExceptionType(): string {
		if (null != this.exception) return this.exception.statusText
		else return 'UNDEFINED-EXCEPTION'
	}
	public getUserMessage(): string {
		if (null != this.exception) return this.exception.getUserMessage()
		else return '-'
	}
	public retryable(): boolean {
		return true
	}
}
