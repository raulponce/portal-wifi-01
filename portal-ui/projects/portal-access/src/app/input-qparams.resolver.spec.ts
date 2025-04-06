import { TestBed } from '@angular/core/testing';

import { InputQparamsResolver } from './input-qparams.resolver';

describe('InputQparamsResolver', () => {
  let resolver: InputQparamsResolver;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    resolver = TestBed.inject(InputQparamsResolver);
  });

  it('should be created', () => {
    expect(resolver).toBeTruthy();
  });
});
