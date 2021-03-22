package com.tester.finder.search.repository.implementation;

import com.tester.finder.search.TesterSearchCriteria;
import com.tester.finder.search.repository.SearchCriteriaRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InMemorySearchCriteriaRepository implements SearchCriteriaRepository {

    private final Map<UUID, TesterSearchCriteria> criteria = new HashMap<>();

    @Override
    public UUID save(TesterSearchCriteria newCriteria) {
        if (newCriteria == null) {
            throw new IllegalArgumentException("Missing search criteria.");
        }
        UUID id = UUID.randomUUID();
        criteria.put(id, newCriteria);
        return id;
    }

    @Override
    public TesterSearchCriteria get(UUID searchId) {
        if (!criteria.containsKey(searchId)) {
            throw new IllegalArgumentException(String.format("Search criteria with id: %s doeas not exist.", searchId));
        }
        return criteria.get(searchId);
    }
}
