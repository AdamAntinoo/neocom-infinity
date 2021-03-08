// - CORE
import { NO_ERRORS_SCHEMA } from '@angular/core'
// - TESTING
import { async } from '@angular/core/testing'
import { TestBed } from '@angular/core/testing'
// - PROVIDERS
import { SupportIsolationService } from '@app/testing/SupportIsolation.service'
// - DOMAIN
import { V1BlueprintRenderComponent } from './v1-blueprint-render.component'
import { V1ProcessedBlueprintSummary } from '@app/modules/industry/domain/V1ProcessedBlueprintSummary.domain'
import { NeoComConstants } from '@app/platform/NeocomConstants.platform'

describe('RENDER V1BlueprintRenderComponent [Module: RENDERS]', () => {
    let component: V1BlueprintRenderComponent
    let isolationService: SupportIsolationService = new SupportIsolationService()
    beforeEach(async(() => {
        TestBed.configureTestingModule({
            schemas: [NO_ERRORS_SCHEMA],
            imports: [
            ],
            declarations: [
                V1BlueprintRenderComponent
            ],
            providers: [
            ]
        }).compileComponents()
        const fixture = TestBed.createComponent(V1BlueprintRenderComponent)
        component = fixture.componentInstance
    }))

    // - C O N S T R U C T I O N   P H A S E
    describe('Construction Phase', () => {
        it('Should be created', () => {
            expect(component).toBeDefined('component has not been created.')
        })
    })
    // - C O V E R A G E   P H A S E
    describe('Code Coverage Phase [Getters]', () => {
        it('getNode.null: validate the convertion from generic node to V1ProcessedBlueprintSummary', () => {
            expect(component).toBeDefined()
            expect(component.getNode() instanceof V1ProcessedBlueprintSummary).toBeFalse()
        })
        it('getNode.valid: validate the convertion from generic node to V1ProcessedBlueprintSummary', () => {
            const blueprint = new V1ProcessedBlueprintSummary()
            component.node = blueprint
            expect(component).toBeDefined()
            expect(component.getNode() instanceof V1ProcessedBlueprintSummary).toBeTrue()
        })
        it('getUniqueId: validate the content for the unique identifier', () => {
            expect(component).toBeDefined()
            expect(component.getUniqueId()).toBe("-")
            const blueprint = new V1ProcessedBlueprintSummary({
                "uid": "BCI:93813310:31741"
            })
            component.node = blueprint
            expect(component).toBeDefined()
            expect(component.getUniqueId()).toBe("BCI:93813310:31741")
        })
        it('getBlueprintIconUrl: validate the blueprint type icon link', () => {
            const expected: string = isolationService.generateRandomString(20)
            expect(component).toBeDefined()
            expect(component.getBlueprintIconUrl()).toBe(NeoComConstants.DEFAULT_ICON_PLACEHOLDER)
            const blueprint = new V1ProcessedBlueprintSummary({
                "blueprintTypeIconURL": expected
            })
            component.node = blueprint
            expect(component).toBeDefined()
            expect(component.getBlueprintIconUrl()).toBe(expected)
        })
        it('getBlueprintName: validate the blueprint typename', () => {
            const expected: string = isolationService.generateRandomString(20)
            expect(component).toBeDefined()
            expect(component.getBlueprintName()).toBe('-')
            const blueprint = new V1ProcessedBlueprintSummary({
                "blueprintTypeName": expected
            })
            component.node = blueprint
            expect(component).toBeDefined()
            expect(component.getBlueprintName()).toBe(expected)
        })
        it('getOutputName: validate the output item typename', () => {
            const expected: string = isolationService.generateRandomString(20)
            expect(component).toBeDefined()
            expect(component.getOutputName()).toBe('-')
            const blueprint = new V1ProcessedBlueprintSummary({
                "outputTypeName": expected
            })
            component.node = blueprint
            expect(component).toBeDefined()
            expect(component.getOutputName()).toBe(expected)
        })
        it('getManufactureCost: validate the blueprint manufacture cost', () => {
            const expected: number = isolationService.generateRandomNum(1, 4000)
            expect(component).toBeDefined()
            expect(component.getManufactureCost()).toBe(0.0)
            const blueprint = new V1ProcessedBlueprintSummary({
                "manufactureMaterialCost": expected
            })
            component.node = blueprint
            expect(component).toBeDefined()
            expect(component.getManufactureCost()).toBe(expected)
        })
        it('getOutputPrice: validate the ouput item market price', () => {
            const expected: number = isolationService.generateRandomNum(1, 4000)
            expect(component).toBeDefined()
            expect(component.getOutputPrice()).toBe(0.0)
            const blueprint = new V1ProcessedBlueprintSummary({
                "outputPrice": expected
            })
            component.node = blueprint
            expect(component).toBeDefined()
            expect(component.getOutputPrice()).toBe(expected)
        })
        it('getCostIndex: validate the ouput item market price', () => {
            const expected: number = isolationService.generateRandomNum(1, 4000)
            expect(component).toBeDefined()
            expect(component.getCostIndex()).toBe(0.0)
            const blueprint = new V1ProcessedBlueprintSummary({
                "costIndex": expected
            })
            component.node = blueprint
            expect(component).toBeDefined()
            expect(component.getCostIndex()).toBe(expected * 100)
        })
        it('getOutputIconUrl: validate the blueprint type icon link', () => {
            const expected: string = isolationService.generateRandomString(20)
            expect(component).toBeDefined()
            expect(component.getOutputIconUrl()).toBe(NeoComConstants.DEFAULT_ICON_PLACEHOLDER)
            const blueprint = new V1ProcessedBlueprintSummary({
                "outputTypeIconURL": expected
            })
            component.node = blueprint
            expect(component).toBeDefined()
            expect(component.getOutputIconUrl()).toBe(expected)
        })
    })
})
