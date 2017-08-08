//--- CORE MODULES
import { BrowserModule }  from '@angular/platform-browser';
import { NgModule }       from '@angular/core';
import { FormsModule }    from '@angular/forms';
import { HttpModule }     from '@angular/http';
import { JsonpModule }    from '@angular/http';
import { CookieModule }   from 'ngx-cookie';

//--- ROUTING
import { AppRoutingModule }   from './app-routing.module';
//--- DIRECTIVES
//--- SERVICES
import { PilotRoasterService }  from './services/pilot-roaster.service';
//--- PAGES
import { PageSplashComponent }  from './page/page-splash/page-splash.component';
import { PageLoginComponent }   from './page/page-login/page-login.component';
import { PageHomeComponent }    from './page/page-home/page-home.component';
import { PagePilotsComponent }        from './page/page-pilots/page-pilots.component';
//--- COMPONENTS
import { AppComponent }               from './app.component';
import { HeaderComponent }            from './component/header/header.component';
import { NavigationMenuComponent }    from './component/navigation-menu/navigation-menu.component';
import { PilotRoasterBlockComponent } from './component/pilot-roaster-block/pilot-roaster-block.component';
//--- PIPES
import { ISKNoDecimalsPipe } from './pipes/iskno-decimals.pipe';
import { CapitalizeLetterPipe } from './pipes/capitalize-letter.pipe';
import { PagePilotDetailComponent } from './page/page-pilot-detail/page-pilot-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    NavigationMenuComponent,
    PageSplashComponent,
    PageLoginComponent,
    PageHomeComponent,
    PagePilotsComponent,
    PilotRoasterBlockComponent,
    ISKNoDecimalsPipe,
    CapitalizeLetterPipe,
    PagePilotDetailComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    JsonpModule,
    CookieModule.forRoot(),
    AppRoutingModule
  ],
  providers: [PilotRoasterService],
  bootstrap: [AppComponent]
})
export class AppModule { }
