package com.tester.finder.initialization.initializer.implementation;

import com.tester.finder.core.repository.CountriesRepository;
import com.tester.finder.initialization.initializer.CountriesDataInitializer;
import lombok.AllArgsConstructor;

import java.io.File;

@AllArgsConstructor
public class CsvFileCountriesDataInitializer implements CountriesDataInitializer {

    private final CountriesRepository countriesRepository;

    @Override
    public void initFromFile(File file) {
        throw new UnsupportedOperationException();
    }
}
