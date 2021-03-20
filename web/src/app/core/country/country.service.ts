import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Country} from "./country.model";
import {API_URL} from "../../settings.constant";

@Injectable({
  providedIn: 'root'
})
export class CountryService {

  private readonly apiCountriesUrl = `${API_URL}/core/countries`

  constructor(private httpClient: HttpClient) {
  }

  public getCountries(): Observable<Country[]> {
    return this.httpClient.get<Country[]>(`${this.apiCountriesUrl}`);
  }
}
