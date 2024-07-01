import { ApiMiningOperationsTypedRequest, ApiMiningOperationsTypedRequestInput } from './ApiMiningOperations.typedrequest'

describe('DTO ApiMiningOperationsTypedRequest [Module: Network]', () => {
	const parameters: ApiMiningOperationsTypedRequestInput = {
		token: '-token-',
	}
	it('Should be created', () => {
		const request = new ApiMiningOperationsTypedRequest(parameters)
		expect(request).toBeDefined()
	})
	it('when getting method should be GET', () => {
		const request = new ApiMiningOperationsTypedRequest(parameters)
		expect(request).toBeDefined()
		expect(request.method).toBe('GET')
	})
	it('when prepared then request should match', () => {
		const pilotId: number = 123
		const request: ApiMiningOperationsTypedRequest = new ApiMiningOperationsTypedRequest(parameters).build()
		expect(request).toBeDefined()
		expect(request.method).toBe('GET')
		expect(request.request).toBe('/api/v3/neocom/characters/miningoperation')
	})
	it('when prepared then options should be defined but empty', () => {
		const pilotId: number = 123
		const request = new ApiMiningOperationsTypedRequest(parameters).build()
		expect(request).toBeDefined()
		expect(request.options).toBeDefined()
		expect(request.options['headers']).toBeDefined()
	})
})
