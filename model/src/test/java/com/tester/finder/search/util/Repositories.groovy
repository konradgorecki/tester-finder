package com.tester.finder.search.util

import com.tester.finder.core.BugsRepository
import com.tester.finder.core.CountriesRepository
import com.tester.finder.core.DevicesRepository
import com.tester.finder.core.TestersRepository

class Repositories {
    private final TestersRepository testersRepository
    private final DevicesRepository devicesRepository
    private final BugsRepository bugsRepository
    private final CountriesRepository countriesRepository

    Repositories(TestersRepository testersRepository, DevicesRepository devicesRepository, BugsRepository bugsRepository, CountriesRepository countriesRepository) {
        this.testersRepository = testersRepository
        this.devicesRepository = devicesRepository
        this.bugsRepository = bugsRepository
        this.countriesRepository = countriesRepository
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
}
