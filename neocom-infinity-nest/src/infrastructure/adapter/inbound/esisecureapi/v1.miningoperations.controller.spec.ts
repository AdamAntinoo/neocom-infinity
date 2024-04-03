import { Test, TestingModule } from '@nestjs/testing';
import { MincontrollerController } from './mincontroller.controller';
import { MincontrollerService } from './mincontroller.service';

describe('MincontrollerController', () => {
    let controller: MincontrollerController;

    beforeEach(async () => {
        const module: TestingModule = await Test.createTestingModule({
            controllers: [MincontrollerController],
            providers: [MincontrollerService],
        }).compile();

        controller = module.get<MincontrollerController>(MincontrollerController);
    });

    it('should be defined', () => {
        expect(controller).toBeDefined();
    });
});
