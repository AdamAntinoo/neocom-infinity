// - CORE
import { NO_ERRORS_SCHEMA } from '@angular/core';
// - TESTING
import { async } from '@angular/core/testing';
import { fakeAsync } from '@angular/core/testing';
import { flushMicrotasks } from '@angular/core/testing';
import { tick } from '@angular/core/testing';
import { ComponentFixture } from '@angular/core/testing';
import { TestBed } from '@angular/core/testing';
// - PROVIDERS
import { IsolationService } from '@app/platform/isolation.service';
import { SupportIsolationService } from '@app/testing/SupportIsolation.service';
import { AppStoreService } from '@app/services/appstore.service';
import { SupportAppStoreService } from '@app/testing/SupportAppStore.service';
import { BackendService } from '@app/services/backend.service';
import { SupportBackendService } from '@app/testing/SupportBackend.service';

import { AppInfoPanelComponent } from '@app/modules/shared/panels/app-info-panel/app-info-panel.component';
import { Alliance } from '@app/domain/Alliance.domain';
import { CorporationPublicDataPanelComponent } from './corporation-public-data-panel.component';
import { Observable } from 'rxjs';
import { Corporation } from '@app/domain/Corporation.domain';
import { environment } from '@env/environment';

describe('PANEL CorporationPublicDataPanelComponent [Module: SHARED]', () => {
   let component: CorporationPublicDataPanelComponent;
   let fixture: ComponentFixture<CorporationPublicDataPanelComponent>;
   let isolationService: SupportIsolationService;
   let appStoreService: SupportAppStoreService;
   const corporation = new Corporation(
      {
         corporationId: 98384726,
         ceo_id: 92223647,
         creator_id: 92223647,
         alliance: null,
         date_founded: "2015-03-16T09:28:02Z",
         description: "<font size=\"12\" color=\"#bfffffff\"></font><font size=\"18\" color=\"#bfffffff\"><b>Industrias Machaque</b><br><br><br></font><font size=\"14\" color=\"#bfffffff\">Bienvenido a la página de descripción de Industrias Machaque.<br><br>El objeto de esta corporación es, además de hacer ricos en ISK a sus miembros, el gestionar los recursos disponibles de minería y de transporte logístico. Actualmente no aceptamos nuevos miembros ya que esta es una sociedad instrumental en un paraiso fiscal y no queremos compartir ese paraiso (de momento).<br><br>Estamos especializados en Industria manufacturera de tecnología T2. Podemos establecer relaciones de consultoría sobre gestión, planificación y logística para colaborar con otras corporaciones, principalmente de habla hispana, para aumentar la potencia económica de nuestras corporaciones. El “que inventen ellos” se ha terminado.<br><br>Para cualquier tema ponte en contacto con nuestro CEO: </font><font size=\"14\" color=\"#ffffa600\"><loc><a href=\"showinfo:1380//92223647\">Beth Ripley</a></loc></font><font size=\"14\" color=\"#bfffffff\"> (habitualmente a bordo de su Nostromo aunque hemos renombrado el nombre de la nave para evitar pagar licencias y royalties).</font>",
         home_station_id: 60006610,
         member_count: 3,
         name: "Industrias Machaque",
         shares: 1000,
         tax_rate: 0.05,
         ticker: "IN.MA",
         url: "http://www.eveonline.com",
         url4Icon: "http://image.eveonline.com/Corporation/98384726_64.png"
      });

   beforeEach(() => {
      TestBed.configureTestingModule({
         schemas: [NO_ERRORS_SCHEMA],
         declarations: [
            CorporationPublicDataPanelComponent,
         ],
         providers: [
            { provide: IsolationService, useClass: SupportIsolationService },
            { provide: AppStoreService, useClass: SupportAppStoreService },
            { provide: BackendService, useClass: SupportBackendService },
         ]
      })
         .compileComponents();
      fixture = TestBed.createComponent(CorporationPublicDataPanelComponent);
      component = fixture.componentInstance;
      isolationService = TestBed.get(IsolationService);
      appStoreService = TestBed.get(SupportAppStoreService);
   });
   // afterEach(() => {
   //    fixture.destroy();
   //    component = null;
   // });

   // - C O N S T R U C T I O N   P H A S E
   describe('Construction Phase', () => {
      it('Should be created', () => {
         console.log('><[shared/CorporationPublicDataPanelComponent]> should be created');
         expect(component).toBeDefined('component has not been created.');
      });
      it('Fields should be on initial state', () => {
         // console.log('><[shared/CorporationPublicDataPanelComponent]> "_medicalActsSubscription" should be undefined');
         let componentAsAny = component as any;
         expect(componentAsAny.corporationSubscription).toBeUndefined();
         expect(component.corporation).toBeUndefined();
         expect(component.ceo).toBeUndefined();
         expect(component.alliance).toBeUndefined();

      });
   });

   // - L I F E C Y C L E   P H A S E
   describe('Lifecycle Phase', () => {
      xit('Lifecycle: OnInit -> get the authorized corporation data', fakeAsync(() => {
         console.log('><[shared/CorporationPublicDataPanelComponent]> Lifecycle: OnInit -> get the authorized corporation data');
         let componentAsAny = component as any;
         // Check that initial state is the expected.
         // fixture.detectChanges();
         expect(componentAsAny.corporationSubscription).toBeUndefined();
         expect(component.corporation).toBeUndefined();
         expect(component.ceo).toBeUndefined();
         expect(component.alliance).toBeUndefined();

         // Prepare the response to be procesed at the ngOnInit method.
         let corporation = new Corporation({ name: 'Corporation name' });
         // spyOn(appStoreService, 'accessCorporation').and.returnValue(Observable.of(corporation));
         // spyOn(appStoreService, 'accessCorporation').and.returnValue(Promise.resolve(corporation));
         spyOn(appStoreService, 'accessCorporation').and.returnValue(Observable.create((observer) => {
            observer.next(corporation);
            // observer.complete();
         }));
         component.ngOnInit();
         expect(componentAsAny.corporationSubscription).toBeDefined('The subscription should exist after the ngOnInit');
         fixture.detectChanges();
      }));
      xit('Lifecycle: OnDestroy -> close the subscription if active', fakeAsync(() => {
         console.log('><[shared/CorporationPublicDataPanelComponent]> Lifecycle: OnDestroy -> close the subscription if active');
         let componentAsAny = component as any;
         // Check that initial state is the expected.
         // fixture.detectChanges();
         expect(componentAsAny.corporationSubscription).toBeUndefined();
         expect(component.corporation).toBeUndefined();
         expect(component.ceo).toBeUndefined();
         expect(component.alliance).toBeUndefined();

         // Prepare the response to be procesed at the ngOnInit method.
         let corporation = new Corporation({ name: 'Corporation name' });
         // spyOn(appStoreService, 'accessCorporation').and.returnValue(Observable.of(corporation));
         // spyOn(appStoreService, 'accessCorporation').and.returnValue(Promise.resolve(corporation));
         spyOn(appStoreService, 'accessCorporation').and.returnValue(Observable.create((observer) => {
            observer.next(corporation);
            // observer.complete();
         }));
         component.ngOnInit();
         expect(componentAsAny.corporationSubscription).toBeDefined('The subscription should exist after the ngOnInit');
         fixture.detectChanges();
         // component.ngOnDestroy();
         // expect(componentAsAny.corporationSubscription).toBeUndefined('The subscription should be completed when destroyed.');
      }));
   });

   // - C O D E   C O V E R A G E   P H A S E
   describe('Code Coverage Phase [getters]', () => {
      it('getCorporationId.success: validate the corporationId field', () => {
         const expected = isolationService.generateRandomNum(100000, 1000000);
         corporation.corporationId = expected;
         component.corporation = corporation;
         const obtained = component.getCorporationId();
         expect(obtained).toBe(expected);
      });
      it('getCorporationId.failure: validate the corporationId field', () => {
         const obtained = component.getCorporationId();
         expect(obtained).toBe(0);
      });
      it('getCorporationName.success: validate the corporation name field', () => {
         const expected = isolationService.generateRandomString(12);
         corporation.name = expected;
         component.corporation = corporation;
         const obtained = component.getCorporationName();
         expect(obtained).toBe(expected);
      });
      it('getCorporationName.failure: validate the corporation name field', () => {
         const obtained = component.getCorporationName();
         expect(obtained).toBe('-');
      });
      it('getCorporationIcon.success: validate the corporation icon field', () => {
         const expected = isolationService.generateRandomString(12);
         corporation.url4Icon = expected;
         component.corporation = corporation;
         const obtained = component.getCorporationIcon();
         expect(obtained).toBe(expected);
      });
      it('getCorporationIcon.failure: validate the corporation icon field', () => {
         // const expected = isolationService.generateRandomString(12);
         // corporation.url4Icon = expected;
         // component.corporation = corporation;
         const obtained = component.getCorporationIcon();
         expect(obtained).toBe(environment.DEFAULT_AVATAR_PLACEHOLDER);
      });
      it('getAlliance: validate the alliance field', () => {
         // Create the data to be tested.
         const expected = isolationService.generateRandomString(12);
         corporation.alliance = new Alliance({ name: expected })
         component.corporation = corporation;
         component.alliance = corporation.alliance;
         const obtained = component.getAlliance();
         console.log('-[shared/CorporationPublicDataPanelComponent]> alliance: ' + JSON.stringify(obtained));
         expect(obtained).toBeDefined();
         expect(obtained.getName()).toBe(expected);
      });
      it('getAllianceIcon.success: validate the alliance icon field', () => {
         // Create the data to be tested.
         const expected = isolationService.generateRandomString(12);
         corporation.alliance = new Alliance({ url4Icon: expected })
         component.corporation = corporation;
         component.alliance = corporation.alliance;
         console.log('-[shared/CorporationPublicDataPanelComponent]> alliance corporation: ' + JSON.stringify(corporation));
         const obtained = component.getAllianceIcon();
         expect(obtained).toBe(expected);
      });
      it('getAllianceIcon.failure: validate the alliance icon field', () => {
         const obtained = component.getAllianceIcon();
         expect(obtained).toBe(environment.DEFAULT_AVATAR_PLACEHOLDER);
      });
   });
});
