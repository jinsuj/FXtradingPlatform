import { TestBed } from '@angular/core/testing';

import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { TradeDetailsService } from './trade-details.service';

describe('TradeDetailsService', () => {
  let service: TradeDetailsService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(TradeDetailsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
