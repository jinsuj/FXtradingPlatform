import { TestBed } from '@angular/core/testing';

import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { TradeHistoryService } from './trade-history.service';

describe('TradeHistoryService', () => {
  let service: TradeHistoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(TradeHistoryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
