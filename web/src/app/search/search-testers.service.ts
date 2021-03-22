import {Injectable} from '@angular/core';
import {API_URL} from "../settings.constant";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {FoundTesters} from "./found-testers.model";
import {SearchCriteria} from "./search-criteria.model";

@Injectable({
  providedIn: 'root'
})
export class SearchTestersService {

  private readonly searchTestersUrl = `${API_URL}/search/`

  constructor(private httpClient: HttpClient) {
  }

  createSearch(searchCriteria: SearchCriteria): Observable<String> {
    return this.httpClient.post<String>(`${this.searchTestersUrl}` + `create`, searchCriteria);
  }

  getTestersForSearch(searchId: String): Observable<FoundTesters> {
    return this.httpClient.get<FoundTesters>(`${this.searchTestersUrl}` + `results/` + searchId);
  }
}
