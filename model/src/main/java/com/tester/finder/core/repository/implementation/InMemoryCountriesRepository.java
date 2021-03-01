package com.tester.finder.core.repository.implementation;

import com.tester.finder.core.Country;
import com.tester.finder.core.repository.CountriesRepository;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryCountriesRepository implements CountriesRepository {

    private final Map<String, Country> countries = new HashMap<>();

    @Override
    public List<Country> findAll() {
        return new ArrayList<>(countries.values());
    }

    @Override
    public void save(Country country) {
        if (countries.containsKey(country.getCode())) {
            throw new IllegalArgumentException(String.format("Country with code %s already exists.", country.getCode()));
        }
        countries.put(country.getCode(), country);
    }

    @Override
    public List<Country> findByCodes(List<String> countryCodes) {
        return countryCodes.stream()
                .map(countries::get)
                .filter(Objects::nonNull) // Map::get might return null in case of missing object
                .collect(Collectors.toList());
    }
}
