package com.tester.finder.initialization;

import com.tester.finder.core.repository.CountriesRepository;
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
