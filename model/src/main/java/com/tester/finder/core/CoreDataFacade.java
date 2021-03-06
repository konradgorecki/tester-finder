package com.tester.finder.core;

import com.tester.finder.core.repository.CountriesRepository;
import com.tester.finder.core.repository.DevicesRepository;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class CoreDataFacade {

    private final CountriesRepository countriesRepository;
    private final DevicesRepository devicesRepository;

    public List<Country> findCountries(boolean includeAllCountriesMarker) {
        List<Country> countries = countriesRepository.findAll();
        List<Country> result = new ArrayList<>();
        if (includeAllCountriesMarker) {
            result.add(Country.ALL);
        }
        result.addAll(countries);
        return result;
    }

    public List<Device> findDevices(boolean includeAllDevicesMarker) {
        List<Device> devices = devicesRepository.findAll();
        List<Device> result = new ArrayList<>();
        if (includeAllDevicesMarker) {
            result.add(Device.ALL);
        }
        result.addAll(devices);
        return result;
    }

}
