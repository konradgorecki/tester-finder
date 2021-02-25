package com.tester.finder.core;

import lombok.Value;

@Value
public class Device {
    public static Device ALL = new Device(-123, "ALL");

    Integer id;
    String description;
}

