import { TestBed } from '@angular/core/testing';

import { Universe.HttpwrapperService } from './universe.httpwrapper';

describe('Universe.HttpwrapperService', () => {
  let service: Universe.HttpwrapperService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Universe.HttpwrapperService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
