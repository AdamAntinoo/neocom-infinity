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
import { Component, OnInit } from '@angular/core';
import { Injectable } from '@angular/core';
import { Inject } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
//--- HTTP PACKAGE
import { Http } from '@angular/http';
import { Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Rx';
// Import RxJs required methods
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
//--- INTERFACES
import { PageComponent } from 'src/app/classes/PageComponent';
// import { AppModelStoreMockService } from 'src/app/modules/app-modelstore/services/app-modelstore-mock.service';
//--- MODELS
import { Credential } from 'src/app/models/Credential.model';
import { NeoComNode } from 'src/app/models/NeoComNode.model';
import { NeoComCharacter } from 'src/app/models/NeoComCharacter.model';
import { Pilot } from 'src/app/models/Pilot.model';
import { Fitting } from 'src/app/models/Fitting.model';

import { Corporation } from 'src/app/models/Corporation.model';
import { Manager } from 'src/app/models/Manager.model';
import { AssetsManager } from 'src/app/models/AssetsManager.model';
import { PlanetaryManager } from 'src/app/models/PlanetaryManager.model';
import { ProcessingAction } from 'src/app/models/ProcessingAction.model';
import { Separator } from 'src/app/models/Separator.model';
import { Login } from 'src/app/models/Login.model';

/**
This service will store persistent application data and has the knowledge to get to the backend to retrieve any data it is requested to render on the view.
*/
@Injectable()
export class AppModelStoreService /*extends AppModelStoreMockService*/ {
  static APPLICATION_NAME: string = "NeoCom-MS";
  static APPLICATION_VERSION: string = "v 0.11.0"
  static APPLICATION_SERVICE_PORT = "9000";
  static RESOURCE_SERVICE_URL: string = "http://localhost:" + AppModelStoreService.APPLICATION_SERVICE_PORT + "/api/v1";

  private _credentialList: Credential[] = null; // List of Credential data. It also includes the Pilotv1 information.
  private _currentCharacter: Pilot = null; // The current active character

  private _loginList: Login[] = null; // List of Login structures to be used to aggregate Keys
  private _currentLogin: Login = null; // The current Login active.
  private _lastViewer: PageComponent = null;

  constructor(protected http: Http, protected router: Router) {
    // super();
  }

  //--- C O M M O N    C A L L S
  public getApplicationName(): string {
    return AppModelStoreService.APPLICATION_NAME;
  }
  public getApplicationVersion(): string {
    return AppModelStoreService.APPLICATION_VERSION;
  }

  //--- S T O R E   F I E L D S    A C C E S S O R S
  //--- C R E D E N T I A L    S E C T I O N
	/**
	This gets the list of Credentials that are the first interaction with the user to select with what character we like to continue the rest of the intereactions. If the Credential list is empty we return a node with an activation button to add new credentials while we go to the backend to get an updated list of the database stored credentials.
	*/
  public accessCredentialList(): Observable<Credential[]> {
    console.log("><[AppModelStoreService.accessCredentialList]");
    if (null == this._credentialList) {
      // Initialize the list with the default "new credential" button.
      //		this._credentialList.push(new )
      // Get the list form the backend Database.
      return this.getBackendCredentialList()
        .map(result => {
          this._credentialList = result;
          return this._credentialList;
        });
    } else
      return new Observable(observer => {
        setTimeout(() => {
          observer.next(this._credentialList);
        }, 500);
        setTimeout(() => {
          observer.complete();
        }, 500);
      });
  }
  public accessPilotFittings(pilotId: number): Observable<Fitting[]> {
    console.log("><[AppModelStoreService.accessPilotFittings]");
    // if (null == this._credentialList) {
    // Initialize the list with the default "new credential" button.
    //		this._credentialList.push(new )
    // Get the list form the backend Database.
    return this.getBackendPilotFittings(pilotId)
      .map(result => {
        // this._credentialList = result;
        return result;
      });
    // } else
    //   return new Observable(observer => {
    //     setTimeout(() => {
    //       observer.next(this._credentialList);
    //     }, 500);
    //     setTimeout(() => {
    //       observer.complete();
    //     }, 500);
    //   });
  }

  // --- P I L O T   S E C T I O N
	/**
	Sets the current Pilot selected to the identifier received as a parameter. The selection requires the search for the character on the list of Pilots that should be related to the list of Credentials.
	*/
  public activatePilotById(id: number): Pilot {
    // Check if the Pilot requested is already the one selected.
    if (null != this._currentCharacter)
      if (this._currentCharacter.characterID === id) return this._currentCharacter

    // Search the identifier on the list of Credentials.
    if (null == this._credentialList) this.router.navigate(['/credentials']);
    for (let credential of this._credentialList) {
      if (credential.getAccountId() == id) {
        this._currentCharacter = credential.getPilot();
      }
    }
    // if (null != this._currentLogin) {
    //   this._currentCharacter = this._currentLogin.accessCharacterById(id);
    // }
    if (null == this._currentCharacter) {
      this.router.navigate(['/credentials']);
    }
    return this._currentCharacter;
  }
  // /**
  // If the list is empty go to the backend and get a new list. Otherwise return the current list. The call to the backend and being the list owned by the same service gets updated with the returned result but this is not the common result of backend access operations.
  // */
  // public accessLoginList(): Observable<Login[]> {
  //   console.log("><[AppModelStoreService.accessLoginList]");
  //   if (null == this._loginList) {
  //     // Get the list form the backend Database.
  //     return this.getBackendLoginList()
  //       .map(result => {
  //         this._loginList = result;
  //         return result;
  //       });
  //   } else
  //     return new Observable(observer => {
  //       setTimeout(() => {
  //         observer.next(this._loginList);
  //       }, 500);
  //       setTimeout(() => {
  //         observer.complete();
  //       }, 500);
  //     });
  // }
  // public setLoginList(newlist: Login[]): Login[] {
  //   this._loginList = newlist;
  //   return this._loginList;
  // }




  //--- B A C K E N D    C A L L S
	/**
	Go to the backend Database to retrieve the list of declared Credentials to let the user to select the one he/she wants for working. If the list is already downloaded then do not access again the Database and return the cached list.
	*/
  public getBackendCredentialList(): Observable<Credential[]> {
    console.log("><[AppModelStoreService.getBackendCredentialList]");
    let request = AppModelStoreService.RESOURCE_SERVICE_URL + "/credentials";
    return this.http.get(request)
      .map(res => res.json())
      .map(result => {
        console.log("--[AppModelStoreService.getBackendCredentialList]> Processing response.");
        // Process the result into a set of Logins or process the Error Message if so.
        //	let constructionList: NeoComNode[] = [];
        // Process the resulting hash array into a list of transformed nodes.
        this._credentialList = this.transformRequestOutput(result);

        //	this._loginList = constructionList
        console.log("<<[AppModelStoreService.getBackendCredentialList]> Processed: " + this._credentialList.length);
        return this._credentialList;
      });
  }
  /**
  Get the list of fittings for the selected character. This list is not cached at the Application Model but is required should be cached at the Credential level and then control there is the backend service should be called.
  */
  public getBackendPilotFittings(pilotId: number): Observable<Fitting[]> {
    console.log("><[AppModelStoreService.getBackendPilotFittings]");
    let request = AppModelStoreService.RESOURCE_SERVICE_URL + "/pilot/" + pilotId + "/fittingmanager/fittings";
    return this.http.get(request)
      .map(res => res.json())
      .map(result => {
        console.log("<<[AppModelStoreService.getBackendPilotFittings]> Processed: " + this._credentialList.length);
        return this.transformRequestOutput(result);
      });
  }
  private transformRequestOutput(result): any[] {
    let results: NeoComNode[] = [];
    for (let key in result) {
      // Access the object into the spot.
      let node = result[key];
      // Check that we have an Action on the spot.
      if (node.jsonClass == "Credential") {
        let convertedCredential = new Credential(node);
        console.log("--[AppModelStoreService.transformRequestOutput]> Credential node: " + convertedCredential.getAccountId());
        results.push(convertedCredential);
      }
      if (node.jsonClass == "Fitting") {
        let convertedFitting = new Fitting(node);
        console.log("--[AppModelStoreService.transformRequestOutput]> Identified Fitting node: " + convertedFitting.getShipName());
        results.push(convertedFitting);
      }
    }
    return results;
  }






	/**
	Go to the backend Database to retrieve the list of declared Logins to let the user to select the one he/she wants for working. If the list is already downloaded then do not access again the Database and return the cached list.
	*/
  public getBackendLoginList(): Observable<Login[]> {
    console.log("><[AppModelStoreService.getBackendLoginList]");
    let request = AppModelStoreService.RESOURCE_SERVICE_URL + "/loginlist";
    return this.http.get(request)
      .map(res => res.json())
      .map(result => {
        console.log("--[AppModelStoreService.getBackendLoginList]> Processing response.");
        // Process the result into a set of Logins or process the Error Message if so.
        let constructionList: Login[] = [];
        // Process the resulting hash array into a list of Logins.
        for (let key in result) {
          // Access the object into the spot.
          let node = result[key];
          // Check that we have an Action on the spot.
          if (node.jsonClass == "Login") {
            let convertedLogin = new Login(node);
            console.log("--[AppModelStoreService.getBackendLoginList]> Identified Login node: " + convertedLogin.getLoginId());
            constructionList.push(convertedLogin);
          }
        }
        this._loginList = constructionList
        console.log("<<[AppModelStoreService.getBackendLoginList]> Processed: " + this._loginList.length);
        return constructionList;
      });
  }
	/**
	The initial version only reported the Managers but seems more effective to retieve the complete Character with theis Managers initialized.
	*/
  public getBackendPilotDetailed(loginid: string, characterid: number): Observable<NeoComCharacter> {
    console.log("><[AppModelStoreService.getBackendPilotManagerList]>Characterid = " + characterid);
    //  let loginid = this.accessLogin().getLoginId();
    return this.http.get(AppModelStoreService.RESOURCE_SERVICE_URL + "/login/" + loginid + "/pilot/" + characterid)
      .map(res => res.json())
      .map(result => {
        let newnode = null;
        switch (result.jsonClass) {
          case "Corporation":
            newnode = new Corporation(result);
            break;
          case "Pilot":
            newnode = new Pilot(result);
            break;
          default:
            newnode = result;
            break;
        }
        return newnode;
      });
  }
  public getBackendPilotManagers(loginid: string, characterid: number): Observable<Manager[]> {
    return this.http.get(AppModelStoreService.RESOURCE_SERVICE_URL + "/login/" + loginid + "/pilot/" + characterid + "/pilotmanagers")
      .map(res => res.json())
      .map(result => {
        let managerList = [];
        for (let manager of result) {
          let newman = null;
          switch (manager.jsonClass) {
            case "AssetsManager":
              newman = new AssetsManager(manager);
              break;
            case "PlanetaryManager":
              newman = new PlanetaryManager(manager);
              break;
            default:
              newman = new Manager(manager);
              break;
          }
          managerList.push(newman);
        }
        return managerList;
      });
  }
  public getBackendPilotPlanetaryManager(characterid: number): Observable<Manager> {
    console.log("><[AppModelStoreService.getBackendPilotManagerList]>Characterid = " + characterid);
    let loginid = this.accessLogin().getLoginId();
    return this.http.get(AppModelStoreService.RESOURCE_SERVICE_URL + "/login/" + loginid + "/pilot/" + characterid + "/planetarymanager")
      .map(res => res.json())
      .map(result => {
        if (result.jsonClass == "PlanetaryManager") {
          let manager = new PlanetaryManager(result);
          return manager;
        } else return result;
      });
  }
  public getBackendPlanetaryOptimizedScenario(locid: number): Observable<ProcessingAction[]> {
    console.log("><[AppModelStoreService.getBackendPilotRoaster]>Loginid = " + locid);
    // Get the current Login identifier and the current Character identifier to be used on the HTTP request.
    let loginid = this._currentLogin.getLoginId();
    let characterid = this._currentCharacter.getId();
    //  this.cookieService.put("login-id", "default")
    let request = AppModelStoreService.RESOURCE_SERVICE_URL + "/login/" + loginid;
    request += "/pilot/" + characterid;
    request += "/planetarymanager/location/" + locid + "/optimizeprocess";
    return this.http.get(request)
      .map(res => res.json())
      .map(result => {
        let actionList: any[] = [];
        // Process the resulting hash array into a list of ProcessingActions.
        for (let key in result) {
          // Access the object into the spot.
          let action = result[key];
          // Check that we have an Action on the spot.
          if (action.jsonClass == "ProcessingAction") {
            let convertedAction = new ProcessingAction(action);
            actionList.push(convertedAction);
            actionList.push(new Separator());
          }
        }
        return actionList;
      });
  }
  public getBackendPilotAssetsManager(characterid: number): Observable<Manager> {
    console.log("><[AppModelStoreService.getBackendPilotAssetsManager]>Characterid = " + characterid);
    let loginid = this.accessLogin().getLoginId();
    return this.http.get(AppModelStoreService.RESOURCE_SERVICE_URL + "/login/" + loginid + "/pilot/" + characterid + "/assetsmanager")
      .map(res => res.json())
      .map(result => {
        if (result.jsonClass == "AssetsManager") {
          let manager = new AssetsManager(result);
          return manager;
        } else return result;
      });
  }
  public getBackendLocationsContents(locationid: number): Observable<NeoComNode[]> {
    // console.log("><[AppModelStoreService.getBackendLocationsContents]> LOginid = " + loginid);
    // console.log("><[AppModelStoreService.getBackendLocationsContents]> Characterid = " + characterid);
    console.log("><[AppModelStoreService.getBackendLocationsContents]> Locationid = " + locationid);
    let loginid = this.accessLogin().getLoginId();
    let pilot = this.accessCharacter();
    return this.http.get(AppModelStoreService.RESOURCE_SERVICE_URL + "/login/" + loginid + "/pilot/" + pilot.getCharacterId() + "/assetsmanager/location/" + locationid + "/downloadcontents")
      .map(res => res.json())
      .map(result => {
        return result;
      });
  }
  public getBackendContainerContents(containerid: number): Observable<NeoComNode[]> {
    console.log("><[AppModelStoreService.getBackendContainerContents]> Locationid = " + containerid);
    let loginid = this.accessLogin().getLoginId();
    let pilot = this.accessCharacter();
    return this.http.get(AppModelStoreService.RESOURCE_SERVICE_URL + "/login/" + loginid + "/pilot/" + pilot.getCharacterId() + "/assetsmanager/container/" + containerid + "/downloadcontents")
      .map(res => res.json())
      .map(result => {
        return result;
      });
  }


  // /**
  // This method was recursive and that seemed to generate some inconsistencies. Removed.
  // */
  // public activateLoginById(newloginid: string): Observable<Login> {
  //   console.log("><[AppModelStoreService.activateLoginById]");
  //   if (null == this._loginList) {
  //     // We have run all the list and we have not found any Login with the right id. We should trigger an exception.
  //     throw new TypeError("Login identifier " + newloginid + " not found. Cannot select that login");
  //   }
  //   // We are sure that the list is present.
  //   // Search for the parameter login id.
  //   for (let lg of this._loginList) {
  //     if (lg.getLoginId() == newloginid) {
  //       this._currentLogin = lg;
  //       return new Observable(observer => {
  //         setTimeout(() => {
  //           observer.next(this._currentLogin);
  //         }, 500);
  //         setTimeout(() => {
  //           observer.complete();
  //         }, 500);
  //       });
  //     }
  //   }
  // }
	/**
	Sets the new login that comes from the URL when the user selects one from the list of logins.
	If the Login set is different from the current Login then we fire the download of
	the list of Pilots associated with that Login's Keys.
	*/
  public accessLoginById(newloginid: string): Login {
    // Check if the Login is not set.
    if (null == this._currentLogin) this._currentLogin = this.setLoginById(newloginid);
    // Check if the required login is already the active Login.
    if (this._currentLogin.getLoginId() == newloginid) return this._currentLogin;
    else return this.setLoginById(newloginid);
  }
  public setLoginById(newloginid: string): Login {
    // WARNING. This method can fail if the list is empty because of the asynch of the backend.
    if (null == this._loginList) {
      this.getBackendLoginList()
        .subscribe(result => {
          console.log("--[AppModelStoreService.accessLoginById.getBackendLoginList]>LoginList: " + JSON.stringify(result));
          // The the list of planetary resource lists to the data returned.
          this._loginList = result;
        });
    }
    // search on the list of Logins the one with the same id.
    for (let lg of this._loginList) {
      if (lg.getLoginId() == newloginid) {
        this._currentLogin = lg;
        return this._currentLogin;
      }
    }
    // We have run all the list and we have not found any Login with the right id. We should trigger an exception.
    throw new TypeError("Login identifier " + newloginid + " not found. Cannot select that login");
  }
  public accessLogin(): Login {
    return this._currentLogin;
  }

  //--- P I L O T   S E C T I O N
	/**
	Selects the current Pilot. If this value is not set then moves the page pointer to the current Login Pilot Roaster.
	*/
  public accessCharacter(): NeoComCharacter {
    if (null == this._currentCharacter) {
      // Move to the Login Pilot Roarter page.
      this.router.navigate(['/login', this.accessLogin().getLoginId(), 'pilotroaster']);
    } else return this._currentCharacter;
  }
	/**
	We asume that the current Login is setup and the we get the pilot list of the pilots associated to the keys assigned to that Login. If that data is not already downloaded then we should go to the backend services and get the list of Characters from the backend database.
	*/
  // public accessPilotRoaster() {
  //   if (null != this.currentLogin) {
  //     this.currentLogin.accessPilotRoaster();
  //   } else new TypeError("Current login is null. Cannot select that login");
  // }
  public getBackendPilotRoaster(loginid: string): Observable<NeoComCharacter[]> {
    console.log("><[AppModelStoreService.getBackendPilotRoaster]>Loginid = " + loginid);
    //  this.cookieService.put("login-id", "default")
    return this.http.get(AppModelStoreService.RESOURCE_SERVICE_URL + "/login/" + loginid + "/pilotroaster")
      .map(res => res.json())
      .map(result => {
        let roaster: NeoComCharacter[] = [];
        for (let character of result) {
          // Check the differentiation between Pilot and Corporation.
          let newchar = null;
          if (character.corporation) {
            newchar = new Corporation(character);
          } else {
            newchar = new Pilot(character);
          }
          roaster.push(newchar);
        }
        // Before returning the data set it to the Model hierarchy.
        this._currentLogin.setPilotRoaster(roaster);
        return roaster;
      });
  }

  //--- C A L L B A C K   S E C T I O N
	/**
	Signal the termination of a callback to the last viewer that was active. There is no check that the viewer is the right one but it has no impact on the result.
	*/
  public fireRefresh() {
    if (null != this._lastViewer) this._lastViewer.refreshViewPort();
  }
  public setCallbackViewer(viewer: PageComponent) {
    this._lastViewer = viewer;
  }
}