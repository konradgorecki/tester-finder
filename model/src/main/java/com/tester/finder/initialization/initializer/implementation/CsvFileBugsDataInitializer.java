package com.tester.finder.initialization.initializer.implementation;

import com.tester.finder.core.repository.BugsRepository;
import com.tester.finder.initialization.initializer.BugsDataInitializer;
import lombok.AllArgsConstructor;

import java.io.File;

@AllArgsConstructor
public class CsvFileBugsDataInitializer implements BugsDataInitializer {

    private final BugsRepository bugsRepository;

    @Override
    public void initFromFile(File bugsFile) {
        throw new UnsupportedOperationException();
    }
}
