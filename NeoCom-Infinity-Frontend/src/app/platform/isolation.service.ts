// - CORE
import { Injectable } from '@angular/core'
import { Inject } from '@angular/core'
import { HttpErrorResponse } from '@angular/common/http'
import { ToastrService } from 'ngx-toastr'
import * as jwt_decode from 'jwt-decode'
// - STORAGE
import { LOCAL_STORAGE } from 'ngx-webstorage-service'
import { SESSION_STORAGE } from 'ngx-webstorage-service'
import { StorageService } from 'ngx-webstorage-service'

/**
 * The responsibility for this service is to isolate the internal application api from the external implementation when using libraries that can change over time like the Toaster. It will also provide a simplification when accessing some common features like the environment, global functions or storage.
 */
@Injectable({
    providedIn: 'root'
})
export class IsolationService {
    constructor(
        @Inject(LOCAL_STORAGE) protected storage: StorageService,
        @Inject(SESSION_STORAGE) protected sessionStorage: StorageService,
        private notifier: ToastrService
    ) { }

    // - E X C E P T I O N S
    public processException(error: HttpErrorResponse): void {
        if (error.error.status == 404) {
            this.errorNotification('Endpoint [' + error.error.path + '] not found on server.', '404 NOT FOUND')
        } else {
            const errorName: string = error.error.errorName
            const httpStatus: string = error.error.httpStatus
            const message: string = error.error.message
            const cause: string = error.error.cause
            if (null != cause)
                this.errorNotification(message + '\nCausa: ' + cause, '[' + httpStatus + ']/' + errorName)
            else
                this.errorNotification(message, '[' + httpStatus + ']/' + errorName)
        }
    }

    // - R A M D O M
    public generateRandomNum(min: number, max: number) {
        return Math.floor(Math.random() * (max - min + 1)) + min;
    };
    public generateRandomString(length: number): string {
        var string = '';
        var letters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz' //Include numbers if you want
        for (let i = 0; i < length; i++) {
            string += letters.charAt(Math.floor(Math.random() * letters.length));
        }
        return string;
    }

    // - J W T
    public JWTDecode(token: string): any {
        return jwt_decode(token);
    }

    // - S T O R A G E
    public getFromStorage(_key: string): any {
        return this.storage.get(_key);
    }
    public setToStorage(_key: string, _content: any): any {
        return this.storage.set(_key, _content)
    }
    public removeFromStorage(_key: string): any {
        this.storage.remove(_key);
    }
    public getFromSession(_key: string): any {
        console.log('>[AppStoreService.getFromSession]> key: ' + _key);
        return this.sessionStorage.get(_key);
    }
    public setToSession(_key: string, _content: any): any {
        return this.sessionStorage.set(_key, _content)
    }
    public removeFromSession(_key: string): any {
        this.sessionStorage.remove(_key);
    }

    // - N O T I F I C A T I O N S
    private notifierConfiguration: any = {
        toastTimeout: 5000,
        newestOnTop: true,
        position: 'bottom-right',
        messageClass: 'notifier-message',
        titleClass: 'notifier-title',
        animate: 'slideFromLeft'
    };
    public successNotification(_message: string, _title?: string, _options?: any): void {
        // Join options configuration.
        let notConf;
        if (null != _options) notConf = { ...this.notifierConfiguration, ..._options };
        else notConf = this.notifierConfiguration;
        // this.notifier.successToastr(_message, _title, notConf);
    }
    public errorNotification(_message: string, _title?: string, _options?: any): void {
        // Join options configuration.
        let notConf;
        if (null != _options) notConf = { ...this.notifierConfiguration, ..._options };
        else notConf = this.notifierConfiguration;
        notConf.toastTimeout = 15000;
        // this.notifier.errorToastr(_message, _title, notConf);
    }
    public warningNotification(_message: string, _title?: string, _options?: any): void {
        // Join options configuration.
        let notConf;
        if (null != _options) notConf = { ...this.notifierConfiguration, ..._options };
        else notConf = this.notifierConfiguration;
        // this.notifier.warningToastr(_message, _title, notConf);
    }
    public infoNotification(_message: string, _title?: string, _options?: any): void {
        // Join options configuration.
        let notConf;
        if (null != _options) notConf = { ...this.notifierConfiguration, ..._options };
        else notConf = this.notifierConfiguration;
        // this.notifier.infoToastr(_message, _title, notConf);
    }

    // - U T I L I T I E S
    /**
 * Adds time to a date. Modelled after MySQL DATE_ADD function.
 * Example: dateAdd(new Date(), 'minute', 30)  //returns 30 minutes from now.
 * https://stackoverflow.com/a/1214753/18511
 *
 * @param date  Date to start with
 * @param interval  One of: year, quarter, month, week, day, hour, minute, second
 * @param units  Number of units of the given interval to add.
 */
    public dateAdd(date, interval, units) {
        var ret = new Date(date); //don't change original date
        var checkRollover = function () { if (ret.getDate() != date.getDate()) ret.setDate(0); };
        switch (interval.toLowerCase()) {
            case 'year': ret.setFullYear(ret.getFullYear() + units); checkRollover(); break;
            case 'quarter': ret.setMonth(ret.getMonth() + 3 * units); checkRollover(); break;
            case 'month': ret.setMonth(ret.getMonth() + units); checkRollover(); break;
            case 'week': ret.setDate(ret.getDate() + 7 * units); break;
            case 'day': ret.setDate(ret.getDate() + units); break;
            case 'hour': ret.setTime(ret.getTime() + units * 3600000); break;
            case 'minute': ret.setTime(ret.getTime() + units * 60000); break;
            case 'second': ret.setTime(ret.getTime() + units * 1000); break;
            default: ret = undefined; break;
        }
        return ret;
    }
}
