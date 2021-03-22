package com.tester.finder.search.repository;

import com.tester.finder.search.TesterSearchCriteria;

import java.util.UUID;

public interface SearchCriteriaRepository {
    UUID save(TesterSearchCriteria criteria);
    TesterSearchCriteria get(UUID searchId);
}
