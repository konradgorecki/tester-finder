package com.tester.finder.util

import com.tester.finder.core.Bug
import com.tester.finder.core.Country
import com.tester.finder.core.Device
import com.tester.finder.core.Tester
import com.tester.finder.search.SearchCriteriaValidator
import com.tester.finder.search.SimpleTestersFinder
import com.tester.finder.search.TestersFinder

class TesterFinderFactory {
    static TestersFinder prepareFinder(List<Tester> testers, List<Device> devices, List<Bug> bugs) {
        Repositories repositories = TestRepositoryFactory.create(testers, devices, bugs)
        new SimpleTestersFinder(repositories.getTestersRepository(), repositories.getBugsRepository(),
                new SearchCriteriaValidator(repositories.getCountriesRepository(), repositories.getDevicesRepository()))
    }

    static TestersFinder prepareFinder(List<Tester> testers, List<Device> devices, List<Bug> bugs, List<Country> countries) {
        Repositories repositories = TestRepositoryFactory.create(testers, devices, bugs, countries)
        new SimpleTestersFinder(repositories.getTestersRepository(), repositories.getBugsRepository(),
                new SearchCriteriaValidator(repositories.getCountriesRepository(), repositories.getDevicesRepository()))
    }
}
