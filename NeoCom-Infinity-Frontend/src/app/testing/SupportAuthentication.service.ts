// - CORE
import { Injectable } from '@angular/core';
import { PlatformConstants } from '@env/PlatformConstants';
import { IsolationService } from '@innovative/services/isolation.service';
import * as jwt_decode from 'jwt-decode';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class SupportAuthenticationService {
    private validationResult: boolean = false;

    constructor(protected isolationService: IsolationService) { }

    public isAuthenticated(): boolean {
        console.log('isAuthenticated: ' + this.validationResult)
        return this.validationResult
        // const jwtTokenData = this.isolationService.getFromSession(platformConstants.JWTTOKEN_KEY)
        // if (jwtTokenData)
        //     return this.validateJwtToken(jwtTokenData)
        // return false
    }
    public setResult(result: boolean): void {
        console.log('SupportAuthenticationService: ' + result)
        this.validationResult = result
    }
    private validateJwtToken(encodedToken: string): boolean {
        return this.validationResult
    }

    // public isLoggedIn(): boolean {
    //     if (this.pass)
    //         return !this.isExpiredToken();
    //     else return false;
    // }
    // public isExpiredToken(): boolean {
    //     return this.expired;
    // }
    //    public setLogged(pass: boolean): void {
    //       this.pass = pass;
    //    }
    //    public setExpired(expired: boolean): void {
    //       this.expired = expired;
    //    }
}
