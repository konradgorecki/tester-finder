package com.tester.finder.initialization

import com.tester.finder.core.repository.implementation.InMemoryCountriesRepository
import com.tester.finder.core.repository.implementation.InMemoryDevicesRepository
import com.tester.finder.core.repository.implementation.InMemoryTestersRepository
import spock.lang.Specification

class CsvFileTestersDataInitializerTest extends Specification {

    def "load countries from testers csv file"() {
        given:
        def testersTestFile = getTestFile("testers.csv")

        and:
        def countriesRepository = new InMemoryCountriesRepository()
        def countriesDataInitializer = new CsvFileCountriesDataInitializer(countriesRepository)

        when:
        countriesDataInitializer.initFromFile(testersTestFile)

        then:
        noExceptionThrown()
        countriesRepository.findAll().size() == 2
        countriesRepository.findByCodes(List.of('PL', 'DE')).size() == 2
    }

    def "load testers from csv file"() {
        given:
        def testersTestFile = getTestFile("testers.csv")

        and:
        def testersRepository = new InMemoryTestersRepository()
        def testersDataInitializer = new CsvFileTestersDataInitializer(testersRepository)

        when:
        testersDataInitializer.initFromFile(testersTestFile)

        then:
        noExceptionThrown()
        testersRepository.findAll().size() == 2

        def tester1 = testersRepository.findById(1)
        tester1 != null
        tester1.getFirstName() == 'Franciszek'
        tester1.getLastName() == 'Dolas'
        tester1.getCountry().getCode() == 'PL'

        def tester2 = testersRepository.findById(2)
        tester2 != null
        tester2.getFirstName() == 'Reinhard'
        tester2.getLastName() == 'Loose'
        tester2.getCountry().getCode() == 'DE'
    }

    def "load devices from csv file"() {
        given:
        def devicesTestFile = getTestFile("devices.csv")

        and:
        def devicesRepository = new InMemoryDevicesRepository()
        def devicesDataInitializer = new CsvFileDevicesDataInitializer(devicesRepository)

        when:
        devicesDataInitializer.initFromFile(devicesTestFile)

        then:
        noExceptionThrown()
        devicesRepository.findAll().size() == 2

        def device1 = devicesRepository.findById(1)
        device1 != null
        device1.getDescription() == 'Samsung Galaxy S10'

        def device2 = devicesRepository.findById(2)
        device2 != null
        device2.getDescription() == 'iPhone X'

    }

    def "load testers and their devices from csv files"() {
        given:
        def testersTestFile = getTestFile("testers.csv")
        def devicesTestFile = getTestFile("devices.csv")
        def testerDeviceMatchingFile = getTestFile("tester_device.csv")

        and:
        def countriesRepository = new InMemoryCountriesRepository()
        def testersRepository = new InMemoryTestersRepository()
        def devicesRepository = new InMemoryDevicesRepository()

        def countriesDataInitializer = new CsvFileCountriesDataInitializer(countriesRepository)
        def testersDataInitializer = new CsvFileTestersDataInitializer(testersRepository)
        def devicesDataInitializer = new CsvFileDevicesDataInitializer(devicesRepository)
        def testerDeviceDataInitializer = new CsvFileTesterDeviceDataInitializer(testersRepository, devicesRepository)

        when:
        countriesDataInitializer.initFromFile(testersTestFile)
        testersDataInitializer.initFromFile(testersTestFile)
        devicesDataInitializer.initFromFile(devicesTestFile)
        testerDeviceDataInitializer.initFromFile(testerDeviceMatchingFile)

        then:
        noExceptionThrown()
        //TODO detailed check here
    }

    def "load bugs from csv file"() {

    }


    //TODO consider moving it to some utility class
    private File getTestFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader()

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        }

        //File file = new File(resource.getFile());
        new File(resource.toURI());
    }
}
