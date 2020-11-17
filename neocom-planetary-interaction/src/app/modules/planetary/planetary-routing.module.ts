import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { V1PlanetaryPageComponent } from './pages/v1-planetary-page/v1-planetary-page.component';

const routes: Routes = [
  { path: 'analysis', component: V1PlanetaryPageComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PlanetaryRoutingModule { }
