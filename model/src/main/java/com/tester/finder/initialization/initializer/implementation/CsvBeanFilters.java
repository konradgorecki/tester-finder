package com.tester.finder.initialization.initializer.implementation;

import com.opencsv.bean.CsvToBeanFilter;

public class CsvBeanFilters {

    /**
     * {@link CsvToBeanFilter} filter for skipping empty lines in the input CSV file.
     */
    public static CsvToBeanFilter SKIP_EMPTY_LINES = line -> {
        for (String item : line) {
            if (item != null && item.length() > 0) {
                return true;
            }
        }
        return false;
    };
}
