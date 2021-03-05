package com.tester.finder.search.finder;

import com.tester.finder.search.FoundTesters;
import com.tester.finder.search.TesterSearchCriteria;
import com.tester.finder.search.exception.InvalidSearchCriteriaException;

public interface TestersFinder {
    FoundTesters findTesters(TesterSearchCriteria criteria) throws InvalidSearchCriteriaException;
}
