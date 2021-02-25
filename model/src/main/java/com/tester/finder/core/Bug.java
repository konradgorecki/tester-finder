package com.tester.finder.core;

import lombok.Value;

@Value
public class Bug {
    Integer bugId;
    Device device;
    Tester tester;
}
