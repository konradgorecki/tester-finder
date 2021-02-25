package com.tester.finder.core;

import java.util.List;

public interface CountriesRepository {
    List<Country> list();
    void save(Country country);

    List<Country> findByCodes(List<String> countryCodes);
}
