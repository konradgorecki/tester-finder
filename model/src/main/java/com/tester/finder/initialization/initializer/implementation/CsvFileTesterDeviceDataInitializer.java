package com.tester.finder.initialization.initializer.implementation;

import com.opencsv.bean.CsvToBeanBuilder;
import com.tester.finder.core.Device;
import com.tester.finder.core.Tester;
import com.tester.finder.core.repository.DevicesRepository;
import com.tester.finder.core.repository.TestersRepository;
import com.tester.finder.initialization.TesterDeviceMapping;
import com.tester.finder.initialization.initializer.TesterDeviceDataInitializer;
import lombok.AllArgsConstructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@AllArgsConstructor
public class CsvFileTesterDeviceDataInitializer implements TesterDeviceDataInitializer {

    private final TestersRepository testersRepository;
    private final DevicesRepository devicesRepository;

    @Override
    public void initFromFile(File file) {
        List<TesterDeviceMapping> mappings;
        try {
            mappings = new CsvToBeanBuilder<TesterDeviceMapping>(new FileReader(file))
                    .withType(TesterDeviceMapping.class)
                    .withFilter(new SkipEmptyLinesCsvToBeanFilter()).build().parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException(String.format("Testers File not found! %s", file.getAbsolutePath()));
        }
        if (mappings == null) {
            return;
        }
        mappings.forEach(this::assignDeviceToTester);
    }

    private void assignDeviceToTester(TesterDeviceMapping mapping) {
        Device device = devicesRepository.findById(mapping.getDeviceId());
        if (device == null) {
            throw new IllegalStateException(String.format("Unknown device with id %s", mapping.getDeviceId()));
        }
        Tester tester = testersRepository.findById(mapping.getTesterId());
        if (tester == null) {
            throw new IllegalStateException(String.format("Unknown tester with id %s", mapping.getTesterId()));
        }
        tester.getDevices().add(device);
    }
}
