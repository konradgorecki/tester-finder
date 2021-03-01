package com.tester.finder.search

import com.tester.finder.core.Bug
import com.tester.finder.core.Country
import com.tester.finder.core.Device
import com.tester.finder.core.Tester
import com.tester.finder.search.util.BugsFactory
import com.tester.finder.search.util.DevicesFactory
import com.tester.finder.search.util.TesterFinderFactory
import com.tester.finder.search.util.TestersFactory
import spock.lang.Specification

import java.util.stream.Collectors
import java.util.stream.Stream

class TestersFinderSearchLogicTest extends Specification {

    def 'find a tester in a set of total 1 testers available - matching by device'() {
        given:
        Device device = DevicesFactory.create(2)
        Tester tester = TestersFactory.create(2, 'PL', device)
        List<Bug> bugs = BugsFactory.create(tester, device, 2)

        TestersFinder testersFinder = TesterFinderFactory.prepareFinder(List.of(tester), List.of(device), bugs)

        and:
        TesterSearchCriteria searchCriteria = new TesterSearchCriteria(List.of(Country.ALL.code), List.of(device.getId()))

        when:
        FoundTesters foundTesters = testersFinder.findTesters(searchCriteria)

        then:
        foundTesters.getTesters().size() == 1
    }

    def 'find a tester in a set of total 1 testers available - matching by country and device'() {
        given:
        Device device = DevicesFactory.create(3)
        Tester tester = TestersFactory.create(4, 'US', device)
        List<Bug> bugs = BugsFactory.create(tester, device, 2)

        TestersFinder testersFinder = TesterFinderFactory.prepareFinder(List.of(tester), List.of(device), bugs)

        and:
        TesterSearchCriteria searchCriteria = new TesterSearchCriteria(List.of(tester.getCountry().getCode()), List.of(device.getId()))

        when:
        FoundTesters foundTesters = testersFinder.findTesters(searchCriteria)

        then:
        foundTesters.getTesters().size() == 1
    }

    def 'no testers found when there are no testers meeting the device criterion'() {
        given:
        Integer deviceId = 3
        Integer searchedDeviceId = 5

        Device device = DevicesFactory.create(deviceId)
        Device searchedDevice = DevicesFactory.create(searchedDeviceId)
        Tester tester = TestersFactory.create(5, 'DE', device)
        List<Bug> bugs = BugsFactory.create(tester, device, 2)

        TestersFinder testersFinder = TesterFinderFactory.prepareFinder(List.of(tester), List.of(device, searchedDevice), bugs)

        and:
        TesterSearchCriteria searchCriteria = new TesterSearchCriteria(List.of(Country.ALL.code), List.of(searchedDeviceId))

        when:
        FoundTesters foundTesters = testersFinder.findTesters(searchCriteria)

        then:
        foundTesters.getTesters().size() == 0
    }

    def 'no testers found when there are no testers meeting the country criterion'() {
        given:
        Country testerCountry = new Country('LT')
        Country searchedCountry = new Country('US')

        Device device = DevicesFactory.create(4)
        Tester tester = TestersFactory.create(3, testerCountry, List.of(device))
        List<Bug> bugs = BugsFactory.create(tester, device, 5)

        TestersFinder testersFinder = TesterFinderFactory.prepareFinder(List.of(tester), List.of(device), bugs, List.of(testerCountry, searchedCountry))

        and:
        TesterSearchCriteria searchCriteria = new TesterSearchCriteria(List.of(searchedCountry.getCode()), List.of(device.getId()))

        when:
        FoundTesters foundTesters = testersFinder.findTesters(searchCriteria)

        then:
        foundTesters.getTesters().size() == 0
    }

    def 'find 2 testers in a set of 2 testers - matching by device'() {
        given:
        Device device = DevicesFactory.create(1)
        Tester tester1 = TestersFactory.create(3, 'US', device)
        Tester tester2 = TestersFactory.create(4, 'AA', device)
        List<Bug> bugs = BugsFactory.create(List.of(tester1, tester2), device, 2)

        TestersFinder testersFinder = TesterFinderFactory.prepareFinder(List.of(tester1, tester2), List.of(device), bugs)

        and:
        TesterSearchCriteria searchCriteria = new TesterSearchCriteria(List.of(Country.ALL.getCode()), List.of(device.getId()))

        when:
        FoundTesters foundTesters = testersFinder.findTesters(searchCriteria)

        then:
        foundTesters.getTesters().size() == 2
    }

    def 'find 2 testers in a set of 2 testers - matching by country and device'() {
        given:
        String tester1CountryCode = 'US'
        String tester2CountryCode = 'IT'

        Device device = DevicesFactory.create(1)
        Tester tester1 = TestersFactory.create(3, tester1CountryCode, device)
        Tester tester2 = TestersFactory.create(4, tester2CountryCode, device)
        List<Bug> bugs = BugsFactory.create(List.of(tester1, tester2), device, 2)

        TestersFinder testersFinder = TesterFinderFactory.prepareFinder(List.of(tester1, tester2), List.of(device), bugs)

        and:
        TesterSearchCriteria searchCriteria = new TesterSearchCriteria(List.of(tester1CountryCode, tester2CountryCode), List.of(device.getId()))

        when:
        FoundTesters foundTesters = testersFinder.findTesters(searchCriteria)

        then:
        foundTesters.getTesters().size() == 2
    }

    def 'find 2 testers in a set of 4 testers - matching by device'() {
        given:
        Device device = DevicesFactory.create(3)
        Device device2 = DevicesFactory.create(5)
        Tester tester1 = TestersFactory.create(1, 'GB', device2)
        Tester tester2 = TestersFactory.create(2, 'GB', device)
        Tester tester3 = TestersFactory.create(3, 'GB', device2)
        Tester tester4 = TestersFactory.create(4, 'GB', device)
        List<Bug> bugs = BugsFactory.create(List.of(tester1, tester2, tester3, tester4), device, 6)

        TestersFinder testersFinder = TesterFinderFactory.prepareFinder(List.of(tester1, tester2, tester3, tester4), List.of(device, device2), bugs)

        and:
        TesterSearchCriteria searchCriteria = new TesterSearchCriteria(List.of('GB'), List.of(device.getId()))

        when:
        FoundTesters foundTesters = testersFinder.findTesters(searchCriteria)

        then:
        foundTesters.getTesters().size() == 2
    }

    def 'find 2 testers in a set of 4 testers - matching by country and device'() {
        given:
        String testersCountryCode1 = 'GB'
        String testersCountryCode2 = 'PL'
        String testersCountryCode3 = 'ES'

        Device device = DevicesFactory.create(18)
        Device device2 = DevicesFactory.create(21)
        Tester tester1 = TestersFactory.create(3, testersCountryCode1, device2)
        Tester tester2 = TestersFactory.create(4, testersCountryCode2, device)
        Tester tester3 = TestersFactory.create(5, testersCountryCode3, device2)
        Tester tester4 = TestersFactory.create(7, testersCountryCode1, device)
        List<Bug> bugs = BugsFactory.create(List.of(tester1, tester2, tester3, tester4), device, 7)

        TestersFinder testersFinder = TesterFinderFactory.prepareFinder(List.of(tester1, tester2, tester3, tester4), List.of(device, device2), bugs)

        and:
        TesterSearchCriteria searchCriteria = new TesterSearchCriteria(List.of(testersCountryCode2, testersCountryCode3), List.of(device.getId(), device2.getId()))

        when:
        FoundTesters foundTesters = testersFinder.findTesters(searchCriteria)

        then:
        foundTesters.getTesters().size() == 2
    }

    def 'find 2 testers in a set of 5 and return them in the correct experience order - matching by device'() {
        given:
        Integer bigExperience = 7
        Integer smallExperience = 1
        Device device = DevicesFactory.create(18)
        Device device2 = DevicesFactory.create(21)

        Tester tester1 = TestersFactory.create(3, 'DE', device2)
        Tester tester2 = TestersFactory.create(4, 'DE', device)
        Tester tester3 = TestersFactory.create(5, 'DE', device2)
        Tester tester4 = TestersFactory.create(7, 'DE', device)
        Tester tester5 = TestersFactory.create(9, 'DE', device)

        List<Bug> bugs1 = BugsFactory.create(List.of(tester1, tester2), device, smallExperience)
        List<Bug> bugs2 = BugsFactory.create(List.of(tester3, tester4, tester5), device, bigExperience)
        List<Bug> bugs = Stream.concat(bugs1.stream(), bugs2.stream()).collect(Collectors.toList())

        TestersFinder testersFinder = TesterFinderFactory.prepareFinder(List.of(tester1, tester2, tester3, tester4, tester5), List.of(device, device2), bugs)

        and:
        TesterSearchCriteria searchCriteria = new TesterSearchCriteria(List.of(Country.ALL.getCode()), List.of(device2.getId()))

        when:
        FoundTesters foundTesters = testersFinder.findTesters(searchCriteria)

        then:
        foundTesters.getTesters().size() == 2
        foundTesters.getTesters().get(0).getTester().getId() == tester3.getId()
        foundTesters.getTesters().get(1).getTester().getId() == tester1.getId()
        foundTesters.getTesters().get(0).getExperience() == bigExperience
        foundTesters.getTesters().get(1).getExperience() == smallExperience
    }

    def 'find testers - matching by country only' () {
        given:
        String searchedCountryCode = 'DE'
        String otherCountryCode = 'US'
        Integer searchedTesterId = 3
        Integer otherTesterId = 4

        Device device = DevicesFactory.create(1)
        Tester tester1 = TestersFactory.create(searchedTesterId, searchedCountryCode, device)
        Tester tester2 = TestersFactory.create(otherTesterId, otherCountryCode, device)
        List<Bug> bugs = BugsFactory.create(List.of(tester1, tester2), device, 2)

        TestersFinder testersFinder = TesterFinderFactory.prepareFinder(List.of(tester1, tester2), List.of(device), bugs)

        and:
        TesterSearchCriteria searchCriteria = new TesterSearchCriteria(List.of(searchedCountryCode), List.of(device.getId()))

        when:
        FoundTesters foundTesters = testersFinder.findTesters(searchCriteria)

        then:
        foundTesters.getTesters().size() == 1
        foundTesters.getTesters().get(0).getTester().getId() == searchedTesterId
    }

    def 'find testers - matching by wildcards only, some might not have any experience' () {
        given:
        Device device = DevicesFactory.create(1)
        Device device2 = DevicesFactory.create(2)
        Tester tester1 = TestersFactory.create(1, 'PL', device)
        Tester tester2 = TestersFactory.create(2, 'ES', device2)
        Tester tester3 = TestersFactory.create(3, 'FR', device2)
        List<Bug> bugs = BugsFactory.create(List.of(tester1, tester2), device, 2)

        TestersFinder testersFinder = TesterFinderFactory.prepareFinder(List.of(tester1, tester2, tester3), List.of(device, device2), bugs)

        and:
        TesterSearchCriteria searchCriteria = new TesterSearchCriteria(List.of(Country.ALL.getCode()), List.of(Device.ALL.getId()))

        when:
        FoundTesters foundTesters = testersFinder.findTesters(searchCriteria)

        then:
        foundTesters.getTesters().size() == 3
    }

    def 'searching and sorting testers with mixed experience - experience with multiple devices' () {
        given:
        Integer coupleOfBugsFixed = 2
        Integer lotsOfBugsFixed = 5
        Device device = DevicesFactory.create(1)
        Device device2 = DevicesFactory.create(2)
        Tester tester1 = TestersFactory.create(1,                                                                                                         'PL', device)
        Tester tester2 = TestersFactory.create(2, 'ES', device2)
        Tester tester3 = TestersFactory.create(3, 'FR', List.of(device, device2))
        List<Bug> bugs1 = BugsFactory.create(List.of(tester1, tester2), device, coupleOfBugsFixed)
        List<Bug> bugs2 = BugsFactory.create(List.of(tester2, tester3), device2, lotsOfBugsFixed)
        List<Bug> bugs = Stream.concat(bugs1.stream(), bugs2.stream()).collect(Collectors.toList())

        TestersFinder testersFinder = TesterFinderFactory.prepareFinder(List.of(tester1, tester2, tester3), List.of(device, device2), bugs)

        and:
        TesterSearchCriteria searchCriteria = new TesterSearchCriteria(List.of(Country.ALL.getCode()), List.of(Device.ALL.getId()))

        when:
        FoundTesters foundTesters = testersFinder.findTesters(searchCriteria)

        then:
        foundTesters.getTesters().size() == 3

        foundTesters.getTesters().get(0).getTester().getId() == tester2.getId()
        foundTesters.getTesters().get(0).getExperience() == coupleOfBugsFixed + lotsOfBugsFixed

        foundTesters.getTesters().get(1).getTester().getId() == tester3.getId()
        foundTesters.getTesters().get(1).getExperience() == lotsOfBugsFixed

        foundTesters.getTesters().get(2).getTester().getId() == tester1.getId()
        foundTesters.getTesters().get(2).getExperience() == coupleOfBugsFixed
    }

}
