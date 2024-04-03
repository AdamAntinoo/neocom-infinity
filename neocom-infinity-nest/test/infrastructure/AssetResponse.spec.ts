import { Test } from "@nestjs/testing";

describe('DTO AssetResponse [Module: Assets]', () => {
	beforeEach(async () => {
		const app: TestingModule = await Test.createTestingModule({
			controllers: [AppController],
			providers: [AppService],
		}).compile();

		appController = app.get<AppController>(AppController);
	});
	describe('constructor contract', () => {
		it('should be created', () => {
			expect(component).toBeDefined('service has not been created.')
		})
	}
}