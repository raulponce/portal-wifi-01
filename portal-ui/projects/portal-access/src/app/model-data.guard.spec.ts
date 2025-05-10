import { TestBed } from '@angular/core/testing';

import { ModelDataGuard } from './model-data.guard';

describe('ModelDataGuard', () => {
  let guard: ModelDataGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(ModelDataGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
