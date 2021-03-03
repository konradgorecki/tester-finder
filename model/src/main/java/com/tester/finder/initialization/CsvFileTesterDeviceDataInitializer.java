package com.tester.finder.initialization;

import com.tester.finder.core.repository.DevicesRepository;
import com.tester.finder.core.repository.TestersRepository;
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
