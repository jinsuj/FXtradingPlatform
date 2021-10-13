import { TestBed } from '@angular/core/testing';

import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import {HttpClientModule} from '@angular/common/http';

import { UpdatePasswordService } from './update-password.service';

describe('UpdatePasswordService', () => {
  let service: UpdatePasswordService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    service = TestBed.inject(UpdatePasswordService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
