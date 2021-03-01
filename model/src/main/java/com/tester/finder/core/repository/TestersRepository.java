package com.tester.finder.core.repository;

import com.tester.finder.core.Tester;

import java.util.List;

public interface TestersRepository {
    void save(Tester tester);
    List<Tester> findByDeviceId(List<Integer> deviceIds);
    List<Tester> findAll();
}
