import { TestBed } from '@angular/core/testing';

import { HttpClientWrapperService } from './httpclientwrapper.service';

describe('HttpClientWrapperService', () => {
   beforeEach(() => TestBed.configureTestingModule({}));

   it('should be created', () => {
      const service: HttpClientWrapperService = TestBed.get(HttpClientWrapperService);
      expect(service).toBeTruthy();
   });
});
