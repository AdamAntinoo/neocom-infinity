import { TestBed, async } from '@angular/core/testing'
import { RouterTestingModule } from '@angular/router/testing'
import { AppComponent } from './app.component'

describe('APP AppComponent [Module: APP]', () => {
    // - C O N S T R U C T I O N   P H A S E
    describe('Construction Contract', () => {
        it('Should be created', () => {
            const fixture = TestBed.createComponent(AppComponent)
            const app = fixture.debugElement.componentInstance
            expect(app).toBeTruthy()
        })
    })
})
