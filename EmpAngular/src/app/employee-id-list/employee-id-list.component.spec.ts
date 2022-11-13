import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeIdListComponent } from './employee-id-list.component';

describe('EmployeeIdListComponent', () => {
  let component: EmployeeIdListComponent;
  let fixture: ComponentFixture<EmployeeIdListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmployeeIdListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeIdListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
