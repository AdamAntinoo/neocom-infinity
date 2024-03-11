import { Injectable } from "@angular/core";
import { ConfigurationPort } from "@app/ports/ConfigurationPort";

@Injectable({
    providedIn: 'root'
})
export class ConfigurationAdapter implements ConfigurationPort {
    getNestBackendHost(): string {
        return 'https://localhost:9999'
    }
}
