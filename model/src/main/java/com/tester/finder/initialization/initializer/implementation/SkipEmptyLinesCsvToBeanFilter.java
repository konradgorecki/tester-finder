package com.tester.finder.initialization.initializer.implementation;

import com.opencsv.bean.CsvToBeanFilter;

/**
 * {@link CsvToBeanFilter} filter for skipping empty lines in the input CSV file.
 */
public class SkipEmptyLinesCsvToBeanFilter implements CsvToBeanFilter {

    @Override
    public boolean allowLine(String[] line) {
        for (String item : line) {
            if (item != null && item.length() > 0) {
                return true;
            }
        }
        return false;
    }
}
