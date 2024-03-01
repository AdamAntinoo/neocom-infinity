import { Test, TestingModule } from "@nestjs/testing"
import { plainToInstance } from 'class-transformer'
import { AssetsAdapter } from "./assets.adapter.impl"

describe('DTO AssetAdapter [Module: Assets]', () => {
	const assetsAdapterImplDefinition = {}
    const assetAdapterInstance = plainToInstance(AssetsAdapter, assetsAdapterImplDefinition)

	describe('constructor contract', () => {
		it('should be created', () => {
			expect(assetAdapterInstance).toBeDefined() // Assets adapter has not bween created.
		})
	})
})
