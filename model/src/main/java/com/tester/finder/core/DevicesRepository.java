package com.tester.finder.core;

import java.util.List;

public interface DevicesRepository {
    List<Device> list();
    void save(Device device);

    List<Device> findByIds(List<Integer> deviceIds);
}
