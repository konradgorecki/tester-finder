package com.tester.finder.initialization

import com.tester.finder.initialization.initializer.implementation.CsvFilesDataInitializer
import com.tester.finder.util.TestFileFactory
import com.tester.finder.util.TestRepositoryFactory
import spock.lang.Specification

class CsvFileTestersDataInitializerTest extends Specification {

    def "load countries from testers csv file"() {
        given:
        def testFiles = TestFileFactory.create()

        and:
        def repositories = TestRepositoryFactory.create()
        def dataInitializer = new CsvFilesDataInitializer(repositories.getCountriesRepository(),
                repositories.getTestersRepository(), repositories.getDevicesRepository(), repositories.getBugsRepository())

        when:
        dataInitializer.initData(testFiles.getTestersTestFile(), testFiles.getDevicesTestFile(), testFiles.getTesterDeviceMatchingFile(), testFiles.getBugsFile())

        then:
        noExceptionThrown()
        repositories.getCountriesRepository().findAll().size() == 2
        2 * repositories.countriesRepository.save(_)
        repositories.getCountriesRepository().findByCodes(List.of('PL', 'DE')).size() == 2
    }

    def "load testers from csv file"() {
        given:
        def testFiles = TestFileFactory.create()

        and:
        def repositories = TestRepositoryFactory.create()
        def dataInitializer = new CsvFilesDataInitializer(repositories.getCountriesRepository(),
                repositories.getTestersRepository(), repositories.getDevicesRepository(), repositories.getBugsRepository())

        when:
        dataInitializer.initData(testFiles.getTestersTestFile(), testFiles.getDevicesTestFile(), testFiles.getTesterDeviceMatchingFile(), testFiles.getBugsFile())

        then:
        noExceptionThrown()
        repositories.getTestersRepository().findAll().size() == 2
        2 * repositories.testersRepository.save(_)

        def tester1 = repositories.getTestersRepository().findById(1)
        tester1 != null
        tester1.getFirstName() == 'Franciszek'
        tester1.getLastName() == 'Dolas'
        tester1.getCountry().getCode() == 'PL'

        def tester2 = repositories.getTestersRepository().findById(2)
        tester2 != null
        tester2.getFirstName() == 'Reinhard'
        tester2.getLastName() == 'Loose'
        tester2.getCountry().getCode() == 'DE'
    }

    def "load devices from csv file"() {
        given:
        def testFiles = TestFileFactory.create()

        and:
        def repositories = TestRepositoryFactory.create()
        def dataInitializer = new CsvFilesDataInitializer(repositories.getCountriesRepository(),
                repositories.getTestersRepository(), repositories.getDevicesRepository(), repositories.getBugsRepository())

        when:
        dataInitializer.initData(testFiles.getTestersTestFile(), testFiles.getDevicesTestFile(), testFiles.getTesterDeviceMatchingFile(), testFiles.getBugsFile())

        then:
        noExceptionThrown()
        2 * repositories.devicesRepository.save(_)
        repositories.getDevicesRepository().findAll().size() == 2

        def device1 = repositories.getDevicesRepository().findById(1)
        device1 != null
        device1.getDescription() == 'Samsung Galaxy S10'

        def device2 = repositories.getDevicesRepository().findById(2)
        device2 != null
        device2.getDescription() == 'iPhone X'

    }

    def "load testers and their devices from csv files"() {
        given:
        def testFiles = TestFileFactory.create()

        and:
        def repositories = TestRepositoryFactory.create()
        def dataInitializer = new CsvFilesDataInitializer(repositories.getCountriesRepository(),
                repositories.getTestersRepository(), repositories.getDevicesRepository(), repositories.getBugsRepository())

        when:
        dataInitializer.initData(testFiles.getTestersTestFile(), testFiles.getDevicesTestFile(), testFiles.getTesterDeviceMatchingFile(), testFiles.getBugsFile())

        then:
        noExceptionThrown()
        def tester1 = repositories.getTestersRepository().findById(1)
        tester1.getDevices().size() == 2
        tester1.getDevices().any { device -> device.getId() == 1 }
        tester1.getDevices().any { device -> device.getId() == 2 }

        def tester2 = repositories.getTestersRepository().findById(2)
        tester2.getDevices().size() == 1
        tester2.getDevices().any { device -> device.getId() == 1 }
        !tester2.getDevices().any { device -> device.getId() == 2 }
    }

    def "load bugs from csv file"() {
        given:
        def testFiles = TestFileFactory.create()

        and:
        def repositories = TestRepositoryFactory.create()
        def dataInitializer = new CsvFilesDataInitializer(repositories.getCountriesRepository(),
                repositories.getTestersRepository(), repositories.getDevicesRepository(), repositories.getBugsRepository())

        when:
        dataInitializer.initData(testFiles.getTestersTestFile(), testFiles.getDevicesTestFile(), testFiles.getTesterDeviceMatchingFile(), testFiles.getBugsFile())

        then:
        10 * repositories.bugsRepository.save(_)
    }

}
