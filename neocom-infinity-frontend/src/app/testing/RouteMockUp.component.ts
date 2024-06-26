// - CORE
import { Component } from '@angular/core';
import { Routes } from '@angular/router';

/**
 * This is an empty component to be pointed with valid routes.
 */
@Component({
    template: `Home`
})
export class RouteMockUpComponent {}
export const routes: Routes = [
    { path: '', redirectTo: '/', pathMatch: 'full' },
    { path: 'start', component: RouteMockUpComponent },
    { path: 'login', component: RouteMockUpComponent },
    { path: 'dashboard', component: RouteMockUpComponent },
    { path: 'inventory', component: RouteMockUpComponent },
    { path: 'inventory/partlist', component: RouteMockUpComponent },
    { path: 'inventory/coillist', component: RouteMockUpComponent }
];
// - E N D   O F   R O U T I N G   C O M P O N E N T
