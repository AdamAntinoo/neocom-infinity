import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { Routes, RouterModule } from "@angular/router";
import { HeaderModule } from "@app/modules/header/header.module";
import { RendersModule } from "@app/modules/renders/renders.module";
import { TokenAuthorizationGuard } from "@app/security/token-authorization.guard";
import { SharedModule } from "@shared/shared.module";
import { V1MiningOperationsPageComponent } from "./pages/V1MiningOperationsPage";
import { V1MiningOperationsPanelComponent } from "./panels/v1-miningoperations-panel/v1-miningoperations-panel.component";

const routes: Routes = [
    { path: 'miningoperations', component: V1MiningOperationsPageComponent, canActivate: [TokenAuthorizationGuard] },
];

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        RouterModule.forChild(routes),
        SharedModule,
        HeaderModule,
        RendersModule
    ],
    declarations: [
        V1MiningOperationsPageComponent,
        V1MiningOperationsPanelComponent
    ],
    exports: [RouterModule]
})
export class MiningModule { }
