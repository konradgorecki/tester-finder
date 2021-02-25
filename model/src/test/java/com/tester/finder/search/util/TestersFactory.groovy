package com.tester.finder.search.util

import com.tester.finder.core.Country
import com.tester.finder.core.Device
import com.tester.finder.core.Tester

import java.time.LocalDateTime

class TestersFactory {

    static Tester create(Integer id, String countryCode, Device device) {
        create(id, countryCode, Collections.singletonList(device))
    }

    static Tester create(Integer id, String countryCode, List<Device> devices) {
        Tester.builder()
                .country(new Country(countryCode, countryCode))
                .devices(devices)
                .firstName(String.valueOf(id))
                .lastName(String.valueOf(id))
                .lastLogin(LocalDateTime.now())
                .build()
    }
}
