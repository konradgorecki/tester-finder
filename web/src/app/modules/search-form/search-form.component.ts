import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {MatAutocomplete} from "@angular/material/autocomplete";
import {MatHorizontalStepper, MatStepperModule} from "@angular/material/stepper";

@Component({
  selector: 'app-search-form',
  templateUrl: './search-form.component.html',
  styleUrls: ['./search-form.component.css']
})
export class SearchFormComponent implements OnInit {
  isLinear = true;
  countriesFormGroup !: FormGroup;
  devicesFormGroup !: FormGroup;

  @ViewChild('stepper') stepper!:  MatHorizontalStepper;

  constructor(private _formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.countriesFormGroup = this._formBuilder.group({
      countries: []
    });
    this.devicesFormGroup = this._formBuilder.group({
      devices: []
    });
  }

  reset(): void {
    this.countriesFormGroup.controls.countries.setValue([]);
    this.devicesFormGroup.controls.devices.setValue([]);
    this.stepper.reset();
  }
}
