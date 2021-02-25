package com.tester.finder.search;

import com.tester.finder.core.*;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SimpleTestersFinder implements TestersFinder {

    private final TestersRepository testersRepository;
    private final DevicesRepository devicesRepository;
    private final BugsRepository bugsRepository;

    @Override
    public FoundTesters findTesters(TesterSearchCriteria criteria) {
        List<Device> devices = devicesRepository.findByIds(criteria.getDeviceIds());
        List<Tester> testers = testersRepository.findByDevices(devices);

        List<Tester> testersMatchingCriteria = testers;
        if (criteria.getCountryCodes().size() == 1 && criteria.getCountryCodes().get(0).equals(Country.ALL.getCode()))
            testersMatchingCriteria = testers.stream()
                    .filter(tester -> criteria.getCountryCodes().contains(tester.getCountry().getCode()))
                    .collect(Collectors.toList());
//TODO implement
        return null;
    }
}
