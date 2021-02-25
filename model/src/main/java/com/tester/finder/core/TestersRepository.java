package com.tester.finder.core;

import java.util.List;

public interface TestersRepository {
    List<Tester> list();
    void save(Tester tester);

    List<Tester> findByDevices(List<Device> devices);
}
