package com.tester.finder.initialization.initializer.implementation;

import com.tester.finder.core.repository.DevicesRepository;
import com.tester.finder.core.repository.TestersRepository;
import com.tester.finder.initialization.initializer.TesterDeviceDataInitializer;
import lombok.AllArgsConstructor;

import java.io.File;

@AllArgsConstructor
public class CsvFileTesterDeviceDataInitializer implements TesterDeviceDataInitializer {

    private final TestersRepository testersRepository;
    private final DevicesRepository devicesRepository;

    @Override
    public void initFromFile(File file) {
        throw new UnsupportedOperationException();
    }
}
