package com.tester.finder.search;

import com.tester.finder.core.CountriesRepository;
import com.tester.finder.core.Country;
import com.tester.finder.core.Device;
import com.tester.finder.core.DevicesRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

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
        //TODO add special validation for option ALL
        List<Device> devices = devicesRepository.findByIds(deviceIds);
        if (devices.size() < deviceIds.size()) {
            throw new InvalidSearchCriteriaException("Unknown devices found in search criteria" + concatToString(deviceIds));
        }
        if (devices.size() > deviceIds.size()) {
            throw new IllegalStateException("Illegal devices repository state for device list query: " + concatToString(deviceIds));
        }
    }


    private void validateCountryCodes(List<String> countryCodes) {
        //TODO add special validation for option ALL
        List<Country> countries = countriesRepository.findByCodes(countryCodes);
        if (countries.size() < countryCodes.size()) {
            throw new InvalidSearchCriteriaException("Unknown country codes found in search criteria" + concatToString(countryCodes));
        }
        if (countries.size() > countryCodes.size()) {
            throw new IllegalStateException("Illegal countries repository state for country code list query: " + concatToString(countryCodes));
        }
    }

    private String concatToString(List<?> list) {
        return list.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }
}
