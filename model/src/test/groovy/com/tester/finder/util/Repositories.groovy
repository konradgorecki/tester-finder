package com.tester.finder.util

import com.tester.finder.core.repository.BugsRepository
import com.tester.finder.core.repository.CountriesRepository
import com.tester.finder.core.repository.DevicesRepository
import com.tester.finder.core.repository.TestersRepository
import com.tester.finder.search.repository.SearchCriteriaRepository

class Repositories {
    private final TestersRepository testersRepository
    private final DevicesRepository devicesRepository
    private final BugsRepository bugsRepository
    private final CountriesRepository countriesRepository
    private final SearchCriteriaRepository searchCriteriaRepository

    Repositories(TestersRepository testersRepository, DevicesRepository devicesRepository, BugsRepository bugsRepository, CountriesRepository countriesRepository, SearchCriteriaRepository searchCriteriaRepository) {
        this.testersRepository = testersRepository
        this.devicesRepository = devicesRepository
        this.bugsRepository = bugsRepository
        this.countriesRepository = countriesRepository
        this.searchCriteriaRepository = searchCriteriaRepository
    }

    TestersRepository getTestersRepository() {
        return testersRepository
    }

    DevicesRepository getDevicesRepository() {
        return devicesRepository
    }

    BugsRepository getBugsRepository() {
        return bugsRepository
    }

    CountriesRepository getCountriesRepository() {
        return countriesRepository
    }

    SearchCriteriaRepository getSearchCriteriaRepository() {
        return searchCriteriaRepository
    }
}
