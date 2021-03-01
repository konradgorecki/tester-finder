package com.tester.finder.core.repository.implementation;

import com.tester.finder.core.Device;
import com.tester.finder.core.repository.DevicesRepository;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryDevicesRepository implements DevicesRepository {

    private final Map<Integer, Device> devices = new HashMap<>();

    @Override
    public List<Device> findAll() {
        return new ArrayList<>(devices.values());
    }

    @Override
    public void save(Device device) {
        if (devices.containsKey(device.getId())) {
            throw new IllegalArgumentException(String.format("Device with id %s already exists.", device.getId()));
        }
        devices.put(device.getId(), device);
    }

    @Override
    public List<Device> findByIds(List<Integer> deviceIds) {
        return deviceIds.stream()
                .map(devices::get)
                .filter(Objects::nonNull) // Map::get might return null in case of missing object
                .collect(Collectors.toList());
    }
}
