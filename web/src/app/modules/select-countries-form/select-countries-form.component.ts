import {ChangeDetectionStrategy, Component, ElementRef, forwardRef, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {Country} from "../../core/country/country.model";
import {
  ControlValueAccessor,
  FormBuilder,
  FormControl,
  FormGroup,
  NG_VALIDATORS,
  NG_VALUE_ACCESSOR
} from "@angular/forms";
import {Observable, Subscription} from "rxjs";
import {COMMA, ENTER} from "@angular/cdk/keycodes";
import {MatAutocomplete, MatAutocompleteSelectedEvent} from "@angular/material/autocomplete";
import {map, startWith} from "rxjs/operators";
import {MatChipInputEvent, MatChipList} from "@angular/material/chips";
import {CountryService} from "../../core/country/country.service";

@Component({
  selector: 'app-select-countries-form',
  templateUrl: './select-countries-form.component.html',
  styleUrls: ['./select-countries-form.component.css'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => SelectCountriesFormComponent),
      multi: true
    },
    {
      provide: NG_VALIDATORS,
      useExisting: forwardRef(() => SelectCountriesFormComponent),
      multi: true
    }
  ],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class SelectCountriesFormComponent implements ControlValueAccessor, OnInit, OnDestroy {
  form!: FormGroup;
  subscriptions: Subscription[] = [];

  selectable = true;
  removable = true;
  separatorKeysCodes: number[] = [ENTER, COMMA];
  ALL_COUNTRIES = 'ALL';
  filteredCountries !: Observable<Country[]>;
  countries: Country[] = [];
  allCountries!: Country[];

  @ViewChild('countriesInput') countriesInput!: ElementRef<HTMLInputElement>;
  @ViewChild('auto') matAutocomplete!: MatAutocomplete;
  @ViewChild('chipList') chipList!: MatChipList;

  onChange: any = () => {
  };
  onTouched: any = () => {
  };

  constructor(private formBuilder: FormBuilder, private countryService: CountryService) {
    this.form = this.formBuilder.group({
      countriesInput: [],
      countries: [this.countries]
    });

    this.subscriptions.push(
      this.form.controls.countriesInput.valueChanges.subscribe(value => {
        this.onChange(value);
        this.onTouched();
      })
    )
  }

  ngOnInit(): void {
    this.countryService.getCountries().subscribe(countries => {
      this.allCountries = countries;
      this.filteredCountries = this.form.controls.countriesInput.valueChanges.pipe(
        startWith(null),
        map(country => this.filterOnValueChange(country)));
    });
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(s => s.unsubscribe());
  }

  writeValue(value: any): void {
    if (value) {
      this.countries = value;
      this.onChange(value);
      this.onTouched();
    }
    if (value === null) {
      this.form.reset();
    }
  }

  registerOnChange(fn: any): void {
    this.onChange = fn;
  }

  registerOnTouched(fn: any): void {
    this.onTouched = fn;
  }

  validate(_: FormControl) {
    return this.form.valid && this.countries.length > 0 ? null : {profile: {valid: false},};
  }

  add(event: MatChipInputEvent): void {
    const value = event.value;

    if ((value || '').trim()) {
      let country = this.getCountryForCode(value.trim());
      this.addCountry(country);
    }
  }

  selected(event: MatAutocompleteSelectedEvent): void {
    this.addCountry(event.option.value)
  }

  private addCountry(country: Country): void {
    if (!country || this.isAllSelected() || this.isCountrySelected(country)) {
      return;
    }
    let isAllCountriesMarker = country.code === this.ALL_COUNTRIES;
    if (isAllCountriesMarker) {
      this.countries.splice(0, this.countries.length);
    }
    this.countries.push(country);
    this.resetInput();
    this.updateFormControlValidityStatus();
  }

  remove(country: Country): void {
    const index = this.countries.indexOf(country);

    if (index >= 0) {
      this.countries.splice(index, 1);
      this.resetInput();
    }
    this.updateFormControlValidityStatus();
  }

  private filterOnValueChange(country: any): Country[] {
    let isCountry = country instanceof Country;
    let isStringWithCountryCode = (typeof country === 'string' || country instanceof String);
    if (country) {
      if (isCountry)
        return this._filter((country as Country).code);
      else if (isStringWithCountryCode)
        return this._filter(country as string);
    }
    return this.getAvailableCountries().slice();
  }

  private _filter(value: String): Country[] {
    const filterValue = value.toLowerCase();

    return this.getAvailableCountries().filter(country => country.code.toLowerCase().indexOf(filterValue) === 0);
  }

  getCountryForCode(code: string): Country {
    return this.allCountries.filter(country => country.code === code)[0];
  }

  resetInput(): void {
    this.countriesInput.nativeElement.value = '';
    this.form.controls.countriesInput.setValue(this.countries);
  }

  private getAvailableCountries(): Country[] {
    let allSelected = this.isAllSelected();
    return this.allCountries.filter(country => {
      return allSelected ? false : !this.isCountrySelected(country);
    });
  }

  private isAllSelected(): boolean {
    return this.countries.some(country => country.code === this.ALL_COUNTRIES);
  }

  private isCountrySelected(country: Country): boolean {
    return this.countries.some(selectedCountry => selectedCountry.code === country.code);
  }

  private updateFormControlValidityStatus(): void {
    if (this.form.dirty)
      this.chipList.errorState = this.countries.length <= 0;
  }
}
