package com.tester.finder.core.repository;

import com.tester.finder.core.Device;

import java.util.List;

public interface DevicesRepository {
    List<Device> findAll();
    void save(Device device);
    List<Device> findByIds(List<Integer> deviceIds);
    Device findById(Integer id);
}
