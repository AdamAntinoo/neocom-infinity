import { Injectable } from "@angular/core";
import { ConfigurationPort } from "@app/ports/ConfigurationPort";

@Injectable({
    providedIn: 'root'
})
/** @deprecated */
export class ConfigurationAdapter implements ConfigurationPort {
    getNestBackendHost(): string {
        return 'http://localhost:5272/api/v3/neocom'
    }
}
