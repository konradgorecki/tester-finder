package com.tester.finder.search.finder.implementation;

import com.tester.finder.core.*;
import com.tester.finder.core.repository.BugsRepository;
import com.tester.finder.core.repository.TestersRepository;
import com.tester.finder.search.*;
import com.tester.finder.search.exception.InvalidSearchCriteriaException;
import com.tester.finder.search.finder.SearchFacade;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public class SimpleTestersFinder implements SearchFacade {

    private final TestersRepository testersRepository;
    private final BugsRepository bugsRepository;
    private final SearchCriteriaValidator searchCriteriaValidator;

    @Override
    public FoundTesters findTesters(final TesterSearchCriteria criteria) throws InvalidSearchCriteriaException {
        searchCriteriaValidator.validate(criteria);

        List<FoundTester> foundTesters = getTestersMatchingCriteria(criteria)
                .map(tester -> this.calculateExperience(tester, criteria.getDeviceIds()))
                .sorted(new DescendingExperienceComparator())
                .collect(Collectors.toUnmodifiableList());
        return new FoundTesters(foundTesters);
    }

    private Stream<Tester> getTestersMatchingCriteria(TesterSearchCriteria criteria) {
        boolean matchAllCountries = criteria.getCountryCodes().size() == 1
                && Country.ALL.getCode().equals(criteria.getCountryCodes().get(0));
        boolean matchAllDevices = criteria.getDeviceIds().size() == 1
                && Device.ALL.getId().equals(criteria.getDeviceIds().get(0));

        List<Tester> testers = matchAllDevices ? testersRepository.findAll() : testersRepository.findByDeviceId(criteria.getDeviceIds());
        return testers.stream()
                .filter(matchAllCountries ?
                        tester -> true :
                        tester -> criteria.getCountryCodes().contains(tester.getCountry().getCode()));
    }

    @NonNull
    private FoundTester calculateExperience(Tester tester, List<Integer> deviceIds) {
        boolean matchAllDevices = deviceIds.size() == 1
                && Device.ALL.getId().equals(deviceIds.get(0));

        long experience = bugsRepository.findByTester(tester).stream()
                .filter(matchAllDevices ?
                        bug -> true :
                        bug -> deviceIds.contains(bug.getDevice().getId()))
                .count();
        return new FoundTester(tester, (int) experience);
    }
}
