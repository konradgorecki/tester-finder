package com.tester.finder.search;

import com.tester.finder.core.repository.BugsRepository;
import com.tester.finder.core.repository.CountriesRepository;
import com.tester.finder.core.repository.DevicesRepository;
import com.tester.finder.core.repository.TestersRepository;
import com.tester.finder.search.finder.SearchFacade;
import com.tester.finder.search.finder.implementation.SearchCriteriaValidator;
import com.tester.finder.search.finder.implementation.SimpleTestersFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SearchConfiguration {

    @Bean
    public SearchCriteriaValidator searchCriteriaValidator(CountriesRepository countriesRepository, DevicesRepository devicesRepository) {
        return new SearchCriteriaValidator(countriesRepository, devicesRepository);
    }

    @Bean
    public SearchFacade searchFacade(TestersRepository testersRepository, BugsRepository bugsRepository, SearchCriteriaValidator searchCriteriaValidator) {
        return new SimpleTestersFinder(testersRepository, bugsRepository, searchCriteriaValidator);
    }
}
