import { Test, TestingModule } from "@nestjs/testing"
import { AssetsAdapterImpl } from "./assets.adapter.impl"
// import { plainToInstance } from 'class-transformer'

describe('DTO AssetAdapter [Module: Assets]', () => {
	const assetsAdapterImplDefinition = {}
    // const assetAdapterInstance = plainToInstance(AssetsAdapterImpl, assetsAdapterImplDefinition)

	describe('constructor contract', () => {
		it('should be created', () => {
			const assetAdapterInstance = new AssetsAdapterImpl();
			expect(assetAdapterInstance).toBeDefined() // Assets adapter has not bween created.
		})
	})
})
