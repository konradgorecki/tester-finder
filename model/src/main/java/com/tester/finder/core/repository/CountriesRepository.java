package com.tester.finder.core.repository;

import com.tester.finder.core.Country;

import java.util.List;

public interface CountriesRepository {
    List<Country> findAll();
    void save(Country country);
    List<Country> findByCodes(List<String> countryCodes);
}
