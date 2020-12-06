// - CORE MODULES
import { NgModule } from '@angular/core'
import { CommonModule } from '@angular/common'
import { FormsModule } from '@angular/forms'
// - ROUTING
import { Routes } from '@angular/router'
import { RouterModule } from '@angular/router'
// - APPLICATION MODULES
import { SharedModule } from '@shared/shared.module'
import { RendersModule } from '../renders/renders.module'
// - COMPONENTS
import { DashboardPageComponent } from './page/dashboard-page/dashboard-page.component';

const routes: Routes = [
    { path: 'dashboard', component: DashboardPageComponent },
]

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        RouterModule.forChild(routes),
    ],
    declarations: [DashboardPageComponent],
    exports: [RouterModule]
})
export class PlanetaryModule { }
