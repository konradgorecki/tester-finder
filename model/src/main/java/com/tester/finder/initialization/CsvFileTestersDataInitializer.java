package com.tester.finder.initialization;

import com.tester.finder.core.repository.TestersRepository;
import lombok.AllArgsConstructor;

import java.io.File;

@AllArgsConstructor
public class CsvFileTestersDataInitializer implements TestersDataInitializer {

    private final TestersRepository testersRepository;

    @Override
    public void initFromFile(File file) {
        throw new UnsupportedOperationException();
    }
}
