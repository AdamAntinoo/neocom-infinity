//  PROJECT:     NeoCom.WS (NEOC.WS)
//  AUTHORS:     Adam Antinoo - adamantinoo.git@gmail.com
//  COPYRIGHT:   (c) 2017-2018 by Dimensinfin Industries, all rights reserved.
//  ENVIRONMENT: Angular 4
//  DESCRIPTION: Angular source code to run on a web server almost the same code as on the Android platform.
//               The project has 3 clear parts. One is the Java libraries that are common for all platforms,
//               the second is the java microservices that compose the web application backend made with
//               SpringBoot technology and finally the web ui code made in typescript within the Angular
//               framework.
//--- CORE MODULES
import { NgModule } from '@angular/core';
import { Routes } from '@angular/router';
import { RouterModule } from '@angular/router';
//--- PAGES
import { CredentialsPageComponent } from './pages/credentials-page/credentials-page.component';
import { PilotDetailPageComponent } from './pages/pilot-detail-page/pilot-detail-page.component';
import { FittingManagerPageComponent } from './pages/fitting-manager-page/fitting-manager-page.component';

// import { LoginPageComponent } from './pages/login-page/login-page.component';
// //import { PilotRoasterPageComponent } from './pages/pilot-roaster-page/pilot-roaster-page.component';
// import { AssetsManagerPageComponent } from './pages/assets-manager-page/assets-manager-page.component';
// import { PlanetaryManagerPageComponent } from './pages/planetary-manager-page/planetary-manager-page.component';
// import { PlanetaryOptimizationPageComponent } from './pages/planetary-optimization-page/planetary-optimization-page.component';


const routes: Routes = [
  {
    path: '',
    redirectTo: '/credentials',
    pathMatch: 'full'
  },
  { path: 'credentials', component: CredentialsPageComponent },
  { path: 'pilot/:id', component: PilotDetailPageComponent },
  { path: 'pilot/:id/fittingmanager', component: FittingManagerPageComponent },

  // { path: 'login', component: LoginPageComponent },
  // { path: 'login/:loginid/pilot/:id', component: PilotDetailPageComponent },
  // { path: 'login/:loginid/pilot/:id/AssetsManager', component: AssetsManagerPageComponent },
  // { path: 'login/:loginid/pilot/:id/PlanetaryManager', component: PlanetaryManagerPageComponent },
  // { path: 'login/:loginid/pilot/:id/PlanetaryManager/location/:locationid/PlanetaryOptimization', component: PlanetaryOptimizationPageComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }