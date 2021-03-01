package com.tester.finder.core;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode
public class Country {

    public static final Country ALL = new Country("ALL");

    String code;
}
