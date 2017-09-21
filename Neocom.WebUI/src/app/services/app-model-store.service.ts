import { Injectable } from '@angular/core';
import { Inject } from '@angular/core';
import { Router } from '@angular/router';

//--- HTTP PACKAGE
import { Http } from '@angular/http';
import { Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Rx';
// Import RxJs required methods
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
//--- SERVICES
//--- INTERFACES
import { IDataSource } from '../classes/IDataSource.interface';
//--- CLASSES
import { DataSourceLocator } from '../classes/DataSourceLocator';
//--- MODELS
import { Login } from '../models/Login.model';
import { Render } from '../models/Render.model';
import { Pilot } from '../models/Pilot.model';
import { Corporation } from '../models/Corporation.model';
import { NeoComNode } from '../models/NeoComNode.model';
import { NeoComCharacter } from '../models/NeoComCharacter.model';
import { Manager } from '../models/Manager.model';
import { AssetsManager } from '../models/AssetsManager.model';
import { PlanetaryManager } from '../models/PlanetaryManager.model';

//
// This service handles the application storage of elements required to setup the data
// hierarchy while generating the presentations. One of the additional elements it
// stores besides the Android equivalent is the DataSource associated to the ListView.
//
@Injectable()
export class AppModelStoreService {
  static APPLICATION_SERVICE_PORT = "9000";
  static RESOURCE_SERVICE_URL: string = "http://localhost:" + AppModelStoreService.APPLICATION_SERVICE_PORT + "/api/v1";

  private _loginList: Login[] = null;
  private _currentLogin: Login = null;
  //  private characterList: NeoComCharacter[] = null;
  private _currentCharacter: NeoComCharacter = null;

  private _dataSourceCache: IDataSource[] = [];
  private _activeDataSource: IDataSource = null;
  private _viewList: Observable<Array<Render>>;

  constructor(private http: Http, private router: Router) { }
  //--- L O G I N    S E C T I O N
  /**
  Go to the backend Database to retrieve the list of declared Logins to let the user to select the one he/she wants for working. If the list is already downloaded then do not access again the Database and return the cached list.
  */
  public accessLoginList(): Observable<Login[]> {
    console.log(">>[AppModelStoreService.accessLoginList]");
    if (null == this._loginList) {
      // Get the list form the backend Database.
      // On this preliminar version simulate it with a hand made list.
      this._loginList = [];
      this._loginList.push(new Login({ loginid: "Beth Ripley" }));
      this._loginList.push(new Login({ loginid: "Perico" }));
      this._loginList.push(new Login({ loginid: "CapitanHaddock09" }));
    }
    return new Observable(observer => {
      setTimeout(() => {
        observer.next(this._loginList);
      }, 100);
      setTimeout(() => {
        observer.complete();
      }, 100);
    });
  }
  /**
  Sets the new login that comes from the URL when the user selects one from the list of logins.
  If the Login set is different from the current Login then we fire the download of
  the list of Pilots associated with that Login's Keys.
  */
  public setLoginById(newloginid: string): Login {
    if (null == this._loginList) {
      this.accessLoginList()
        .subscribe(result => {
          console.log("--[AppModelStoreService.setLoginById.accessLoginList]>LoginList: " + JSON.stringify(result));
          // The the list of planetary resource lists to the data returned.
          this._loginList = result;
          //      this.downloading = false;
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
  Sets the current Pilot selected to the identifier received as a parameter. The selection requires the search for the character on the list of Pilots that should be related to the current Login. This starts to require the hierarchical model storage on the Service.
  */
  public setPilotById(id: number): NeoComCharacter {
    if (null != this._currentLogin) {
      this._currentCharacter = this._currentLogin.accessCharacterById(id);
    }
    if (null == this._currentCharacter) {
      this.router.navigate(['/login', this.accessLogin().getLoginId(), 'pilotroaster']);
    }
    return this._currentCharacter;
  }
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
  public getBackendPilotManagerList(characterid: number): Observable<Manager[]> {
    console.log("><[AppModelStoreService.getBackendPilotManagerList]>Characterid = " + characterid);
    let loginid = this.accessLogin().getLoginId();
    return this.http.get(AppModelStoreService.RESOURCE_SERVICE_URL + "/login/" + loginid + "/pilot/" + characterid + "/pilotmanagers")
      .map(res => res.json())
      .map(result => {
        let roaster: Manager[] = [];
        for (let manager of result) {
          // Check the differentiation between Pilot and Corporation.
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
          roaster.push(newman);
        }
        return roaster;
      });
  }
  public getBackendPilotPlanetaryManager(characterid: number): Observable<Manager[]> {
    console.log("><[AppModelStoreService.getBackendPilotManagerList]>Characterid = " + characterid);
    let loginid = this.accessLogin().getLoginId();
    return this.http.get(AppModelStoreService.RESOURCE_SERVICE_URL + "/login/" + loginid + "/pilot/" + characterid + "/planetarymanager")
      .map(res => res.json())
      .map(result => {
        let roaster: Manager[] = [];
        for (let manager of result) {
          // Check the differentiation between Pilot and Corporation.
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
          roaster.push(newman);
        }
        return roaster;
      });
  }

  public accessDataSource(): IDataSource {
    return this._activeDataSource;
  }
  public searchDataSource(locator: DataSourceLocator): IDataSource {
    let target = this._dataSourceCache[locator.getLocator()];
    return target;
  }



  /**
  Checks if this datasource is already present at the registration list. If found returns the already found datasource, otherwise adds this to the list of caches datasources.
*/
  public registerDataSource(ds: IDataSource): IDataSource {
    let locator = ds.getLocator();
    let target = this._dataSourceCache[locator.getLocator()];
    if (target == null) {
      this._dataSourceCache.push(ds);
      return ds;
    }
    return target;
  }
  public setActiveDataSource(ds: IDataSource): IDataSource {
    this._activeDataSource = ds;
    return this._activeDataSource;
  }
}