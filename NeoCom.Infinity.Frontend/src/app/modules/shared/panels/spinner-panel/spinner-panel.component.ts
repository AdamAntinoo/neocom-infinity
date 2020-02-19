// - CORE
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { OnDestroy } from '@angular/core';
import { Input } from '@angular/core';
import { Observable } from 'rxjs';
import { Subscription } from 'rxjs';
import { interval } from 'rxjs';
// - ANIMATIONS
import { trigger } from '@angular/animations';
import { state } from '@angular/animations';
import { style } from '@angular/animations';
import { transition } from '@angular/animations';
import { animate } from '@angular/animations';
import { keyframes } from '@angular/animations';
import { query } from '@angular/animations';
import { stagger } from '@angular/animations';
// - DOMAIN
import { ESeparator } from '@domain/interfaces/EPack.enumerated';

const SPINNER_IMAGE_PATH_PREFIX = '/assets/res-ui/drawable/';

@Component({
    selector: 'spinner-panel',
    templateUrl: './spinner-panel.component.html',
    styleUrls: ['./spinner-panel.component.scss']
})
export class SpinnerPanelComponent implements OnInit, OnDestroy {
    @Input() title: string = "-";
    @Input() colorTheme: ESeparator = ESeparator.WHITE;

    private ticks = 0;
    private timer: Observable<number> = null;
    private timerSubscription: Subscription;

    ngOnInit() {
        this.timer = interval(1000);
        this.timer.subscribe(t => {
            this.ticks = t;
        });
    }
    ngOnDestroy() {
        if (null != this.timerSubscription) this.timerSubscription.unsubscribe();
    }

    public getSpinnerColor(): string {
        switch (this.colorTheme) {
            case ESeparator.WHITE:
                return SPINNER_IMAGE_PATH_PREFIX + 'progress70_white.png';
            case ESeparator.RED:
                return SPINNER_IMAGE_PATH_PREFIX + 'progress70_red.png';
            case ESeparator.ORANGE:
                return SPINNER_IMAGE_PATH_PREFIX + 'progress70_orange.png';
            case ESeparator.GREEN:
                return SPINNER_IMAGE_PATH_PREFIX + 'progress70_green.png';
            case ESeparator.BLUE:
                return SPINNER_IMAGE_PATH_PREFIX + 'progress70_blue.png';
            case ESeparator.BLACK:
                return SPINNER_IMAGE_PATH_PREFIX + 'progress70_black.png';
            default:
                return SPINNER_IMAGE_PATH_PREFIX + 'progress70_white.png';
        }
    }
    public getWaitingTime() {
        var date = new Date(null);
        date.setSeconds(this.ticks);
        return date;
    }
}
