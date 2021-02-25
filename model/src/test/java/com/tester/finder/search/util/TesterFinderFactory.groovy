package com.tester.finder.search.util

import com.tester.finder.core.Bug
import com.tester.finder.core.Device
import com.tester.finder.core.Tester
import com.tester.finder.search.SearchCriteriaValidator
import com.tester.finder.search.SimpleTestersFinder
import com.tester.finder.search.TestersFinder

class TesterFinderFactory {
    static TestersFinder prepareFinder(List<Tester> testers, List<Device> devices, List<Bug> bugs) {
        Repositories repositories = TestRepositories.prepareRepositories(testers, devices, bugs)
        new SimpleTestersFinder(repositories.getTestersRepository(), repositories.getBugsRepository(),
                new SearchCriteriaValidator(repositories.getCountriesRepository(), repositories.getDevicesRepository()))
    }
}
