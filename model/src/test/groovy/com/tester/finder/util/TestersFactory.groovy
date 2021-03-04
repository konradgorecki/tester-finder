package com.tester.finder.util

import com.tester.finder.core.Country
import com.tester.finder.core.Device
import com.tester.finder.core.Tester

import java.time.LocalDateTime

class TestersFactory {

    static Tester create(Integer id, String countryCode, Device device) {
        create(id, countryCode, Collections.singletonList(device))
    }

    static Tester create(Integer id, String countryCode, List<Device> devices) {
        create(id, new Country(countryCode), devices)
    }

    static Tester create(Integer id, Country country, List<Device> devices) {
        Tester.builder()
                .id(id)
                .country(country)
                .devices(devices)
                .firstName(String.valueOf(id))
                .lastName(String.valueOf(id))
                .lastLogin(LocalDateTime.now())
                .build()
    }
}
