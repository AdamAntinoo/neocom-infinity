import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DataService } from './services/data-service.service';
import { HttpClientModule } from '@angular/common/http';
import { PlanetaryDataService } from './services/planetary-data.service';

@NgModule({
    imports: [
        BrowserModule,
        ReactiveFormsModule,
        BrowserAnimationsModule,
        FormsModule,
        HttpClientModule,
        AppRoutingModule
    ], declarations: [
        AppComponent
    ],
    providers: [
        { provide: DataService, useClass: DataService },
        { provide: PlanetaryDataService, useClass: PlanetaryDataService },
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
