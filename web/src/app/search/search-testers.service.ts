import {Injectable} from '@angular/core';
import {API_URL} from "../settings.constant";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {FoundTesters} from "./found-testers.model";
import {SearchCriteria} from "./search-criteria.model";

@Injectable({
  providedIn: 'root'
})
export class SearchTestersService {

  private readonly searchTestersUrl = `${API_URL}/search`

  constructor(private httpClient: HttpClient) {
  }

  getTestersForSearch(searchId: String): Observable<FoundTesters> {
    return new Observable<FoundTesters>();//TODO implement
  }

  createSearch(searchCriteria: SearchCriteria): Observable<String> {
    return new Observable<String>();//TODO implement
  }
}
