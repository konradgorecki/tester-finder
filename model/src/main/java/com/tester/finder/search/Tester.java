package com.tester.finder.search;

import com.tester.finder.core.Country;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class Tester {
    Integer id;
    String firstName;
    String lastName;
    Country country;
    LocalDateTime lastLogin;
}
