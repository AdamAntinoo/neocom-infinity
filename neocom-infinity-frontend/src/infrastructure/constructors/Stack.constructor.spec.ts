import { TestBed } from "@angular/core/testing"
import { StackConstructor } from "./Stack.constructor"
import { V1EsiTypeDto, V1StackDto } from "neocom-domain"
import { V1Stack } from "@domain/esi/V1.Stack.domain"
import { UnsecuredProxy } from "@adapter/outbound/UnsecuredProxy/V1.UnsecuredProxy.adapter"

describe('CONSTRUCTOR StackConstructor [Module: Intrastructure]', () => {
    let resolver: UnsecuredProxy

    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [
                {
                    provide: UnsecuredProxy, useValue: {
                        apiv3_GetUnsecuredLink: (link: string) => {
                            console.log('link->' + link)

                            switch (link) {
                                }
                        }
                    }
                }
            ]
        }).compileComponents()
        resolver = TestBed.inject(UnsecuredProxy)
    })

    it('Should be created', () => {
        const constructor = new StackConstructor(resolver)
        expect(constructor).toBeDefined()
    })
    describe('Functionality phase', () => {
        it('when the construction is started we receive a Promise for the data', async () => {
            expect(resolver).toBeDefined()
            const stackDto: V1StackDto = new V1StackDto({
                quantity: 100,
                typeLink: '/stack/typelink'
            })
            const stackPromise: Promise<V1Stack> = new StackConstructor(resolver).construct(stackDto)
            expect(stackPromise).toBeDefined()
            await stackPromise.then((stack: V1Stack) => {
                expect(stack).toBeDefined()
                expect(stack.quantity).toBe(100)
                expect(stack.type).toBeDefined()
                expect(stack.type.typeId).toBe(17464)
                expect(stack.type.name).toBe('Scordite')
                expect(stack.type.name).toBe('Scordite')
            })
        })
    })

})
