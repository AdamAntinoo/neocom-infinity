import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpClientWrapperService } from '@bit/innovative.innovative.innovative-core/dist/innovative-core/services/httpclientwrapper.service';
import { GeneratedResource } from '@domain/generated-resource';
import { PlanetaryDataRecord } from '@domain/planetary-data-record';
import { PlanetaryResource } from '@domain/planetary-resource';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
	providedIn: 'root'
})

export class PlanetaryDataService{
constructor () {
    // Initialize planetary data conversion structures.
}
}