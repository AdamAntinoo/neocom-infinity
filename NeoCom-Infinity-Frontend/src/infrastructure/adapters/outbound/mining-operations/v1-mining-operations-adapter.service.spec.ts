import { TestBed } from '@angular/core/testing';

import { V1MiningOperationsAdapterService } from './v1-mining-operations-adapter.service';

fdescribe('ADAPTER V1MiningOperationsAdapterService [Module: Infrastructure]', () => {
  let service: V1MiningOperationsAdapterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(V1MiningOperationsAdapterService);
  });

 describe('Constructor contract phase', () => {
    it('should be created', () => {
        expect(service).toBeTruthy()
      })
  })
  describe('Service Delivery phase', () => {
    it('when the downloadMiningOperationsForCharacter is called we return a valid Promise', () => {
        expect(service).toBeTruthy()
        const pilotId: number = 93813310
        const sut = service.downloadMiningOperationsForCharacter(pilotId)
        expect(sut).toBeDefined
      })
  })
})
