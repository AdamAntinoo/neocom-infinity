//  PROJECT:     NeoCom.WS (NEOC.WS)
//  AUTHORS:     Adam Antinoo - adamantinoo.git@gmail.com
//  COPYRIGHT:   (c) 2017-2018 by Dimensinfin Industries, all rights reserved.
//  ENVIRONMENT: Angular 4
//  DESCRIPTION: Angular source code to run on a web server almost the same code as on the Android platform.
//               The project has 3 clear parts. One is the Java libraries that are common for all platforms,
//               the second is the java microservices that compose the web application backend made with
//               SpringBoot technology and finally the web ui code made in typescript within the Angular
//               framework.
//--- CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
//--- HTTP PACKAGE
import { Http } from '@angular/http';
// import { HttpClient } from '@angular/common/http';
import { Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Rx';
//--- ENVIRONMENT
import { environment } from '../../../environments/environment';
//--- ROUTER
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import 'rxjs/add/operator/switchMap';
//--- NOTIFICATIONS
// import { ToastsManager } from 'ng2-toastr/ng2-toastr';
//--- SERVICES
// import { OAuthService } from 'angular2-oauth2/oauth-service';
import { AuthConfig } from 'angular-oauth2-oidc';
import { OAuthService } from 'angular-oauth2-oidc';
import { JwksValidationHandler } from 'angular-oauth2-oidc';
// import { OAuthService, AuthConfig } from 'angular-oauth2-oidc';

// import { authConfig } from './authconfig.const';
import { AppModelStoreService } from '../../services/app-model-store.service';
//--- INTERFACES
// import { EVariant } from '../../classes/EVariant.enumerated';
// import { IDetailedEnabledPage } from '../../classes/IDetailedEnabledPage.interface';
// import { INeoComNode } from '../../classes/INeoComNode.interface';
//--- COMPONENTS
// import { BasePageComponent } from '../../components/core/base-page/base-page.component';
//--- MODELS
import { ESIConfiguration } from '../../models/ESI.Singularity';
// import { Credential } from '../../models/Credential.model';
// import { authConfig } from './auth.config';

@Component({
  selector: 'neocom-welcome-page',
  templateUrl: './welcome-page.component.html',
  styleUrls: ['./welcome-page.component.scss']
})
export class WelcomePageComponent {
  // //--- SINGULARITY CREDENTIALS
  // public static AUTHORIZATION_SERVER = "https://sisilogin.testeveonline.com/";
  // public static CLIENT_ID = "ddfafe24dcdb43e3ae964bf580636172";
  // public static SECRET_KEY = "kKWgfyzBVs2ra0wn8Hcwto0llWoeUgWz1P1FkPlb";
  // public static CALLBACK = "http://localhost:4200/validateauthorization";
  // public static CONTENT_TYPE = "application/json";
  // public static PECK = "ZWY2ODI5OGQ1ODJjNGRmZWJiNjc4ODZlMzBkMDg4YTg6emdQZnhPNjNRUFIyZUgxMXJOcGdQQUwwSjJtQjZmWko3SFFDcmkwcQ==";
  // public static SCOPE = "publicData esi-location.read_location.v1 esi-location.read_ship_type.v1 esi-mail.organize_mail.v1 esi-skills.read_skills.v1 esi-skills.read_skillqueue.v1 esi-wallet.read_character_wallet.v1 esi-characters.read_contacts.v1 esi-search.search_structures.v1 esi-clones.read_clones.v1 esi-universe.read_structures.v1 esi-killmails.read_killmails.v1 esi-assets.read_assets.v1 esi-planets.manage_planets.v1 esi-markets.structure_markets.v1 esi-characters.read_corporation_roles.v1 esi-location.read_online.v1 esi-fleets.read_fleet.v1 esi-ui.open_window.v1 esi-ui.write_waypoint.v1 esi-fittings.read_fittings.v1  esi-characters.read_standings.v1 esi-industry.read_character_jobs.v1 esi-markets.read_character_orders.v1 esi-characters.read_blueprints.v1 esi-contracts.read_character_contracts.v1 esi-clones.read_implants.v1 esi-characters.read_fatigue.v1 esi-characters.read_notifications.v1 esi-industry.read_character_mining.v1 esi-characters.read_fw_stats.v1";
  // public static AUTHORIZE_URL = WelcomePageComponent.AUTHORIZATION_SERVER + "oauth/authorize";
  // public static ACCESS_TOKEN_RESOURCE = WelcomePageComponent.AUTHORIZATION_SERVER + "oauth/token";

  public working: boolean = false;
  public code: string;

  constructor(protected appModelStore: AppModelStoreService
    , protected http: Http
    , private oauthService: OAuthService) {
    this.oauthService.showDebugInformation = true;
    // this.oauthService.userResponseType = 'code';
    this.oauthService.requestAccessToken = true;
    // Login-Url
    this.oauthService.loginUrl = ESIConfiguration.AUTHORIZE_URL; //Id-Provider?
    // URL of the SPA to redirect the user to after login
    this.oauthService.redirectUri = ESIConfiguration.CALLBACK;
    // The SPA's id. Register SPA with this id at the auth-server
    this.oauthService.clientId = ESIConfiguration.CLIENT_ID;
    // The name of the auth-server that has to be mentioned within the token
    this.oauthService.issuer = ESIConfiguration.AUTHORIZE_URL;
    // set the scope for the permissions the client should request
    this.oauthService.scope = ESIConfiguration.SCOPE;
    // set to true, to receive also an id_token via OpenId Connect (OIDC) in addition to the
    // OAuth2-based access_token
    this.oauthService.oidc = false;
    // Use setStorage to use sessionStorage or another implementation of the TS-type Storage
    // instead of localStorage
    this.oauthService.setStorage(sessionStorage);
    // To also enable single-sign-out set the url for your auth-server's logout-endpoint here
    this.oauthService.logoutUrl = ESIConfiguration.AUTHORIZATION_SERVER + "account/logoff";
  }

  public getDescription(): string {
    return "Welcome to the Infinity helper. Log in with your Eve Online credentials on the ESI login portal to get access to a capsuleer dedicated Industrialist Management System. Schedule complete Fittings and get all the Manufacture chain tasks to complete them on time."
  }
  public launchLogin() {
    console.log(">> [WelcomePageComponent.launchLogin]");
    // Show the validation spinning while we get the authorization credentials.
    this.working = true;
    // Start the OAuth flow.
    this.oauthService.initImplicitFlow();
    console.log("<< [WelcomePageComponent.launchLogin]");
  }
}
