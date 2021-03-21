import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectDevicesFormComponent } from './select-devices-form.component';

describe('SelectDevicesFormComponent', () => {
  let component: SelectDevicesFormComponent;
  let fixture: ComponentFixture<SelectDevicesFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SelectDevicesFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SelectDevicesFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
