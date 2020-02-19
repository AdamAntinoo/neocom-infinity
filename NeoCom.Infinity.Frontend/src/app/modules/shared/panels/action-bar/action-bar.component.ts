// - CORE
import { Component } from '@angular/core';
import { Input } from '@angular/core';

@Component({
    selector: 'action-bar',
    templateUrl: './action-bar.component.html',
    styleUrls: ['./action-bar.component.scss']
})
export class ActionBarComponent {
    @Input() title: string = '-';
    @Input() subtitle: string = '';
}
