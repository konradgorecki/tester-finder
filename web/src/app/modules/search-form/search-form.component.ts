import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Country} from "../../core/country/country.model";

@Component({
  selector: 'app-search-form',
  templateUrl: './search-form.component.html',
  styleUrls: ['./search-form.component.css']
})
export class SearchFormComponent implements OnInit {
  isLinear = true;
  firstFormGroup !: FormGroup;
  secondFormGroup !: FormGroup;
  selectedCountries!: Country[];

  constructor(private _formBuilder: FormBuilder) {}

  ngOnInit() : void {
    this.firstFormGroup = this._formBuilder.group({
      countries: new FormControl(this.selectedCountries, [Validators.required, Validators.minLength(1)])
    });
    this.secondFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required]
    });
  }

  checkCountries(firstFormGroup: FormGroup) {
    alert(firstFormGroup.value);
  }
}
