import {
  AfterViewInit,
  ChangeDetectionStrategy,
  Component, ElementRef,
  forwardRef,
  OnDestroy,
  OnInit,
  ViewChild
} from '@angular/core';
import {
  FormArray,
  FormBuilder,
  FormControl,
  FormGroup,
  NG_VALIDATORS,
  NG_VALUE_ACCESSOR,
  Validators
} from "@angular/forms";
import {Observable, Subscription} from "rxjs";
import {COMMA, ENTER} from "@angular/cdk/keycodes";
import {Device} from "../../core/device/device.model";
import {map, startWith} from "rxjs/operators";
import {MatAutocomplete, MatAutocompleteSelectedEvent} from "@angular/material/autocomplete";
import {MatChipInputEvent, MatChipList} from "@angular/material/chips";

@Component({
  selector: 'app-select-devices-form',
  templateUrl: './select-devices-form.component.html',
  styleUrls: ['./select-devices-form.component.css'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => SelectDevicesFormComponent),
      multi: true
    },
    {
      provide: NG_VALIDATORS,
      useExisting: forwardRef(() => SelectDevicesFormComponent),
      multi: true
    }
  ],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class SelectDevicesFormComponent implements OnInit, OnInit, OnDestroy, AfterViewInit {

  form!: FormGroup;
  subscriptions: Subscription[] = [];

  selectable = true;
  removable = true;
  separatorKeysCodes: number[] = [ENTER, COMMA];
  filteredDevices !: Observable<Device[]>;
  devices: Device[] = [];
  // allDevices!: Device[];
  allDevices: Device[] = [this.getDevice('Samsung A71', 1), this.getDevice('Huawei Mate X', 2), this.getDevice('iPhone X', 3), this.getDevice('TestDevice 4', 4), this.getDevice('Motorola RAZR', 7)];

  onChange: any = () => {};
  onTouched: any = () => {};
  private ALL_DEVICES_DESC = 'ALL';

  @ViewChild('devicesInput') devicesInput!: ElementRef<HTMLInputElement>;
  @ViewChild('auto') matAutocomplete!: MatAutocomplete;
  @ViewChild('chipList') chipList!: MatChipList;

  get deviceControls(): FormArray {
    return this.form.controls.devices as FormArray;
  }

  /**
   * TODO DELETE THIS
   * @param desc
   * @param id
   */
  getDevice(desc: string, id: number): Device {
    let d = new Device();
    d.description = desc;
    d.id = id;
    return d;
  }

  get value(): Device[] {
    alert ('Get value called');
    //TODO remove alert + swith impl to formarray controls
    return this.devices;
  }

  set value(value: Device[]) {
    this.devices = value;
    this.onChange(value);
    this.onTouched();
  }

  constructor(private formBuilder: FormBuilder/*, private deviceService DeviceService*/) {
    this.form = this.formBuilder.group({
      devices: this.formBuilder.array([], Validators.required)
    });

    this.subscriptions.push(
      this.form.valueChanges.subscribe(value => {
        this.onChange(value);
        this.onTouched();
      })
    )
  }

  ngOnInit() : void {
    // this.deviceService.getDevices().subscribe(devices => {
    //   this.allDevices = devices;
    //   this.filteredDevices = this.form.controls.devices.valueChanges.pipe(
    //     startWith(null),
    //     map(device => this.filterOnValueChange(device)));
    // });


    this.filteredDevices = this.form.controls.devices.valueChanges.pipe(
      startWith(null),
      map(device => this.filterOnValueChange(device)));
  }

  ngAfterViewInit(): void {
    this.updateFormControlValidityStatus();
  }

  private filterOnValueChange(device: any) : Device[] {
    let isDevice = device instanceof Device;
    let isStringWithDeviceDesc = (typeof device === 'string' || device instanceof String);
    if (device) {
      if (isDevice)
        return this._filter((device as Device).description);
      else if (isStringWithDeviceDesc)
        return this._filter(device as string);
    }
    return this.getAvailableDevices().slice();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(s => s.unsubscribe());
  }

  registerOnChange(fn: any): void {
    this.onChange = fn;
  }

  registerOnTouched(fn: any): void {
    this.onTouched = fn;
  }

  writeValue(value: any): void {
    if (value) {
      this.value = value;
    }
    if (value === null) {
      this.form.reset();
    }
  }

  validate(_: FormControl) {
    return this.form.valid && this.deviceControls.length > 0 ? null : {profile: { valid: false}, };
  }

  add(event: MatChipInputEvent): void {
    const value = event.value;

    if ((value || '').trim()) {
      let device = this.getDeviceForDescription(value.trim());
      this.addDevice(device);
    }
  }

  getDeviceForDescription(desc: string): Device {
    return this.allDevices.filter(device => device.description === desc)[0];
  }

  remove(device: Device): void {
    const index = this.deviceControls.value.indexOf(device);

    if (index >= 0) {
      this.deviceControls.removeAt(index);
      this.resetInput();
    }
    this.updateFormControlValidityStatus();
  }

  resetInput(): void {
    this.devicesInput.nativeElement.value = '';
  }

  selected(event: MatAutocompleteSelectedEvent): void {
    this.addDevice(event.option.value)
  }

  private _filter(value: String): Device[] {
    const filterValue = value.toLowerCase();

    return this.getAvailableDevices().filter(device => device.description.toLowerCase().indexOf(filterValue) === 0);
  }

  private addDevice(device: Device): void {
    if (!device || this.isAllSelected() || this.isDeviceSelected(device)) {
      return;
    }
    let isAllDevicesMarker = device.description === this.ALL_DEVICES_DESC;
    if (isAllDevicesMarker) {
      this.deviceControls.clear();
    }
    this.deviceControls.push(this.formBuilder.control(device));
    this.resetInput();
    this.updateFormControlValidityStatus();
  }

  private getAvailableDevices(): Device[] {
    let allSelected = this.isAllSelected();
    return this.allDevices.filter(device => {
      return allSelected ? false : !this.isDeviceSelected(device);
    });
  }

  private isAllSelected(): boolean {
    return this.deviceControls.value.some((device: Device) => device.description === this.ALL_DEVICES_DESC);
  }

  private isDeviceSelected(device: Device): boolean {
    return this.deviceControls.value.some((selectedDevice: Device) => selectedDevice.id === device.id);
  }

  private updateFormControlValidityStatus(): void{
    if (this.form.controls.devices.dirty)
      this.chipList.errorState = this.deviceControls.length <= 0;
  }
}
