import { UnsecuredProxy } from "@adapter/outbound/UnsecuredProxy/V1.UnsecuredProxy.adapter"
import { TestBed } from "@angular/core/testing"
import { UnsecureProxyMock } from "src/test/service-mocks/UnsecureProxy.mock"
import { BackendFactory } from "./BackendFactory"
import { V1Stack } from "@domain/esi/V1.Stack.domain"
import { V1StackDto } from "neocom-domain"

describe('CONSTRUCTOR MiningOperationConstructor [Module: Constructors]', () => {
    let resolver: UnsecuredProxy

    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [
                { provide: UnsecuredProxy, useClass: UnsecureProxyMock }
            ]
        }).compileComponents()
        resolver = TestBed.inject(UnsecuredProxy)
    })

    it('Should be created', () => {
        const factory = new BackendFactory(resolver)
        expect(factory).toBeDefined()
    })
    describe('Functionality phase', () => {
        it('when the constructor for a stack is called we get a completed stack', async () => {
            expect(resolver).toBeDefined()
            const factory = new BackendFactory(resolver)
            expect(factory).toBeDefined()

            const stackDto: V1StackDto = getV1StackDto()
            const sut: Promise<V1Stack> = factory.construct(stackDto)
            await sut.then((stack: V1Stack) => {
                expect(stack.quantity).toBe(1200)
                expect(stack.type).toBeDefined()
                console.log(stack.type)
                expect(stack.type.typeId).toBe(17464)
                expect(stack.type.name).toBe('Scordite')
                expect(stack.type.description).toBe('-scrdite-description-')
                expect(stack.type.iconId).toBe(34)
                expect(stack.type.groupId).toBe(200)
                expect(stack.type.groupName).toBe('-gorup-name-')
                expect(stack.type.categoryId).toBe(2)
                expect(stack.type.categoryName).toBe('-category-name-')
                expect(stack.type.volume).toBe(0.1)
                expect(stack.type.marketData).toBeDefined()
                expect(stack.type.marketData.buyPrice).toBe(14)
                expect(stack.type.marketData.buyOrderCount).toBe(7)
                expect(stack.type.marketData.sellPrice).toBe(18.9)
                expect(stack.type.marketData.sellOrderCount).toBe(8)
            })
        })
    })

})
function getV1StackDto(): V1StackDto {
    return new V1StackDto({
        jsonClass: 'StackDto',
        quantity: 1200,
        typeLink: '/esi/v1/universe/types/17464'
    })
}
