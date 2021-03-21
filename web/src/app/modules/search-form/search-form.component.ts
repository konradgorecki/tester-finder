import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-search-form',
  templateUrl: './search-form.component.html',
  styleUrls: ['./search-form.component.css']
})
export class SearchFormComponent implements OnInit {
  isLinear = true;
  countriesFormGroup !: FormGroup;
  devicesFormGroup !: FormGroup;

  constructor(private _formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.countriesFormGroup = this._formBuilder.group({
      countries: ['', Validators.required]
    });
    this.devicesFormGroup = this._formBuilder.group({
      devices: ['', Validators.required]
    });
  }
}
