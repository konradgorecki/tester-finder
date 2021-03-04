package com.tester.finder.initialization.initializer.implementation;

import com.tester.finder.core.repository.DevicesRepository;
import com.tester.finder.initialization.initializer.DevicesDataInitializer;
import lombok.AllArgsConstructor;

import java.io.File;

@AllArgsConstructor
public class CsvFileDevicesDataInitializer implements DevicesDataInitializer {

    private final DevicesRepository devicesRepository;

    @Override
    public void initFromFile(File file) {
        throw new UnsupportedOperationException();
    }
}
