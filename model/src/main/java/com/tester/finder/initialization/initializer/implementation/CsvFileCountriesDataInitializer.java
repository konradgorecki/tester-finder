package com.tester.finder.initialization.initializer.implementation;

import com.opencsv.bean.CsvToBeanBuilder;
import com.tester.finder.core.Country;
import com.tester.finder.core.repository.CountriesRepository;
import com.tester.finder.initialization.Tester;
import com.tester.finder.initialization.initializer.CountriesDataInitializer;
import lombok.AllArgsConstructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
public class CsvFileCountriesDataInitializer implements CountriesDataInitializer {

    private final CountriesRepository countriesRepository;

    @Override
    public void initFromFile(File file) {
        List<Tester> testers;
        try {
            testers = new CsvToBeanBuilder<Tester>(new FileReader(file))
                    .withType(Tester.class)
                    .withFilter(new SkipEmptyLinesCsvToBeanFilter()).build().parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException(String.format("Testers File not found! %s", file.getAbsolutePath()));
        }
        if (testers == null) {
            return;
        }
        Set<Country> countries = new HashSet<>();
        testers.forEach(tester -> countries.add(new Country(tester.getCountry())));
        countries.forEach(countriesRepository::save);
    }
}
