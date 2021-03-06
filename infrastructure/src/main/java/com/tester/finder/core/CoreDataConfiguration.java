package com.tester.finder.core;

import com.tester.finder.core.repository.BugsRepository;
import com.tester.finder.core.repository.CountriesRepository;
import com.tester.finder.core.repository.DevicesRepository;
import com.tester.finder.core.repository.TestersRepository;
import com.tester.finder.core.repository.implementation.InMemoryBugsRepository;
import com.tester.finder.core.repository.implementation.InMemoryCountriesRepository;
import com.tester.finder.core.repository.implementation.InMemoryDevicesRepository;
import com.tester.finder.core.repository.implementation.InMemoryTestersRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreDataConfiguration {

    @Bean
    public CountriesRepository countriesRepository() {
        return new InMemoryCountriesRepository();
    }

    @Bean
    public TestersRepository testersRepository() {
        return new InMemoryTestersRepository();
    }

    @Bean
    public DevicesRepository devicesRepository() {
        return new InMemoryDevicesRepository();
    }

    @Bean
    public BugsRepository bugsRepository() {
        return new InMemoryBugsRepository();
    }

    @Bean
    public CoreDataFacade coreDataFacade() {
        return new CoreDataFacade(countriesRepository(), devicesRepository());
    }

}
