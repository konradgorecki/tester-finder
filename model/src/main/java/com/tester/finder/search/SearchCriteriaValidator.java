package com.tester.finder.search;

import com.google.common.base.Joiner;
import com.tester.finder.core.repository.CountriesRepository;
import com.tester.finder.core.Country;
import com.tester.finder.core.Device;
import com.tester.finder.core.repository.DevicesRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class SearchCriteriaValidator {

    private final CountriesRepository countriesRepository;
    private final DevicesRepository devicesRepository;

    public void validate(TesterSearchCriteria criteria) throws InvalidSearchCriteriaException {
        if (null == criteria) {
            throw new NullPointerException("Missing TesterSearchCriteria");
        }
        if (null == criteria.getCountryCodes()) {
            throw new NullPointerException("Missing CountryCodes");
        }
        if (null == criteria.getDeviceIds()) {
            throw new NullPointerException("Missing DeviceIds");
        }
        if (criteria.getCountryCodes().isEmpty()) {
            throw new InvalidSearchCriteriaException("No Country Codes specified in search criteria");
        }
        if (criteria.getDeviceIds().isEmpty()) {
            throw new InvalidSearchCriteriaException("No Devices specified in search criteria");
        }
        validateDevices(criteria.getDeviceIds());
        validateCountryCodes(criteria.getCountryCodes());
    }

    private void validateDevices(List<Integer> deviceIds) {
        if (deviceIds.contains(Device.ALL.getId()) && deviceIds.size() > 1) {
            throw new InvalidSearchCriteriaException("Specified specific devices despite ALL flag: " + Joiner.on(", ").join(deviceIds));
        }
        List<Device> devices = devicesRepository.findByIds(deviceIds);
        if (devices.size() < deviceIds.size()) {
            throw new InvalidSearchCriteriaException("Unknown devices found in search criteria" + Joiner.on(", ").join(deviceIds));
        }
        if (devices.size() > deviceIds.size()) {
            throw new IllegalStateException("Illegal devices repository state for device list query: " + Joiner.on(", ").join(deviceIds));
        }
    }

    private void validateCountryCodes(List<String> countryCodes) {
        if (countryCodes.contains(Country.ALL.getCode()) && countryCodes.size() > 1) {
            throw new InvalidSearchCriteriaException("Specified specific countries despite ALL flag: " + Joiner.on(", ").join(countryCodes));
        }
        List<Country> countries = countriesRepository.findByCodes(countryCodes);
        if (countries.size() < countryCodes.size()) {
            throw new InvalidSearchCriteriaException("Unknown country codes found in search criteria" + Joiner.on(", ").join(countryCodes));
        }
        if (countries.size() > countryCodes.size()) {
            throw new IllegalStateException("Illegal countries repository state for country code list query: " + Joiner.on(", ").join(countryCodes));
        }
    }

}
