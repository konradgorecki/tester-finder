package com.tester.finder.search

import com.tester.finder.core.Bug
import com.tester.finder.core.Device
import com.tester.finder.core.Tester
import com.tester.finder.search.util.BugsFactory
import com.tester.finder.search.util.DevicesFactory
import com.tester.finder.search.util.TesterFinderFactory
import com.tester.finder.search.util.TestersFactory
import spock.lang.Specification

class TestersFinderInputDataConstraintsTest extends Specification {

    def 'missing search criteria'() {
        given:
        Device device = DevicesFactory.create(2)
        Tester tester = TestersFactory.create(2, 'PL', device)
        List<Bug> bugs = BugsFactory.create(tester, device, 2)

        TestersFinder testersFinder = TesterFinderFactory.prepareFinder(List.of(tester), List.of(device), bugs)

        and:
        TesterSearchCriteria searchCriteria = null

        when:
        testersFinder.findTesters(searchCriteria)

        then:
        thrown RuntimeException//TODO consider replacing it with business runtime exception
    }

    def 'missing country code in search criteria'() {
        given:
        Device device = DevicesFactory.create(2)
        Tester tester = TestersFactory.create(2, 'PL', device)
        List<Bug> bugs = BugsFactory.create(tester, device, 2)

        TestersFinder testersFinder = TesterFinderFactory.prepareFinder(List.of(tester), List.of(device), bugs)

        and:
        TesterSearchCriteria searchCriteria = new TesterSearchCriteria(List.of(), List.of(device.getId()))

        when:
        testersFinder.findTesters(searchCriteria)

        then:
        thrown RuntimeException//TODO consider replacing it with business runtime exception
    }

    def 'missing country code list in search criteria'() {
        given:
        Device device = DevicesFactory.create(2)
        Tester tester = TestersFactory.create(2, 'PL', device)
        List<Bug> bugs = BugsFactory.create(tester, device, 2)

        TestersFinder testersFinder = TesterFinderFactory.prepareFinder(List.of(tester), List.of(device), bugs)

        and:
        TesterSearchCriteria searchCriteria = new TesterSearchCriteria(null, List.of(device.getId()))

        when:
        testersFinder.findTesters(searchCriteria)

        then:
        thrown RuntimeException//TODO consider replacing it with business runtime exception
    }

    def 'missing device in search criteria'() {
        given:
        Device device = DevicesFactory.create(2)
        Tester tester = TestersFactory.create(2, 'PL', device)
        List<Bug> bugs = BugsFactory.create(tester, device, 2)

        TestersFinder testersFinder = TesterFinderFactory.prepareFinder(List.of(tester), List.of(device), bugs)

        and:
        TesterSearchCriteria searchCriteria = new TesterSearchCriteria(List.of('EN'), List.of())

        when:
        testersFinder.findTesters(searchCriteria)

        then:
        thrown RuntimeException//TODO consider replacing it with business runtime exception
    }

    def 'missing device list in search criteria'() {
        given:
        Device device = DevicesFactory.create(2)
        Tester tester = TestersFactory.create(2, 'PL', device)
        List<Bug> bugs = BugsFactory.create(tester, device, 2)

        TestersFinder testersFinder = TesterFinderFactory.prepareFinder(List.of(tester), List.of(device), bugs)

        and:
        TesterSearchCriteria searchCriteria = new TesterSearchCriteria(List.of('EN'), null)

        when:
        testersFinder.findTesters(searchCriteria)

        then:
        thrown RuntimeException//TODO consider replacing it with business runtime exception
    }

    def 'unknown device in search criteria'() {
        given:
        Integer unknownDeviceId = 111
        Device device = DevicesFactory.create(2)
        Tester tester = TestersFactory.create(2, 'PL', device)
        List<Bug> bugs = BugsFactory.create(tester, device, 2)

        TestersFinder testersFinder = TesterFinderFactory.prepareFinder(List.of(tester), List.of(device), bugs)

        and:
        TesterSearchCriteria searchCriteria = new TesterSearchCriteria(List.of('PL'), List.of(unknownDeviceId))

        when:
        testersFinder.findTesters(searchCriteria)

        then:
        thrown RuntimeException//TODO consider replacing it with business runtime exception
    }

    def 'unknown country code in search criteria'() {
        given:
        String unknownCountryCode = 'UNKNOWN'
        Device device = DevicesFactory.create(2)
        Tester tester = TestersFactory.create(2, 'PL', device)
        List<Bug> bugs = BugsFactory.create(tester, device, 2)

        TestersFinder testersFinder = TesterFinderFactory.prepareFinder(List.of(tester), List.of(device), bugs)

        and:
        TesterSearchCriteria searchCriteria = new TesterSearchCriteria(List.of(unknownCountryCode), List.of(device.getId()))

        when:
        testersFinder.findTesters(searchCriteria)

        then:
        thrown RuntimeException//TODO consider replacing it with business runtime exception
    }

}
