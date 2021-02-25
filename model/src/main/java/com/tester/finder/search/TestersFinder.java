package com.tester.finder.search;

public interface TestersFinder {
    FoundTesters findTesters(TesterSearchCriteria criteria) throws InvalidSearchCriteriaException;
}
