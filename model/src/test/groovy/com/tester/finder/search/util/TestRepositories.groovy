package com.tester.finder.search.util

import com.tester.finder.core.Bug
import com.tester.finder.core.repository.BugsRepository
import com.tester.finder.core.repository.CountriesRepository
import com.tester.finder.core.Country
import com.tester.finder.core.Device
import com.tester.finder.core.repository.DevicesRepository
import com.tester.finder.core.repository.implementation.InMemoryBugsRepository
import com.tester.finder.core.repository.implementation.InMemoryCountriesRepository
import com.tester.finder.core.repository.implementation.InMemoryDevicesRepository
import com.tester.finder.core.repository.implementation.InMemoryTestersRepository
import com.tester.finder.core.Tester
import com.tester.finder.core.repository.TestersRepository

class TestRepositories {

    static Repositories prepareRepositories(List<Tester> testers, List<Device> devices, List<Bug> bugs) {
        TestersRepository testersRepository = initTestersRepository(testers)
        DevicesRepository devicesRepository = initDevicesRepository(devices)
        BugsRepository bugsRepository = initBugsRepository(bugs)
        CountriesRepository countriesRepository = initCountriesRepository(testers)

        new Repositories(testersRepository, devicesRepository, bugsRepository, countriesRepository)
    }

    static TestersRepository initTestersRepository(List<Tester> testers) {
        TestersRepository testersRepository = new InMemoryTestersRepository()
        testers.each {tester -> testersRepository.save(tester)}
        testersRepository
    }

    static DevicesRepository initDevicesRepository(List<Device> devices) {
        DevicesRepository devicesRepository = new InMemoryDevicesRepository()
        devices.each {device -> devicesRepository.save(device)}
        devicesRepository
    }

    static BugsRepository initBugsRepository(List<Bug> bugs) {
        BugsRepository bugsRepository = new InMemoryBugsRepository()
        bugs.each {bug -> bugsRepository.save(bug)}
        bugsRepository
    }

    static CountriesRepository initCountriesRepository(List<Tester> testers) {
        Set<Country> countries = new HashSet<>()
        testers.each {tester -> countries.add(tester.getCountry())}
        CountriesRepository countriesRepository = new InMemoryCountriesRepository()
        countries.each {country -> countriesRepository.save(country)}
        countriesRepository
    }
}
