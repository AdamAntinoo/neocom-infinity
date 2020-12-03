import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
    {
        path: '',
        redirectTo: '/planetary/analysis',
        pathMatch: 'full',
    },
    // - LOGIN PAGES
    {
        path: 'planetary',
        loadChildren: () =>
            import('./modules/planetary/planetary.module').then(
                (m) => m.PlanetaryModule
            ),
    },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
})
export class AppRoutingModule { }
