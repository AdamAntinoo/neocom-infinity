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

// - INTERCEPTORS
import { AuthorizationInterceptor } from './security/authorization.interceptor';
import { NeoComHeadersInterceptor } from './security/neocomheaders.interceptor';
// - SERVICES
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
import { V1LoginValidationPageComponent } from './pages/v1-login-validation-page/v1-login-validation-page.component';
import { DashboardHomePageComponent } from './pages/dashboard-home-page/dashboard-home-page.component';
import { ExceptionInformationPageComponent } from './pages/exception-information-page/exception-information-page.component';
// - COMPONENTS
import { LoginValidationProgressComponent } from './panels/login-validation-progress/login-validation-progress.component';
import { LoginValidationExceptionComponent } from './panels/login-validation-exception/login-validation-exception.component';

// - LOCALES
import localeEs from '@angular/common/locales/es';
import { registerLocaleData } from '@angular/common';
import { UniverseService } from './services/universe.service';
import { V1StartPageComponent } from './pages/v1-start-page/v1-start-page.component';
import { V1MiningOperationsPageComponent } from './industry/pages/v1-mining-operations-page/V1MiningOperationsPage';
import { V1MiningOperationsPanelComponent } from './industry/panels/v1-miningoperations-panel/v1-miningoperations-panel.component';
import { RendersModule } from './modules/renders/renders.module';
import { V1StackRenderComponent } from './industry/renders/v1-stack-render/v1-stack-render.component';
registerLocaleData(localeEs);

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
        // - APPLICATION MODULES
        AppCommonModule,
        // SharedModule,
        HeaderModule,
        // FittingsModule
        RendersModule
    ],
    declarations: [
        AppComponent,
        // - PAGES
        V1LoginValidationPageComponent,
        LoginValidationProgressComponent,
        LoginValidationExceptionComponent,
        DashboardHomePageComponent,
        ExceptionInformationPageComponent,
        V1StartPageComponent,
        // - ELEMENTS ON THE FLY
        V1MiningOperationsPageComponent,
        V1MiningOperationsPanelComponent,
    ],
    providers: [
        // - SERVICES
        { provide: AppStoreService, useClass: AppStoreService },
        { provide: UniverseService, useClass: UniverseService },
        { provide: BackendService, useClass: BackendService },
        { provide: AuthenticationService, useClass: AuthenticationService },
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
