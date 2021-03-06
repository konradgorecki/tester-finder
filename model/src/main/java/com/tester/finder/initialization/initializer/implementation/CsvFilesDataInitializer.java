package com.tester.finder.initialization.initializer.implementation;

import com.tester.finder.core.repository.BugsRepository;
import com.tester.finder.core.repository.CountriesRepository;
import com.tester.finder.core.repository.DevicesRepository;
import com.tester.finder.core.repository.TestersRepository;
import com.tester.finder.initialization.initializer.*;

import java.io.File;

public class CsvFilesDataInitializer implements DataInitializationFacade {

    private final TestersDataInitializer testersDataInitializer;
    private final DevicesDataInitializer devicesDataInitializer;
    private final TesterDeviceDataInitializer testerDeviceDataInitializer;
    private final BugsDataInitializer bugsDataInitializer;

    public CsvFilesDataInitializer(CountriesRepository countriesRepository, TestersRepository testersRepository, DevicesRepository devicesRepository, BugsRepository bugsRepository) {
        this.testersDataInitializer = new CsvFileTestersDataInitializer(testersRepository, countriesRepository);
        this.devicesDataInitializer = new CsvFileDevicesDataInitializer(devicesRepository);
        this.testerDeviceDataInitializer = new CsvFileTesterDeviceDataInitializer(testersRepository, devicesRepository);
        this.bugsDataInitializer = new CsvFileBugsDataInitializer(bugsRepository, testersRepository, devicesRepository);
    }

    @Override
    public void initData(File testersFile, File devicesFile, File testerDevicesFile, File bugsFile) {
        testersDataInitializer.initFromFile(testersFile);
        devicesDataInitializer.initFromFile(devicesFile);
        testerDeviceDataInitializer.initFromFile(testerDevicesFile);
        bugsDataInitializer.initFromFile(bugsFile);
    }
}
