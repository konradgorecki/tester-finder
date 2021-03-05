package com.tester.finder.initialization.initializer.implementation;

import com.opencsv.bean.CsvToBeanBuilder;
import com.tester.finder.core.repository.CountriesRepository;
import com.tester.finder.core.repository.TestersRepository;
import com.tester.finder.initialization.Tester;
import com.tester.finder.initialization.initializer.TestersDataInitializer;
import lombok.AllArgsConstructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@AllArgsConstructor
public class CsvFileTestersDataInitializer implements TestersDataInitializer {

    private final TestersRepository testersRepository;
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
        testers.stream()
                .map(this::convertTester)
                .forEach(testersRepository::save);
    }

    private com.tester.finder.core.Tester convertTester(Tester source) {
        return com.tester.finder.core.Tester.builder()
                .country(countriesRepository.findByCode(source.getCountry()))
                .id(source.getTesterId())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .lastLogin(source.getLastLogin())
                .build();
    }
}
