import {Component, Input, OnInit} from '@angular/core';
import {SearchTestersService} from "../../search/search-testers.service";
import {SearchCriteria} from "../../search/search-criteria.model";
import {FoundTester} from "../../search/found-tester.model";
import {Tester} from "../../search/tester.model";
import {Country} from "../../core/country/country.model";
import {Device} from "../../core/device/device.model";

@Component({
  selector: 'app-search-executor',
  templateUrl: './search-executor.component.html',
  styleUrls: ['./search-executor.component.css']
})
export class SearchExecutorComponent implements OnInit {
  // foundTesters: FoundTester[] = [SearchExecutorComponent.getFoundTester(), SearchExecutorComponent.getFoundTester(), SearchExecutorComponent.getFoundTester()]
  foundTesters!: FoundTester[];

  @Input()
  selectedCountries!: Country[];

  @Input()
  selectedDevices!: Device[];

  constructor(private searchTestersService: SearchTestersService) { }

  ngOnInit(): void {
  }

  search() {
    this.searchTestersService.createSearch(this.prepareSearchCriteria())
      .subscribe(searchId =>
        this.searchTestersService.getTestersForSearch(searchId)
        .subscribe(foundTesters => this.foundTesters = foundTesters.testers)
    )
  }

  private prepareSearchCriteria(): SearchCriteria {
    let searchCriteria = new SearchCriteria();
    searchCriteria.countryCodes = this.selectedCountries.map(country => country.code);
    searchCriteria.deviceIds = this.selectedDevices.map(device => device.id);
    return searchCriteria;
  }

  private static getFoundTester() {
    let tester = new Tester();
    let country = new Country();
    country.code = 'PL';
    tester.country = country;
    tester.firstName = 'John';
    tester.lastName = 'Czempinsky';
    tester.lastLogin = new Date();
    tester.id = 1;
    let foundTester = new FoundTester();
    foundTester.tester = tester;
    foundTester.experience = 10;
    return foundTester;
  }
}
