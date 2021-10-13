import { TestBed } from '@angular/core/testing';

import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { AskusernameService } from './askusername.service';

describe('AskusernameService', () => {
  let service: AskusernameService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(AskusernameService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
