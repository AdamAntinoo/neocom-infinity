import { Test, TestingModule } from '@nestjs/testing';
import { MincontrollerService } from '../../../mincontroller/mincontroller.service';

describe('MincontrollerService', () => {
  let service: MincontrollerService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [MincontrollerService],
    }).compile();

    service = module.get<MincontrollerService>(MincontrollerService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });
});
