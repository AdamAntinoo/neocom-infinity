// - CORE
import { Injectable } from '@angular/core';
// - HTTP PACKAGE
import { ESIUniverseHttpWrapper } from './esiuniverse.httpwrapper.service';

@Injectable({
    providedIn: 'root'
})
export class ESIUniverseDataService {
    constructor ( protected http : ESIUniverseHttpWrapper){}

    // - U N I V E R S E
    
}
