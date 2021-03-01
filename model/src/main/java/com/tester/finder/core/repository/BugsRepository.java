package com.tester.finder.core.repository;

import com.tester.finder.core.Bug;
import com.tester.finder.core.Tester;

import java.util.Collection;

public interface BugsRepository {
    void save(Bug bug);
    Collection<Bug> findByTester(Tester tester);
}
