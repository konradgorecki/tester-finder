import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectCountriesFormComponent } from './select-countries-form.component';

describe('SelectCountriesFormComponent', () => {
  let component: SelectCountriesFormComponent;
  let fixture: ComponentFixture<SelectCountriesFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SelectCountriesFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SelectCountriesFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
