package com.tester.finder.search;

import com.tester.finder.core.repository.BugsRepository;
import com.tester.finder.core.repository.CountriesRepository;
import com.tester.finder.core.repository.DevicesRepository;
import com.tester.finder.core.repository.TestersRepository;
import com.tester.finder.search.finder.SearchFacade;
import com.tester.finder.search.finder.implementation.SearchCriteriaValidator;
import com.tester.finder.search.finder.implementation.SimpleTestersFinder;
import com.tester.finder.search.repository.SearchCriteriaRepository;
import com.tester.finder.search.repository.implementation.InMemorySearchCriteriaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SearchConfiguration {

    @Bean
    public SearchCriteriaValidator searchCriteriaValidator(CountriesRepository countriesRepository, DevicesRepository devicesRepository) {
        return new SearchCriteriaValidator(countriesRepository, devicesRepository);
    }

    @Bean
    public SearchCriteriaRepository searchCriteriaRepository() {
        return new InMemorySearchCriteriaRepository();
    }

    @Bean
    public SearchFacade searchFacade(TestersRepository testersRepository, BugsRepository bugsRepository, SearchCriteriaValidator searchCriteriaValidator, SearchCriteriaRepository searchCriteriaRepository) {
        return new SimpleTestersFinder(testersRepository, bugsRepository, searchCriteriaValidator, searchCriteriaRepository);
    }
}
