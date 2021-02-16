// - CORE
import { Injectable } from '@angular/core';
// - INOVATIVE
import { IsolationService } from '@innovative/services/isolation.service';
// - SERVICES
import { NeoComConstants } from '@app/platform/NeocomConstants.platform';
import { PlatformConstants } from '@env/PlatformConstants';

@Injectable({
    providedIn: 'root'
})
export class AuthenticationService {
    // - C O N S T R U C T O R
    constructor(protected isolationService: IsolationService) { }

    // - A U T H E N T I C A T I O N   A P I
    /**
     * This method verifies if the authentication token is present on the navigator session storage. If not present then it should trigger a new authentication flow.
     */
    public isAuthenticated(): boolean {
        const jwtTokenData = this.isolationService.getFromSession(PlatformConstants.JWTTOKEN_KEY)
        if (jwtTokenData)
            return this.validateJwtToken(jwtTokenData)
        return false
    }
    private validateJwtToken(encodedToken: string): boolean {
        if (this.JWTDecode2AccountName(encodedToken))
            if (this.JWTDecode2UniqueId(encodedToken))
                return true
        return false
    }

    // - J W T   D E C O D E
    /** @deprecated */
    public isLoggedIn(): boolean {
        const jwtToken = this.isolationService.getFromSession(PlatformConstants.JWTTOKEN_KEY);
        console.log('-[AuthenticationService.isLoggedIn]> jwtToken: ' + jwtToken);
        if (jwtToken)
            if (!this.isExpiredToken()) return true;
        return false;
    }
    /** @deprecated */
    public isExpiredToken(): boolean {
        console.log('-[AuthenticationService.isExpiredToken]> expiration time: ' +
            this.isolationService.getFromSession(
                PlatformConstants.JWTTOKEN_EXPIRATION_TIME_KEY));
        console.log('-[AuthenticationService.isExpiredToken]> expiration date: ' +
            new Date(this.isolationService.getFromSession(
                PlatformConstants.JWTTOKEN_EXPIRATION_TIME_KEY)));
        const expirationTime: number = +new Date(this.isolationService.getFromSession(
            PlatformConstants.JWTTOKEN_EXPIRATION_TIME_KEY));
        console.log('-[AuthenticationService.isExpiredToken]> expiration time: ' + expirationTime);
        if (Number.isNaN(expirationTime)) return true;
        const currentTime = +new Date();
        return (expirationTime < currentTime);
    }
    public storeJwtToken(newToken: string): void {
        this.isolationService.setToSession(PlatformConstants.JWTTOKEN_KEY, newToken);
        const expirationTime = this.isolationService.dateAdd(Date.now(), 'hour', 1);
        console.log('-[AuthenticationService.isExpiredToken]> setting expiration time: ' + expirationTime);
        this.isolationService.setToSession(PlatformConstants.JWTTOKEN_EXPIRATION_TIME_KEY, expirationTime);
    }
    /** @deprecated */
    public timestampJwtToken(deviation: number): void {
        const expirationTime = this.isolationService.dateAdd(this.isolationService.dateAdd(
            Date.now(), 'hour', 1),
            'second', deviation);
        this.isolationService.setToSession(PlatformConstants.JWTTOKEN_EXPIRATION_TIME_KEY, expirationTime);
    }
    public clearJwtToken(): void {
        this.isolationService.removeFromSession(PlatformConstants.JWTTOKEN_KEY);
        this.isolationService.removeFromSession(PlatformConstants.JWTTOKEN_EXPIRATION_TIME_KEY);
    }
    public JWTDecode2AccountName(codedToken: string): string {
        const token = this.isolationService.JWTDecode(codedToken);
        return token["accountName"];
    }
    public JWTDecode2UniqueId(codedToken: string): string {
        const token = this.isolationService.JWTDecode(codedToken);
        return token["uniqueId"];
    }
}
