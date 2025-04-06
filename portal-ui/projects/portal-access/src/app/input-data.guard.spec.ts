import { TestBed } from '@angular/core/testing';

import { InputDataGuard } from './input-data.guard';

describe('InputDataGuard', () => {
  let guard: InputDataGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(InputDataGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
