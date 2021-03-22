import {ChangeDetectionStrategy, Component, ElementRef, forwardRef, OnDestroy, OnInit, ViewChild} from '@angular/core';
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
import {MatChipInputEvent, MatChipList} from "@angular/material/chips";
import {Device} from "../../core/device/device.model";
import {DeviceService} from "../../core/device/device.service";
import {map, startWith} from "rxjs/operators";

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
export class SelectDevicesFormComponent implements ControlValueAccessor, OnInit, OnDestroy {
  form!: FormGroup;
  subscriptions: Subscription[] = [];

  selectable = true;
  removable = true;
  separatorKeysCodes: number[] = [ENTER, COMMA];
  ALL_DEVICES_DESC = 'ALL';
  filteredDevices !: Observable<Device[]>;
  devices: Device[] = [];
  allDevices!: Device[];

  @ViewChild('devicesInput') devicesInput!: ElementRef<HTMLInputElement>;
  @ViewChild('auto') matAutocomplete!: MatAutocomplete;
  @ViewChild('chipList') chipList!: MatChipList;

  onChange: any = () => {
  };
  onTouched: any = () => {
  };

  constructor(private formBuilder: FormBuilder, private deviceService: DeviceService) {
    this.form = this.formBuilder.group({
      devicesInput: [],
      devices: [this.devices]
    });

    this.subscriptions.push(
      this.form.controls.devicesInput.valueChanges.subscribe(value => {
        this.onChange(value);
        this.onTouched();
      })
    )
  }

  ngOnInit(): void {
    this.deviceService.getDevices().subscribe(devices => {
      this.allDevices = devices;
      this.filteredDevices = this.form.controls.devicesInput.valueChanges.pipe(
        startWith(null),
        map(device => this.filterOnValueChange(device)));
    });
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(s => s.unsubscribe());
  }

  writeValue(value: any): void {
    if (value) {
      this.devices = value;
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
    return this.form.valid && this.devices.length > 0 ? null : {profile: {valid: false},};
  }

  add(event: MatChipInputEvent): void {
    const value = event.value;

    if ((value || '').trim()) {
      let device = this.getDeviceForDescription(value.trim());
      this.addDevice(device);
    }
  }

  selected(event: MatAutocompleteSelectedEvent): void {
    this.addDevice(event.option.value)
  }

  private addDevice(device: Device): void {
    if (!device || this.isAllSelected() || this.isDeviceSelected(device)) {
      return;
    }
    let isAllDevicesMarker = device.description === this.ALL_DEVICES_DESC;
    if (isAllDevicesMarker) {
      this.devices.splice(0, this.devices.length);
    }
    this.devices.push(device);
    this.resetInput();
    this.updateFormControlValidityStatus();
  }

  remove(device: Device): void {
    const index = this.devices.indexOf(device);

    if (index >= 0) {
      this.devices.splice(index, 1);
      this.resetInput();
    }
    this.updateFormControlValidityStatus();
  }

  private filterOnValueChange(device: any): Device[] {
    let isDevice = device instanceof Device;
    let isStringWithDeviceDescription = (typeof device === 'string' || device instanceof String);
    if (device) {
      if (isDevice)
        return this._filter((device as Device).description);
      else if (isStringWithDeviceDescription)
        return this._filter(device as string);
    }
    return this.getAvailableDevices().slice();
  }

  private _filter(value: String): Device[] {
    const filterValue = value.toLowerCase();

    return this.getAvailableDevices().filter(device => device.description.toLowerCase().indexOf(filterValue) === 0);
  }

  getDeviceForDescription(description: string): Device {
    return this.allDevices.filter(device => device.description === description)[0];
  }

  resetInput(): void {
    this.devicesInput.nativeElement.value = '';
    this.form.controls.devicesInput.setValue(this.devices);
  }

  private getAvailableDevices(): Device[] {
    let allSelected = this.isAllSelected();
    return this.allDevices.filter(device => {
      return allSelected ? false : !this.isDeviceSelected(device);
    });
  }

  private isAllSelected(): boolean {
    return this.devices.some(device => device.description === this.ALL_DEVICES_DESC);
  }

  private isDeviceSelected(device: Device): boolean {
    return this.devices.some(selectedDevice => selectedDevice.id === device.id);
  }

  private updateFormControlValidityStatus(): void {
    if (this.form.dirty)
      this.chipList.errorState = this.devices.length <= 0;
  }
}
