package com.tester.finder.core;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
public class Tester {
    Integer id;
    String firstName;
    String lastName;
    Country country;
    LocalDateTime lastLogin;
    List<Device> devices;
}
