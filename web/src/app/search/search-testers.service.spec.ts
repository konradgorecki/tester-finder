import { TestBed } from '@angular/core/testing';

import { SearchTestersService } from './search-testers.service';

describe('SearchTestersService', () => {
  let service: SearchTestersService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SearchTestersService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
