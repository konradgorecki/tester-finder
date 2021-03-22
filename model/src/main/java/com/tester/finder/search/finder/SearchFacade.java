package com.tester.finder.search.finder;

import com.tester.finder.search.FoundTesters;
import com.tester.finder.search.TesterSearchCriteria;
import com.tester.finder.search.exception.InvalidSearchCriteriaException;

import java.util.UUID;

public interface SearchFacade {
    FoundTesters findTesters(TesterSearchCriteria criteria) throws InvalidSearchCriteriaException;

    UUID createSearch(TesterSearchCriteria criteria);

    FoundTesters executeSearch(UUID searchId);
}
