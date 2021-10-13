import { TestBed } from '@angular/core/testing';

import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { SecurityQuestionService } from './security-question.service';

describe('SecurityQuestionService', () => {
  let service: SecurityQuestionService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(SecurityQuestionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
