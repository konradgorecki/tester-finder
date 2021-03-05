package com.tester.finder.core.repository.implementation;

import com.tester.finder.core.Tester;
import com.tester.finder.core.repository.TestersRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryTestersRepository implements TestersRepository {

    private final Map<Integer, Tester> testers = new HashMap<>();

    @Override
    public List<Tester> findAll() {
        return new ArrayList<>(testers.values());
    }

    @Override
    public void save(Tester tester) {
        if (testers.containsKey(tester.getId())) {
            throw new IllegalArgumentException(String.format("Tester with id %s already exists.", tester.getId()));
        }
        testers.put(tester.getId(), tester);
    }

    @Override
    public List<Tester> findByDeviceId(List<Integer> deviceIds) {
        return testers.values().stream()
                .filter(tester -> this.hasAnyOfTheDevices(tester, deviceIds))
                .collect(Collectors.toList());
    }

    private boolean hasAnyOfTheDevices(Tester tester, List<Integer> deviceIds) {
        return tester.getDevices().stream()
                .anyMatch(device -> deviceIds.contains(device.getId()));
    }

    @Override
    public Tester findById(Integer id) {
        return testers.get(id);
    }
}
