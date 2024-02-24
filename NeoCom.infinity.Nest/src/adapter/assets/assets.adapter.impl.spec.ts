import { Test, TestingModule } from "@nestjs/testing"
import { plainToInstance } from 'class-transformer'

describe('DTO AssetAdapter [Module: Assets]', () => {
	const assetsAdapterImplDefinition = {}
    const assetAdapterInstance = plainToInstance(AssetsAdapterImpl, assetsAdapterImplDefinition)

	describe('constructor contract', () => {
		it('should be created', () => {
			expect(assetAdapterInstance).toBeDefined() // Assets adapter has not bween created.
		})
	})
})
