import { TestBed } from '@angular/core/testing';

import { InputQdataGuard } from './input-qdata.guard';

describe('InputQdataGuard', () => {
  let guard: InputQdataGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(InputQdataGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
