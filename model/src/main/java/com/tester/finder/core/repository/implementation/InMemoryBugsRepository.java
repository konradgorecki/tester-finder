package com.tester.finder.core.repository.implementation;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.tester.finder.core.Bug;
import com.tester.finder.core.Tester;
import com.tester.finder.core.repository.BugsRepository;

import java.util.*;

public class InMemoryBugsRepository implements BugsRepository {

    private final Multimap<Tester, Bug> bugs = LinkedHashMultimap.create();
    private final Set<Integer> ids = new HashSet<>();

    @Override
    public void save(Bug bug) {
        if (ids.contains(bug.getBugId())) {
            throw new IllegalArgumentException(String.format("Bug with the id %s already exists.", bug.getBugId()));
        }
        ids.add(bug.getBugId());
        bugs.put(bug.getTester(), bug);
    }

    @Override
    public Collection<Bug> findByTester(Tester tester) {
        return bugs.get(tester);
    }

    @Override
    public List<Bug> findAll() {
        return new ArrayList<>(bugs.values());
    }

}
