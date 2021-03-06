package com.tester.finder.initialization;

import com.tester.finder.core.repository.BugsRepository;
import com.tester.finder.core.repository.CountriesRepository;
import com.tester.finder.core.repository.DevicesRepository;
import com.tester.finder.core.repository.TestersRepository;
import com.tester.finder.initialization.initializer.DataInitializationFacade;
import com.tester.finder.initialization.initializer.implementation.CsvFilesDataInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializerConfiguration {

    @Bean
    public DataInitializationFacade dataInitializationFacade(CountriesRepository countriesRepository,
                                                             TestersRepository testersRepository,
                                                             DevicesRepository devicesRepository,
                                                             BugsRepository bugsRepository) {
        return new CsvFilesDataInitializer(countriesRepository, testersRepository, devicesRepository, bugsRepository);
    }
}
