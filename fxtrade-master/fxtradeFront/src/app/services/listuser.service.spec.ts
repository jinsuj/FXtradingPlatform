import { TestBed } from '@angular/core/testing';

import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ListuserService } from './listuser.service';

describe('ListuserService', () => {
  let service: ListuserService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(ListuserService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
