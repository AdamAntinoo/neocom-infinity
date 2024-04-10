// - CORE MODULES
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
// - COMPONENTS
import { AppPanelComponent } from './components/app-panel/app-panel.component';
import { BackgroundEnabledComponent } from './components/background-enabled/background-enabled.component';
// import { RouteMockUpComponent } from '../../testing/RouteMockUp.component';

@NgModule({
    imports: [
        CommonModule
    ],
    declarations: [
        AppPanelComponent,
        BackgroundEnabledComponent,
        // RouteMockUpComponent,
    ],
    exports: [
        AppPanelComponent,
        BackgroundEnabledComponent,
        // RouteMockUpComponent,
    ]
})
export class InnovativeCoreModule { }
