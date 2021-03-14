import { Injectable } from '@angular/core';
import {API_URL} from "../../settings.constant";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Device} from "./device.model";

@Injectable({
  providedIn: 'root'
})
export class DeviceService {

  private readonly apiDevicesUrl = `${API_URL}/core/devices`

  constructor(private httpClient: HttpClient) {
  }

  getDevices(): Observable<Device[]> {
    return this.httpClient.get<Device[]>(`${this.apiDevicesUrl}`);
  }
}
