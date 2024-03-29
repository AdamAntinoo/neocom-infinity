// - CORE MODULES
import { ErrorHandler } from '@angular/core';
import { NgModule } from '@angular/core';
// - BROWSER & ANIMATIONS
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
// - ROUTING
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
// - ADDITIONAL PACKAGES
import { HttpClientModule } from '@angular/common/http';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { MatDialogModule } from '@angular/material/dialog';
import { ToastrModule } from 'ngx-toastr';
import { NgDragDropModule } from 'ng-drag-drop';
import { RollbarModule } from 'angular-rollbar'
import { RollbarService } from 'angular-rollbar'


// - INTERCEPTORS
import { AuthorizationInterceptor } from './security/authorization.interceptor';
import { NeoComHeadersInterceptor } from './security/neocomheaders.interceptor';
// - SERVICES
import { IsolationService } from './platform/isolation.service';
import { AppStoreService } from './services/appstore.service';
import { BackendService } from './services/backend.service';
import { AuthenticationService } from './security/authentication.service';
// - COMPONENTS-CORE
import { AppComponent } from './app.component';

// - APPLICATION MODULES
import { AppCommonModule } from './modules/common/common.module';
import { SharedModule } from './modules/shared/shared.module';
import { HeaderModule } from './modules/header/header.module';
import { FittingsModule } from './modules/fittings/fittings.module';
// - PAGES
import { LoginValidationPageComponent } from './pages/login-validation-page/login-validation-page.component';
import { DashboardHomePageComponent } from './pages/dashboard-home-page/dashboard-home-page.component';
import { ExceptionInformationPageComponent } from './pages/exception-information-page/exception-information-page.component';
// - COMPONENTS
import { LoginValidationProgressComponent } from './panels/login-validation-progress/login-validation-progress.component';
import { LoginValidationExceptionComponent } from './panels/login-validation-exception/login-validation-exception.component';

// - LOCALES
import localeEs from '@angular/common/locales/es';
import { registerLocaleData } from '@angular/common';
registerLocaleData(localeEs);

// // - ERROR INTERCEPTION
// import * as Rollbar from 'rollbar';
// import { rollbarConfig } from '@app/rollbar-errorhandler.service';
// import { RollbarService } from '@app/rollbar-errorhandler.service';
// import { ErrorHandler } from '@angular/core';
// import { RollbarErrorHandler } from '@app/rollbar-errorhandler.service';
// import { HttpErrorInterceptor } from './security/httpErrorProcessing.interceptor';

// export function rollbarFactory() {
//     return new Rollbar(rollbarConfig);
// }

@NgModule({
    imports: [
        // - BROWSER & ANIMATIONS
        FormsModule,
        ReactiveFormsModule,
        BrowserModule,
        BrowserAnimationsModule,
        // - ROUTING
        RouterModule,
        AppRoutingModule,
        // - ADDITIONAL MODULES
        HttpClientModule,
        ToastrModule.forRoot(),
        MatDialogModule,
        NgDragDropModule.forRoot(),
        RollbarModule.forRoot({
            accessToken: '4b7515a4ac41496b931963f64ef666e2'
        }),
        // - APPLICATION MODULES
        AppCommonModule,
        SharedModule,
        HeaderModule,
        FittingsModule
    ],
    declarations: [
        AppComponent,
        // - PAGES
        LoginValidationPageComponent,
        LoginValidationProgressComponent,
        LoginValidationExceptionComponent,
        DashboardHomePageComponent,
        ExceptionInformationPageComponent
    ],
    providers: [
        // - SERVICES
        { provide: IsolationService, useClass: IsolationService },
        { provide: AppStoreService, useClass: AppStoreService },
        { provide: BackendService, useClass: BackendService },
        { provide: AuthenticationService, useClass: AuthenticationService },
        // - ERROR INTERCEPTION
        // { provide: ErrorHandler, useClass: RollbarErrorHandler },
        // { provide: RollbarService, useFactory: rollbarFactory },
        // - HTTP INTERCEPTION
        // { provide: HTTP_INTERCEPTORS, useClass: AuthorizationInterceptor, multi: true },
        // { provide: HTTP_INTERCEPTORS, useClass: HttpErrorInterceptor, multi: true }
    ],
  bootstrap: [AppComponent]
})
export class AppModule { }
