import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WrongAccessComponent } from './wrong-access.component';

describe('WrongAccessComponent', () => {
  let component: WrongAccessComponent;
  let fixture: ComponentFixture<WrongAccessComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WrongAccessComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WrongAccessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
