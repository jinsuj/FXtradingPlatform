import { TestBed } from '@angular/core/testing';

import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { MakeTradeService } from './make-trade.service';
import {} from 'jasmine';
describe('MakeTradeService', () => {
  let service: MakeTradeService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(MakeTradeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('#getCurrencyId should return value',
    (done: DoneFn) => {
      jasmine.DEFAULT_TIMEOUT_INTERVAL = 30000;
      // service = TestBed.inject(MakeTradeService);
      service.getCurrencyId({}).subscribe((value: any)  => {
        expect(value).not.toBeNull();
        done();
      });
    });


});
