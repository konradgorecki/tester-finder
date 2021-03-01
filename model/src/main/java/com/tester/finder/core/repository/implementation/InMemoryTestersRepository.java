package com.tester.finder.core.repository.implementation;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.tester.finder.core.Tester;
import com.tester.finder.core.repository.TestersRepository;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryTestersRepository implements TestersRepository {

    private final Multimap<Integer, Tester> testersMappedByDeviceId = LinkedHashMultimap.create();
    private final Set<Integer> ids = new HashSet<>();

    @Override
    public List<Tester> findAll() {
        return new ArrayList<>(new HashSet<>(testersMappedByDeviceId.values()));
    }

    @Override
    public void save(Tester tester) {
        if (ids.contains(tester.getId())) {
            throw new IllegalArgumentException(String.format("Tester with id %s already exists.", tester.getId()));
        }
        ids.add(tester.getId());
        tester.getDevices().forEach(device -> testersMappedByDeviceId.put(device.getId(), tester));

    }

    @Override
    public List<Tester> findByDeviceId(List<Integer> deviceIds) {
        return deviceIds.stream()
                .flatMap(deviceId -> testersMappedByDeviceId.get(deviceId).stream())
                .collect(Collectors.toList());
    }

}
