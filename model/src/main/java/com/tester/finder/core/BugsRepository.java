package com.tester.finder.core;

import java.util.List;

public interface BugsRepository {
    List<Bug> get(Tester tester);
    List<Bug> get(Device device);
    void save(Bug bug);
}
