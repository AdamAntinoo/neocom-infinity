// - CORE
import { NO_ERRORS_SCHEMA } from '@angular/core'
import { HttpHeaders } from '@angular/common/http'
import { HttpClient } from '@angular/common/http'
// - HTTP PACKAGE
import { HttpClientTestingModule } from '@angular/common/http/testing'
import { HttpTestingController } from '@angular/common/http/testing'
// - TESTING
import { async } from '@angular/core/testing'
import { inject } from '@angular/core/testing'
import { TestBed } from '@angular/core/testing'
import { RouterTestingModule } from '@angular/router/testing'
// - APP
import { UniverseHttpWrapper } from './universe.httpwrapper'
import { IsolationService } from '@innovative/services/isolation.service'
import { SupportIsolationService } from '@app/testing/SupportIsolation.service'

describe('SERVICE UniverseHttpWrapper [Module: APP]', () => {
    let wrapper: UniverseHttpWrapper

    beforeEach(() => {

        TestBed.configureTestingModule({
            schemas: [NO_ERRORS_SCHEMA],
            imports: [
                HttpClientTestingModule
            ],
            providers: [
                { provide: IsolationService, useClass: SupportIsolationService },
                { provide: HttpClient, useClass: HttpTestingController }
            ]
       })
            .compileComponents()
        wrapper = TestBed.inject(UniverseHttpWrapper)
    })
    // - C O N S T R U C T I O N   P H A S E
    describe('Construction Contract', () => {
        it('Should be created', () => {
            console.log('><[app/BackendService]> should be created')
            expect(wrapper).toBeTruthy('component has not been created.')
        })
    })
    // - C O D E   C O V E R A G E   P H A S E
    describe('Code Coverage Phase [wrapHttpSecureHeaders]', () => {
        it('wrapHttpSecureHeaders.success: compose the required Universe type request headers', async () => {
            console.log('step 01')
            const wrapperAsAny = wrapper as any
            const headerAsAny: HttpHeaders = wrapperAsAny.wrapHttpSecureHeaders()
            expect(headerAsAny).toBeDefined()
            expect(headerAsAny instanceof HttpHeaders).toBeTrue()
        })
        it('wrapHttpSecureHeaders.add headers: compose the required Universe type request headers', async () => {
            console.log('step 01')
            const wrapperAsAny = wrapper as any
            const additional : HttpHeaders = new HttpHeaders()
            additional.set('additional', 'value')
            const headerAsAny: HttpHeaders = wrapperAsAny.wrapHttpSecureHeaders(additional)
            expect(headerAsAny).toBeDefined()
            expect(headerAsAny instanceof HttpHeaders).toBeTrue()
        })
    })
})
