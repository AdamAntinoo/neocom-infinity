import { NoActionConverter, TypedResponseTransformer, V2MiningOperation } from "neocom-domain"

describe('CLASS TypedResponseTransformer [Module: Domain - Version: v1]', () => {
  // - C O N S T R U C T I O N   P H A S E
  describe('Constructor Phase', () => {
    it('Should be created', () => {
      console.log('><[TypedResponseTransformer]> should be created')
      const sut: TypedResponseTransformer<V2MiningOperation> = new TypedResponseTransformer<V2MiningOperation>()
      expect(sut).toBeDefined()
    })
    it('create with a converter', () => {
      console.log('><[TypedResponseTransformer]> should be created')
      const sut: TypedResponseTransformer<V2MiningOperation> = new TypedResponseTransformer<V2MiningOperation>()
        .setConverter(new NoActionConverter())
      expect(sut).toBeDefined()
    })
  })
  describe('Transformation Phase', () => {
    it('when the thanformer is used the default output has no changes', () => {
      const sut: TypedResponseTransformer<V2MiningOperation> = new TypedResponseTransformer<V2MiningOperation>()
      expect(sut).toBeDefined
      const operation: V2MiningOperation = new V2MiningOperation({
        id: '-test-id-',
        date: '2024/01/01',
        quantity: 432,
        solarSystem: '-solarsystem-url-',
        typeId: '-type-url-'
      })
      expect(operation).toBeDefined
      expect(operation.jsonClass).toBe('MiningOperation')
      expect(operation.id).toBe('-test-id-')
      expect(operation.date).toBe('2024/01/01')
      expect(operation.quantity).toBe(432)
      expect(operation.solarSystem).toBe('-solarsystem-url-')
      expect(operation.typeId).toBe('-type-url-')
      const transformedOperation: V2MiningOperation[] = sut.transform(operation)
      expect(transformedOperation).toBeDefined
      expect(transformedOperation.length).toBe(1)
      const op: V2MiningOperation = transformedOperation[0]
      expect(op).toBeDefined
      expect(op.jsonClass).toBe('MiningOperation')
      expect(op.id).toBe('-test-id-')
      expect(op.date).toBe('2024/01/01')
      expect(op.quantity).toBe(432)
      expect(op.solarSystem).toBe('-solarsystem-url-')
      expect(op.typeId).toBe('-type-url-')
    })
  })
})
