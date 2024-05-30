import { ApiMiningOperationsTypedRequest } from './ApiMiningOperations.typedrequest'

describe('DTO ESIMiningOperationsTypedRequest [Module: Network]', () => {
	it('Should be created', () => {
		const request = new ApiMiningOperationsTypedRequest()
		expect(request).toBeDefined()
	})
	it('when getting method shoeld be GET', () => {
		const request = new ApiMiningOperationsTypedRequest()
		expect(request).toBeDefined()
		expect(request.method).toBe('GET')
	})
	it('when prepared then request should match', () => {
		const pilotId: number = 123
		const request = new ApiMiningOperationsTypedRequest().prepare(pilotId)
		expect(request).toBeDefined()
		expect(request.method).toBe('GET')
		expect(request.request).toBe('/api/v3/neocom/characters/123/miningoperation')
	})
	it('when prepared then options should be defined but empty', () => {
		const pilotId: number = 123
		const request = new ApiMiningOperationsTypedRequest().prepare(pilotId)
		expect(request).toBeDefined()
		expect(request.options).toBeDefined()
		expect(request.options['headers']).toBeDefined()
	})
})
