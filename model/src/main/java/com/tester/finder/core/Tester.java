package com.tester.finder.core;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Value
@Builder
public class Tester {
    Integer id;
    String firstName;
    String lastName;
    Country country;
    LocalDateTime lastLogin;
    List<Device> devices;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tester tester = (Tester) o;
        return id.equals(tester.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
