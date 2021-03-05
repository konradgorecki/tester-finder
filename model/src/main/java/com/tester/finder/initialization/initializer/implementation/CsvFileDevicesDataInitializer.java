package com.tester.finder.initialization.initializer.implementation;

import com.opencsv.bean.CsvToBeanBuilder;
import com.tester.finder.core.repository.DevicesRepository;
import com.tester.finder.initialization.Device;
import com.tester.finder.initialization.initializer.DevicesDataInitializer;
import lombok.AllArgsConstructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static com.tester.finder.initialization.initializer.implementation.CsvBeanFilters.SKIP_EMPTY_LINES;

@AllArgsConstructor
public class CsvFileDevicesDataInitializer implements DevicesDataInitializer {

    private final DevicesRepository devicesRepository;

    @Override
    public void initFromFile(File file) {
        List<Device> devices;
        try {
            devices = new CsvToBeanBuilder<Device>(new FileReader(file))
                    .withType(Device.class)
                    .withFilter(SKIP_EMPTY_LINES).build().parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException(String.format("Devices File not found! %s", file.getAbsolutePath()));
        }
        if (devices == null) {
            return;
        }
        devices.stream()
                .map(this::convertDevice)
                .forEach(devicesRepository::save);
    }

    private com.tester.finder.core.Device convertDevice(Device device) {
        return new com.tester.finder.core.Device(device.getDeviceId(), device.getDescription());
    }
}
