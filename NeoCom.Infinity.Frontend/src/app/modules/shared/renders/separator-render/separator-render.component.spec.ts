// - CORE
import { NO_ERRORS_SCHEMA } from '@angular/core';
// - TESTING
import { async } from '@angular/core/testing';
import { fakeAsync } from '@angular/core/testing';
import { tick } from '@angular/core/testing';
import { ComponentFixture } from '@angular/core/testing';
import { TestBed } from '@angular/core/testing';
// - PROVIDERS
import { SupportIsolationService } from '@app/testing/SupportIsolation.service';
import { SeparatorComponent } from './separator-render.component';
import { GroupContainer } from '@domain/GroupContainer.domain';
import { ESeparator } from '@domain/interfaces/EPack.enumerated';

describe('RENDER SeparatorComponent [Module: SHARED]', () => {
    let fixture: ComponentFixture<SeparatorComponent>;
    let component: SeparatorComponent;
    let isolation: SupportIsolationService;

    beforeEach(() => {
        TestBed.configureTestingModule({
            schemas: [NO_ERRORS_SCHEMA],
            declarations: [
                SeparatorComponent
            ],
        })
            .compileComponents();
        fixture = TestBed.createComponent(SeparatorComponent);
        isolation = TestBed.get(SupportIsolationService);
    });

    // - C O N S T R U C T I O N   P H A S E
    describe('Construction Phase', () => {
        it('Should be created', () => {
            console.log('><[renders/SeparatorComponent]> should be created');
            component = fixture.componentInstance;
            expect(component).toBeDefined('component has not been created.');
        });
    });
    // - C O D E   C O V E R A G E   P H A S E
    describe('Code Coverage Phase [getters]', () => {
        it('getSeparatorColor.white: get the color stype depending on the separator type', () => {
            const node = new GroupContainer();
            const nodeAny = node as any;
            nodeAny.themeColor = ESeparator.WHITE;
            component = fixture.componentInstance;
            component.node = node;
            expect(component.getSeparatorColor()).toEqual('solid-white');
        });
        it('getSeparatorColor.green: get the color stype depending on the separator type', () => {
            const node = new GroupContainer();
            const nodeAny = node as any;
            nodeAny.themeColor = ESeparator.GREEN;
            component = fixture.componentInstance;
            component.node = node;
            expect(component.getSeparatorColor()).toEqual('solid-green');
        });
        it('getSeparatorColor.red: get the color stype depending on the separator type', () => {
            const node = new GroupContainer();
            const nodeAny = node as any;
            nodeAny.themeColor = ESeparator.RED;
            component = fixture.componentInstance;
            component.node = node;
            expect(component.getSeparatorColor()).toEqual('solid-red');
        });
        it('getSeparatorColor.orange: get the color stype depending on the separator type', () => {
            const node = new GroupContainer();
            const nodeAny = node as any;
            nodeAny.themeColor = ESeparator.ORANGE;
            component = fixture.componentInstance;
            component.node = node;
            expect(component.getSeparatorColor()).toEqual('solid-orange');
        });
        it('getSeparatorColor.yellow: get the color stype depending on the separator type', () => {
            const node = new GroupContainer();
            const nodeAny = node as any;
            nodeAny.themeColor = ESeparator.YELLOW;
            component = fixture.componentInstance;
            component.node = node;
            expect(component.getSeparatorColor()).toEqual('solid-yellow');
        });
        it('getSeparatorColor.empty: get the color stype depending on the separator type', () => {
            const node = new GroupContainer();
            const nodeAny = node as any;
            nodeAny.themeColor = ESeparator.EMPTY;
            component = fixture.componentInstance;
            component.node = node;
            expect(component.getSeparatorColor()).toEqual('empty-marker');
        });
        it('getSeparatorColor.spinner: get the color stype depending on the separator type', () => {
            const node = new GroupContainer();
            const nodeAny = node as any;
            nodeAny.themeColor = ESeparator.SPINNER;
            component = fixture.componentInstance;
            component.node = node;
            expect(component.getSeparatorColor()).toEqual('empty-marker');
        });
    });
});
